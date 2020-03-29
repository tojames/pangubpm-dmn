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

public class ComparisonTransformer implements FeelToJuelTransformer {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;
  public static final Pattern COMPARISON_PATTERN = Pattern.compile("^(<=|>=|<|>)([^=].*)$");

  public boolean canTransform(String feelExpression) {
    return feelExpression.startsWith("<=") || feelExpression.startsWith("<") || feelExpression.startsWith(">=") || feelExpression.startsWith(">");
  }

  public String transform(FeelToJuelTransform transform, String feelExpression, String inputName) {
    Matcher matcher = COMPARISON_PATTERN.matcher(feelExpression);
    if (matcher.matches()) {
      return transformComparison(transform, matcher.group(1), matcher.group(2), inputName);
    }
    else {
      throw LOG.invalidComparisonExpression(feelExpression);
    }
  }

  protected String transformComparison(FeelToJuelTransform transform, String operator, String endpoint, String inputName) {
    String juelEndpoint = transform.transformEndpoint(endpoint, inputName);
    return String.format("%s %s %s", inputName, operator, juelEndpoint);
  }

}
