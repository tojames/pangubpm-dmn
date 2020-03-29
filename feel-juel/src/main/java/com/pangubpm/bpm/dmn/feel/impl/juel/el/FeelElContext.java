/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.el;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.FunctionMapper;
import javax.el.VariableMapper;

public class FeelElContext extends ELContext {

  protected ELResolver elResolver;
  protected FunctionMapper functionMapper;
  protected VariableMapper variableMapper;

  public FeelElContext(ELResolver elResolver, FunctionMapper functionMapper, VariableMapper variableMapper) {
    this.elResolver = elResolver;
    this.functionMapper = functionMapper;
    this.variableMapper = variableMapper;
  }

  public ELResolver getELResolver() {
    return elResolver;
  }

  public FunctionMapper getFunctionMapper() {
    return functionMapper;
  }

  public VariableMapper getVariableMapper() {
    return variableMapper;
  }

}
