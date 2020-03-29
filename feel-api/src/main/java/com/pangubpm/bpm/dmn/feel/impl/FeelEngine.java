/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl;

import com.pangubpm.bpm.engine.variable.context.VariableContext;

/**
 * Engine to evaluate FEEL expressions.
 */
public interface FeelEngine {

  /**
   * Evaluate a FEEL simple expression
   *
   * @param simpleExpression the simple expression to evaluate
   * @param variableContext the variable context which are available
   * @param <T> the expected return type
   * @return the result of the simple expression
   *
   * @throws FeelException
   *           if the expression cannot be evaluated
   */
  <T> T evaluateSimpleExpression(String simpleExpression, VariableContext variableContext);

  /**
   * Evaluate a FEEL simple unary tests expression
   *
   * @param simpleUnaryTests the simple unary tests expression to evaluate
   * @param inputName the name of the variable which is tested
   * @param variableContext the variable context are available
   * @return the result of the simple unary tests expression
   *
   * @throws FeelException
   *           if the expression cannot be evaluated
   */
  boolean evaluateSimpleUnaryTests(String simpleUnaryTests, String inputName, VariableContext variableContext);

}
