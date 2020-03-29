/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.transform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pangubpm.bpm.dmn.feel.impl.juel.FeelEngineLogger;
import com.pangubpm.bpm.dmn.feel.impl.juel.FeelLogger;

public class NotTransformer implements FeelToJuelTransformer {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;
  public static final Pattern NOT_PATTERN = Pattern.compile("^not\\((.+)\\)$");

  public boolean canTransform(String feelExpression) {
    return feelExpression.startsWith("not(");
  }

  public String transform(FeelToJuelTransform transform, String feelExpression, String inputName) {
    String simplePositiveUnaryTests = extractInnerExpression(feelExpression);
    String juelExpression = transform.transformSimplePositiveUnaryTests(simplePositiveUnaryTests, inputName);
    return "not(" + juelExpression + ")";
  }

  public String extractInnerExpression(String feelExpression) {
    Matcher matcher = NOT_PATTERN.matcher(feelExpression);
    if (matcher.matches()) {
      return matcher.group(1);
    }
    else {
      throw LOG.invalidNotExpression(feelExpression);
    }
  }

}
