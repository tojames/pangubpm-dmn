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
 * Exception thrown if an error occurs during a method invocation
 * in a FEEL expression.
 */
public class FeelMethodInvocationException extends FeelException {

  protected String method;
  protected String[] parameters;

  public FeelMethodInvocationException(String message, String method, String... parameters) {
    super(message);
    this.method = method;
    this.parameters = parameters;
  }

  public FeelMethodInvocationException(String message, Throwable cause, String method, String... parameters) {
    super(message, cause);
    this.method = method;
    this.parameters = parameters;
  }

  public String getMethod() {
    return method;
  }

  public String[] getParameters() {
    return parameters;
  }

}
