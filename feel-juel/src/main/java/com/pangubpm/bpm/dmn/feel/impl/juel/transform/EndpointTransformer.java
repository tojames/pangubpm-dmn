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

import com.pangubpm.bpm.dmn.feel.impl.juel.el.FeelFunctionMapper;

public class EndpointTransformer implements FeelToJuelTransformer {

  public static final Pattern DATE_AND_TIME_PATTERN = Pattern.compile("^date and time\\((.+)\\)$");

  public boolean canTransform(String feelExpression) {
    return true;
  }

  public String transform(FeelToJuelTransform transform, String feelExpression, String inputName) {
    Matcher matcher = DATE_AND_TIME_PATTERN.matcher(feelExpression);
    if (matcher.matches()) {
      return FeelFunctionMapper.JUEL_DATE_AND_TIME_METHOD + "(" + matcher.group(1) + ")";
    }
    else {
      return feelExpression;
    }
  }

}
