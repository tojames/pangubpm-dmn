/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml;


/**
 * Thrown when an error is encountered during reference resolving.
 *
 * @author pangubpm( pangubpm@139.com )
 *
 */
public class ModelReferenceException extends ModelException {

  private static final long serialVersionUID = 1L;

  public ModelReferenceException() {
  }

  public ModelReferenceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ModelReferenceException(String message) {
    super(message);
  }

  public ModelReferenceException(Throwable cause) {
    super(cause);
  }

}
