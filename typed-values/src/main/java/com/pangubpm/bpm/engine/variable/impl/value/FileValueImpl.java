/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.impl.value;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.pangubpm.bpm.engine.variable.type.FileValueType;
import com.pangubpm.bpm.engine.variable.type.ValueType;
import com.pangubpm.bpm.engine.variable.value.FileValue;

/**
 * @author pangubpm( pangubpm@139.com )
 * @since 7.4
 *
 */
public class FileValueImpl implements FileValue {

  private static final long serialVersionUID = 1L;
  protected String mimeType;
  protected String filename;
  protected byte[] value;
  protected FileValueType type;
  protected String encoding;
  protected boolean isTransient;

  public FileValueImpl(byte[] value, FileValueType type, String filename, String mimeType, String encoding) {
    this.value = value;
    this.type = type;
    this.filename = filename;
    this.mimeType = mimeType;
    this.encoding = encoding;
  }

  public FileValueImpl(FileValueType type, String filename) {
    this(null, type, filename, null, null);
  }

  @Override
  public String getFilename() {
    return filename;
  }

  @Override
  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public void setValue(byte[] bytes) {
    this.value = bytes;
  }

  @Override
  public InputStream getValue() {
    if (value == null) {
      return null;
    }
    return new ByteArrayInputStream(value);
  }

  @Override
  public ValueType getType() {
    return type;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public void setEncoding(Charset encoding) {
    this.encoding = encoding.name();
  }

  @Override
  public Charset getEncodingAsCharset() {
    if (encoding == null) {
      return null;
    }
    return Charset.forName(encoding);
  }

  @Override
  public String getEncoding() {
    return encoding;
  }

  /**
   * Get the byte array directly without wrapping it inside a stream to evade
   * not needed wrapping. This method is intended for the internal API, which
   * needs the byte array anyways.
   */
  public byte[] getByteArray() {
    return value;
  }

  @Override
  public String toString() {
    return "FileValueImpl [mimeType=" + mimeType + ", filename=" + filename + ", type=" + type + ", isTransient=" + isTransient + "]";
  }

  @Override
  public boolean isTransient() {
    return isTransient;
  }

  public void setTransient(boolean isTransient) {
    this.isTransient = isTransient;
  }
}
