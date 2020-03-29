/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.el;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.el.FunctionMapper;

import com.pangubpm.bpm.dmn.feel.impl.juel.FeelEngineLogger;
import com.pangubpm.bpm.dmn.feel.impl.juel.FeelLogger;

public class FeelFunctionMapper extends FunctionMapper {

  public static final FeelEngineLogger LOG = FeelLogger.ENGINE_LOGGER;

  protected static final SimpleDateFormat FEEL_DATE_AND_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
  public static final String JUEL_DATE_AND_TIME_METHOD = "dateAndTime";

  protected static final Map<String, Method> methods = new HashMap<String, Method>();

  static {
    methods.put(JUEL_DATE_AND_TIME_METHOD, getMethod("parseDateAndTime", String.class));
  }

  public Method resolveFunction(String prefix, String localName) {
    return methods.get(localName);
  }

  protected static Method getMethod(String name, Class<?>... parameterTypes) {
    try {
      return FeelFunctionMapper.class.getMethod(name, parameterTypes);
    } catch (NoSuchMethodException e) {
      throw LOG.unableToFindMethod(e, name, parameterTypes);
    }
  }

  public static Date parseDateAndTime(String dateAndTimeString) {
    try {
      SimpleDateFormat clonedDateFormat = (SimpleDateFormat) FEEL_DATE_AND_TIME_FORMAT.clone();
      return clonedDateFormat.parse(dateAndTimeString);
    } catch (ParseException e) {
      throw LOG.invalidDateAndTimeFormat(dateAndTimeString, e);
    }
  }

}
