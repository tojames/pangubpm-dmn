/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.el.ELException;
import javax.el.ExpressionFactory;

import com.pangubpm.bpm.dmn.feel.impl.FeelEngine;
import com.pangubpm.bpm.dmn.feel.impl.FeelEngineFactory;
import com.pangubpm.bpm.dmn.feel.impl.juel.el.ElContextFactory;
import com.pangubpm.bpm.dmn.feel.impl.juel.el.FeelElContextFactory;
import com.pangubpm.bpm.dmn.feel.impl.juel.el.FeelTypeConverter;
import com.pangubpm.bpm.dmn.feel.impl.juel.transform.FeelToJuelFunctionTransformer;
import com.pangubpm.bpm.dmn.feel.impl.juel.transform.FeelToJuelTransform;
import com.pangubpm.bpm.dmn.feel.impl.juel.transform.FeelToJuelTransformImpl;
import com.pangubpm.commons.utils.cache.Cache;
import com.pangubpm.commons.utils.cache.ConcurrentLruCache;

import de.odysseus.el.ExpressionFactoryImpl;

public class FeelEngineFactoryImpl implements FeelEngineFactory {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;

  public static final int DEFAULT_EXPRESSION_CACHE_SIZE = 10;

  protected final FeelEngine feelEngine;

  protected final int expressionCacheSize;
  protected final List<FeelToJuelFunctionTransformer> customFunctionTransformers;

  public FeelEngineFactoryImpl() {
    this(DEFAULT_EXPRESSION_CACHE_SIZE);
  }

  public FeelEngineFactoryImpl(int expressionCacheSize) {
      this(expressionCacheSize, Collections.<FeelToJuelFunctionTransformer> emptyList());
  }

  public FeelEngineFactoryImpl(List<FeelToJuelFunctionTransformer> customFunctionTransformers) {
      this(DEFAULT_EXPRESSION_CACHE_SIZE, customFunctionTransformers);
  }

  public FeelEngineFactoryImpl(int expressionCacheSize, List<FeelToJuelFunctionTransformer> customFunctionTransformers) {
    this.expressionCacheSize = expressionCacheSize;
    this.customFunctionTransformers = customFunctionTransformers;

    feelEngine = createFeelEngine();
  }

  public FeelEngine createInstance() {
    return feelEngine;
  }

  protected FeelEngine createFeelEngine() {
    FeelToJuelTransform transform = createFeelToJuelTransform();
    ExpressionFactory expressionFactory = createExpressionFactory();
    ElContextFactory elContextFactory = createElContextFactory();
    Cache<TransformExpressionCacheKey, String> transformExpressionCache = createTransformExpressionCache();
    return new FeelEngineImpl(transform, expressionFactory, elContextFactory, transformExpressionCache);
  }

  protected FeelToJuelTransform createFeelToJuelTransform() {
    FeelToJuelTransformImpl transformer = new FeelToJuelTransformImpl();

    for (FeelToJuelFunctionTransformer functionTransformer : customFunctionTransformers) {
      transformer.addCustomFunctionTransformer(functionTransformer);
    }

    return transformer;
  }

  protected ExpressionFactory createExpressionFactory() {
    Properties properties = new Properties();
    properties.put(ExpressionFactoryImpl.PROP_CACHE_SIZE, String.valueOf(expressionCacheSize));

    try {
      return new ExpressionFactoryImpl(properties, createTypeConverter());
    }
    catch (ELException e) {
      throw LOG.unableToInitializeFeelEngine(e);
    }
  }

  protected FeelTypeConverter createTypeConverter() {
    return new FeelTypeConverter();
  }

  protected ElContextFactory createElContextFactory() {
    FeelElContextFactory factory = new FeelElContextFactory();

    for (FeelToJuelFunctionTransformer functionTransformer : customFunctionTransformers) {
      factory.addCustomFunction(functionTransformer.getName(), functionTransformer.getMethod());
    }

    return factory;
  }

  protected Cache<TransformExpressionCacheKey, String> createTransformExpressionCache() {
    return new ConcurrentLruCache<TransformExpressionCacheKey, String>(expressionCacheSize);
  }

}
