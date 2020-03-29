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
public class EnsureUtil {

  private static final EnsureUtilLogger LOG = UtilsLogger.ENSURE_UTIL_LOGGER;

  /**
   * Ensures that the parameter is not null.
   *
   * @param parameterName the parameter name
   * @param value the value to ensure to be not null
   * @throws IllegalArgumentException if the parameter value is null
   */
  public static void ensureNotNull(String parameterName, Object value) {
    if(value == null) {
      throw LOG.parameterIsNullException(parameterName);
    }
  }

  /**
   * Ensure the object is of a given type and return the casted object
   *
   * @param objectName the name of the parameter
   * @param object the parameter value
   * @param type the expected type
   * @return the parameter casted to the requested type
   * @throws IllegalArgumentException in case object cannot be casted to type
   */
  @SuppressWarnings("unchecked")
  public static <T> T ensureParamInstanceOf(String objectName, Object object, Class<T> type) {
    if(type.isAssignableFrom(object.getClass())) {
      return (T) object;
    } else {
      throw LOG.unsupportedParameterType(objectName, object, type);
    }
  }
}
