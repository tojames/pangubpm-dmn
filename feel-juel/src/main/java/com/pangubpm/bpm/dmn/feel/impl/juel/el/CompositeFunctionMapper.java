/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.el;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.el.FunctionMapper;

import com.pangubpm.bpm.dmn.feel.impl.juel.FeelEngineLogger;
import com.pangubpm.bpm.dmn.feel.impl.juel.FeelLogger;

public class CompositeFunctionMapper extends FunctionMapper {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;

  protected List<FunctionMapper> functionMappers = new ArrayList<FunctionMapper>();

  public Method resolveFunction(String prefix, String localName) {
    for (FunctionMapper functionMapper : functionMappers) {
      Method method = functionMapper.resolveFunction(prefix, localName);
      if (method != null) {
        return method;
      }
    }
    throw LOG.unknownFunction(prefix, localName);
  }

  public void setFunctionMappers(List<FunctionMapper> functionMappers) {
    this.functionMappers = functionMappers;
  }

  public void add(FunctionMapper functionMapper) {
    functionMappers.add(functionMapper);
  }

  public void remove(FunctionMapper functionMapper) {
    functionMappers.remove(functionMapper);
  }

}
