/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.value.builder;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.pangubpm.bpm.engine.variable.value.FileValue;

/**
 * @author pangubpm( pangubpm@139.com )
 * @since 7.4
 *
 */
public interface FileValueBuilder extends TypedValueBuilder<FileValue> {

  /**
   * Saves the MIME type of a file in the value infos.
   *
   * @param mimeType
   *          the MIME type as string
   */
  FileValueBuilder mimeType(String mimeType);

  /**
   * Sets the value to the specified {@link File}.
   *
   * @see #file(byte[])
   * @see #file(InputStream)
   */
  FileValueBuilder file(File file);

  /**
   * Sets the value to the specified {@link InputStream}.
   *
   * @see #file(byte[])
   * @see #file(File)
   */
  FileValueBuilder file(InputStream stream);

  /**
   * Sets the value to the specified {@link Byte} array
   *
   * @see #file(File)
   * @see #file(InputStream)
   */
  FileValueBuilder file(byte[] bytes);

  /**
   * Sets the encoding for the file in the value infos (optional).
   *
   * @param encoding
   * @return
   */
  FileValueBuilder encoding(Charset encoding);

  /**
   * Sets the encoding for the file in the value infos (optional).
   *
   * @param encoding
   * @return
   */
  FileValueBuilder encoding(String encoding);

}
