/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml;

/**
 * An Exception in the Core XML Model.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ModelException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ModelException() {
  }

  public ModelException(String message, Throwable cause) {
    super(message, cause);
  }

  public ModelException(String message) {
    super(message);
  }

  public ModelException(Throwable cause) {
    super(cause);
  }

}
