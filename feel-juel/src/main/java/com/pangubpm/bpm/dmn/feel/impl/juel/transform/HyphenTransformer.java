/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.transform;

public class HyphenTransformer implements FeelToJuelTransformer {

  public boolean canTransform(String feelExpression) {
    return feelExpression.equals("-");
  }

  public String transform(FeelToJuelTransform transform, String feelExpression, String inputName) {
    return "true";
  }

}
