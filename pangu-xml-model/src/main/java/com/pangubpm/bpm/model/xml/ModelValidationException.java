/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml;


/**
 * Thrown when an error is encountered during the model validation.
 *
 * @author pangubpm( pangubpm@139.com )
 *
 */
public class ModelValidationException extends ModelException {

  private static final long serialVersionUID = 1L;

  public ModelValidationException() {
  }

  public ModelValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ModelValidationException(String message) {
    super(message);
  }

  public ModelValidationException(Throwable cause) {
    super(cause);
  }

}
