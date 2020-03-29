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

public class IntervalTransformer implements FeelToJuelTransformer {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;
  public static final Pattern INTERVAL_PATTERN = Pattern.compile("^(\\(|\\[|\\])(.*[^\\.])\\.\\.(.+)(\\)|\\]|\\[)$");

  public boolean canTransform(String feelExpression) {
    return feelExpression.startsWith("(") || feelExpression.startsWith("[") || feelExpression.startsWith("]");
  }

  public String transform(FeelToJuelTransform transform, String feelExpression, String inputName) {
    Matcher matcher = INTERVAL_PATTERN.matcher(feelExpression);
    if (matcher.matches()) {
      return transformInterval(transform, matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), inputName);
    }
    else {
      throw LOG.invalidIntervalExpression(feelExpression);
    }
  }

  public String transformInterval(FeelToJuelTransform transform, String startIntervalSymbol, String lowerEndpoint, String upperEndpoint, String stopIntervalSymbol, String inputName) {
    String juelLowerEndpoint = transform.transformEndpoint(lowerEndpoint, inputName);
    String juelUpperEndpoint = transform.transformEndpoint(upperEndpoint, inputName);
    String lowerEndpointComparator = transformLowerEndpointComparator(startIntervalSymbol);
    String upperEndpointComparator = transformUpperEndpointComparator(stopIntervalSymbol);

    return String.format("%s %s %s && %s %s %s", inputName, lowerEndpointComparator, juelLowerEndpoint, inputName, upperEndpointComparator, juelUpperEndpoint);
  }

  protected String transformLowerEndpointComparator(String startIntervalSymbol) {
    if (startIntervalSymbol.equals("[")) {
      return ">=";
    }
    else {
      return ">";
    }
  }

  protected String transformUpperEndpointComparator(String stopIntervalSymbol) {
    if (stopIntervalSymbol.equals("]")) {
      return "<=";
    }
    else {
      return "<";
    }
  }

}
