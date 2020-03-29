/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.transform;

public interface FeelToJuelTransform {

  /**
   * Transform a FEEL simple unary tests expression to a JUEL expression.
   *
   * @param simpleUnaryTests the FEEL simple unary tests expression to transform
   * @param inputName the variable name of the input variable to test against
   * @return the resulting JUEL expression
   */
  String transformSimpleUnaryTests(String simpleUnaryTests, String inputName);

  /**
   * Transform a FEEL simple positive unary tests expression to a JUEL expression.
   *
   * @param simplePositiveUnaryTests the FEEL simple positive unary tests expression to transform
   * @param inputName the variable name of the input variable to test against
   * @return the resulting JUEL expression
   */
  String transformSimplePositiveUnaryTests(String simplePositiveUnaryTests, String inputName);

  /**
   * Transform a FEEL simple positive unary test expression to a JUEL expression.
   *
   * @param simplePositiveUnaryTest the FEEL simple positive unary test expression to transform
   * @param inputName the variable name of the input variable to test against
   * @return the resulting JUEL expression
   */
  String transformSimplePositiveUnaryTest(String simplePositiveUnaryTest, String inputName);

  /**
   * Transform a FEEL endpoint expression to a JUEL expression.
   *
   * @param endpoint the FEEL endpoint expression to transform
   * @param inputName the variable name of the input variable to test against
   * @return the resulting JUEL expression
   */
  String transformEndpoint(String endpoint, String inputName);

  /**
   * Add a transformer for a custom function.
   *
   * @param functionTransformer the transformer for the custom function
   */
  void addCustomFunctionTransformer(FeelToJuelTransformer functionTransformer);

}
