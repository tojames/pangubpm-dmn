/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.el;

import java.lang.reflect.Method;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import com.pangubpm.bpm.engine.variable.context.VariableContext;

public interface ElContextFactory {

  /**
   * Create a {@link ELContext} for the given {@link ExpressionFactory} and {@link VariableContext}.
   *
   * @param expressionFactory the {@link ExpressionFactory} to use
   * @param variableContext the {@link VariableContext} to use
   * @return the {@link ELContext} instance
   */
  ELContext createContext(ExpressionFactory expressionFactory, VariableContext variableContext);

  /**
   * Add a custom function which can be used by the context.
   *
   * @param name the name of the function
   * @param method the method reference of the function
   */
  void addCustomFunction(String name, Method method);

}
