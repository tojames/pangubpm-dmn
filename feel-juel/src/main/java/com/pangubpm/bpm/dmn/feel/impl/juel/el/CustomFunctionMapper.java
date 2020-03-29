/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.el;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.el.FunctionMapper;

import com.pangubpm.bpm.dmn.feel.impl.juel.FeelEngineLogger;
import com.pangubpm.bpm.dmn.feel.impl.juel.FeelLogger;

public class CustomFunctionMapper extends FunctionMapper {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;

  protected final Map<String, Method> methods = new HashMap<String, Method>();

  @Override
  public Method resolveFunction(String prefix, String localName) {
    return methods.get(localName);
  }

  public void addMethod(final String name, final Method method) {
    methods.put(name, method);
  }

}
