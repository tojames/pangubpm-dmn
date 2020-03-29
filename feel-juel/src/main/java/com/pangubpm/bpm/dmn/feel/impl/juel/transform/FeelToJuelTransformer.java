/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.transform;

public interface FeelToJuelTransformer {

  /**
   * Test if an expression can be transformed by this transformer.
   *
   * @param feelExpression the FEEL expression to transform
   * @return true if the expression can be transformed by this transformer, false otherwise
   */
  boolean canTransform(String feelExpression);

  /**
   * Transform the FEEL expression to a JUEL expression.
   *
   * @param transform the {@link FeelToJuelTransform} to use for further transforms
   * @param feelExpression the FEEL expression to transform
   * @param inputName the variable name of the input variable to test against
   * @return the resulting JUEL expression
   */
  String transform(FeelToJuelTransform transform, String feelExpression, String inputName);

}
