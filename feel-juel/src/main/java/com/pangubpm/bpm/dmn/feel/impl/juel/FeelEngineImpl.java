/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import com.pangubpm.bpm.dmn.feel.impl.FeelEngine;
import com.pangubpm.bpm.dmn.feel.impl.juel.el.ElContextFactory;
import com.pangubpm.bpm.dmn.feel.impl.juel.transform.FeelToJuelTransform;
import com.pangubpm.bpm.engine.variable.context.VariableContext;
import com.pangubpm.commons.utils.cache.Cache;

public class FeelEngineImpl implements FeelEngine {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;

  protected FeelToJuelTransform transform;
  protected ExpressionFactory expressionFactory;
  protected ElContextFactory elContextFactory;
  protected Cache<TransformExpressionCacheKey, String> transformExpressionCache;

  public FeelEngineImpl(FeelToJuelTransform transform, ExpressionFactory expressionFactory, ElContextFactory elContextFactory,
      Cache<TransformExpressionCacheKey, String> transformExpressionCache) {
    this.transform = transform;
    this.expressionFactory = expressionFactory;
    this.elContextFactory = elContextFactory;
    this.transformExpressionCache = transformExpressionCache;
  }

  public <T> T evaluateSimpleExpression(String simpleExpression, VariableContext variableContext) {
    throw LOG.simpleExpressionNotSupported();
  }

  public boolean evaluateSimpleUnaryTests(String simpleUnaryTests, String inputName, VariableContext variableContext) {
    try {
      ELContext elContext = createContext(variableContext);
      ValueExpression valueExpression = transformSimpleUnaryTests(simpleUnaryTests, inputName, elContext);
       return (Boolean) valueExpression.getValue(elContext);
    }
    catch (FeelMissingFunctionException e) {
      throw LOG.unknownFunction(simpleUnaryTests, e);
    }
    catch (FeelMissingVariableException e) {
      if (inputName.equals(e.getVariable())) {
        throw LOG.unableToEvaluateExpressionAsNotInputIsSet(simpleUnaryTests, e);
      }
      else {
        throw LOG.unknownVariable(simpleUnaryTests, e);
      }
    }
    catch (FeelConvertException e) {
      throw LOG.unableToConvertValue(simpleUnaryTests, e);
    }
    catch (ELException e) {
      if (e.getCause() instanceof FeelMethodInvocationException) {
        throw LOG.unableToInvokeMethod(simpleUnaryTests, (FeelMethodInvocationException) e.getCause());
      }
      else {
        throw LOG.unableToEvaluateExpression(simpleUnaryTests, e);
      }
    }
  }

  protected ELContext createContext(VariableContext variableContext) {
    return elContextFactory.createContext(expressionFactory, variableContext);
  }

  protected ValueExpression transformSimpleUnaryTests(String simpleUnaryTests, String inputName, ELContext elContext) {

    String juelExpression = transformToJuelExpression(simpleUnaryTests, inputName);

    try {
      return expressionFactory.createValueExpression(elContext, juelExpression, Object.class);
    }
    catch (ELException e) {
      throw LOG.invalidExpression(simpleUnaryTests, e);
    }
  }

  protected String transformToJuelExpression(String simpleUnaryTests, String inputName) {

    TransformExpressionCacheKey cacheKey = new TransformExpressionCacheKey(simpleUnaryTests, inputName);
    String juelExpression = transformExpressionCache.get(cacheKey);

    if (juelExpression == null) {
      juelExpression = transform.transformSimpleUnaryTests(simpleUnaryTests, inputName);
      transformExpressionCache.put(cacheKey, juelExpression);
    }
    return juelExpression;
  }

}
