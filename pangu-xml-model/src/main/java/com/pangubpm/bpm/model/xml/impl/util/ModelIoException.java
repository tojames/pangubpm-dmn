/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.util;

/**
 * An exception during IO operations.
 *
 * @author pangubpm( pangubpm@139.com )
 */
public class ModelIoException extends RuntimeException {

  public ModelIoException() {
  }

  public ModelIoException(String message) {
    super(message);
  }

  public ModelIoException(String message, Throwable cause) {
    super(message, cause);
  }

  public ModelIoException(Throwable cause) {
    super(cause);
  }

}
