/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.el;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.el.VariableMapper;

import com.pangubpm.bpm.dmn.feel.impl.juel.FeelEngineLogger;
import com.pangubpm.bpm.dmn.feel.impl.juel.FeelLogger;
import com.pangubpm.bpm.engine.variable.context.VariableContext;
import com.pangubpm.bpm.engine.variable.value.TypedValue;

public class FeelTypedVariableMapper extends VariableMapper {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;

  protected ExpressionFactory expressionFactory;
  protected VariableContext variableContext;

  public FeelTypedVariableMapper(ExpressionFactory expressionFactory, VariableContext variableContext) {
    this.expressionFactory = expressionFactory;
    this.variableContext = variableContext;
  }

  public ValueExpression resolveVariable(String variable) {
    if (variableContext.containsVariable(variable)) {
      Object value = unpackVariable(variable);
      return expressionFactory.createValueExpression(value, Object.class);
    }
    else {
      throw LOG.unknownVariable(variable);
    }
  }

  public ValueExpression setVariable(String variable, ValueExpression expression) {
    throw LOG.variableMapperIsReadOnly();
  }

  public Object unpackVariable(String variable) {
    TypedValue valueTyped = variableContext.resolve(variable);
    if(valueTyped != null) {
      return valueTyped.getValue();
    }
    return null;
  }

}
