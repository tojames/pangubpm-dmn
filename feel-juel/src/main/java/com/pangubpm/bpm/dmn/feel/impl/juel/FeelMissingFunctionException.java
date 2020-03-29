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
 * Exception thrown if a method used in a FEEL expression is not available.
 */
public class FeelMissingFunctionException extends FeelException {

  protected String function;

  public FeelMissingFunctionException(String message, String function) {
    super(message);
    this.function = function;
  }

  public FeelMissingFunctionException(String message, Throwable cause, String function) {
    super(message, cause);
    this.function = function;
  }

  public String getFunction() {
    return function;
  }

}
