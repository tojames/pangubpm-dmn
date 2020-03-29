/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.commons.utils;

/**
 * @author pangubpm.
 * @Email pangubpm@139.com
 */
public class EnsureUtilLogger extends UtilsLogger {

  public IllegalArgumentException parameterIsNullException(String parameterName) {
    return new IllegalArgumentException(exceptionMessage("001", "Parameter '{}' is null", parameterName));
  }

  public IllegalArgumentException unsupportedParameterType(String parameterName, Object param, Class<?> expectedType) {
    return new IllegalArgumentException(exceptionMessage("002", "Unsupported parameter '{}' of type '{}'. Expected type '{}'.", parameterName, param.getClass(), expectedType.getName()));
  }
}
