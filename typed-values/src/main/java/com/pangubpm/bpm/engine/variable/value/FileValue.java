/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.value;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author pangubpm( pangubpm@139.com )
 * @since 7.4
 *
 */
public interface FileValue extends TypedValue {

  String getFilename();

  String getMimeType();

  /**
   * Convenience method to save the transformation. This method will perform no
   * check if the saved encoding is known to the JVM and therefore could throw
   * every exception that {@link Charset#forName(String)} lists.
   * <p>
   * If no encoding has been saved it will return null.
   *
   */
  Charset getEncodingAsCharset();

  /**
   * @return the saved encoding or null if none has been saved
   */
  String getEncoding();

  @Override
  InputStream getValue();

}
