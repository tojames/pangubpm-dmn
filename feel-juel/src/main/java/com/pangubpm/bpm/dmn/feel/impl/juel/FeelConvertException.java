/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel;

import com.pangubpm.bpm.dmn.feel.impl.FeelException;

/**
 * Exception thrown if a invalid type conversion occurs in a FEEL expression.
 */
public class FeelConvertException extends FeelException {

  protected Object value;
  protected Class<?> type;

  public FeelConvertException(String message, Object value, Class<?> type) {
    super(message);
    this.value = value;
    this.type = type;
  }

  public FeelConvertException(String message, Object value, Class<?> type, Throwable cause) {
    super(message, cause);
    this.value = value;
    this.type = type;
  }

  public FeelConvertException(String message, FeelConvertException convertException) {
    super(message, convertException.getCause());
    this.value = convertException.getValue();
    this.type = convertException.getType();
  }

  public Object getValue() {
    return value;
  }

  public Class<?> getType() {
    return type;
  }

}
