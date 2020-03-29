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
 * Exception thrown if a variable used in a FEEL expression is not available.
 */
public class FeelMissingVariableException extends FeelException {

  private final String variable;

  public FeelMissingVariableException(String message, String variable) {
    super(message);
    this.variable = variable;
  }

  public FeelMissingVariableException(String message, Throwable cause, String variable) {
    super(message, cause);
    this.variable = variable;
  }

  public String getVariable() {
    return variable;
  }

}
