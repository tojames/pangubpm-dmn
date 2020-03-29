/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.commons.utils;

import java.io.IOException;

/**
 * @author pangubpm.
 * @Email pangubpm@139.com
 */
public class IoUtilLogger extends UtilsLogger {

  public IoUtilException unableToReadInputStream(IOException cause) {
    return new IoUtilException(exceptionMessage("001", "Unable to read input stream"), cause);
  }

  public IoUtilException fileNotFoundException(String filename, Exception cause) {
    return new IoUtilException(exceptionMessage("002", "Unable to find file with path '{}'", filename), cause);
  }

  public IoUtilException fileNotFoundException(String filename) {
    return fileNotFoundException(filename, null);
  }

  public IoUtilException nullParameter(String parameter) {
    return new IoUtilException(exceptionMessage("003", "Parameter '{}' can not be null", parameter));
  }

  public IoUtilException unableToReadFromReader(Throwable cause) {
    return new IoUtilException(exceptionMessage("004", "Unable to read from reader"), cause);
  }

}
