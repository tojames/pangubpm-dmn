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
 * Exception thrown if the syntax of a FEEL expression is invalid.
 */
public class FeelSyntaxException extends FeelException {

  protected String feelExpression;
  protected String description;

  public FeelSyntaxException(String message, String feelExpression, String description) {
    super(message);
    this.feelExpression = feelExpression;
    this.description = description;
  }

  public FeelSyntaxException(String message, String feelExpression, String description, Throwable cause) {
    super(message, cause);
    this.feelExpression = feelExpression;
    this.description = description;
  }

  public String getFeelExpression() {
    return feelExpression;
  }

  public String getDescription() {
    return description;
  }

}
