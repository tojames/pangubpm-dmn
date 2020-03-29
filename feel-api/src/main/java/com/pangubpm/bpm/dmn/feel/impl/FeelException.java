/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl;

/**
 * Exception which is thrown if a error occurs during the
 * evaluation of a FEEL expression.
 */
public class FeelException extends RuntimeException {

  public FeelException(String message) {
    super(message);
  }

  public FeelException(String message, Throwable cause) {
    super(message, cause);
  }

}
