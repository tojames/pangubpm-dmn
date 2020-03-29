/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml;


/**
 * Thrown when an error is encountered during XML parsing
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ModelParseException extends ModelException {

  private static final long serialVersionUID = 1L;

  public ModelParseException() {
  }

  public ModelParseException(String message, Throwable cause) {
    super(message, cause);
  }

  public ModelParseException(String message) {
    super(message);
  }

  public ModelParseException(Throwable cause) {
    super(cause);
  }

}
