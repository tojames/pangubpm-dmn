/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.impl.value.builder;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.pangubpm.bpm.engine.variable.impl.value.FileValueImpl;
import com.pangubpm.bpm.engine.variable.type.PrimitiveValueType;
import com.pangubpm.bpm.engine.variable.value.FileValue;
import com.pangubpm.bpm.engine.variable.value.builder.FileValueBuilder;
import com.pangubpm.commons.utils.EnsureUtil;
import com.pangubpm.commons.utils.IoUtil;
import com.pangubpm.commons.utils.IoUtilException;

/**
 * @author pangubpm( pangubpm@139.com )
 * @since 7.4
 *
 */
public class FileValueBuilderImpl implements FileValueBuilder {

  protected FileValueImpl fileValue;

  public FileValueBuilderImpl(String filename) {
    EnsureUtil.ensureNotNull("filename", filename);
    fileValue = new FileValueImpl(PrimitiveValueType.FILE, filename);
  }

  @Override
  public FileValue create() {
    return fileValue;
  }

  @Override
  public FileValueBuilder mimeType(String mimeType) {
    fileValue.setMimeType(mimeType);
    return this;
  }

  @Override
  public FileValueBuilder file(File file) {
    try {
      return file(IoUtil.fileAsByteArray(file));
    } catch(IoUtilException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Override
  public FileValueBuilder file(InputStream stream) {
      try {
        return file(IoUtil.inputStreamAsByteArray(stream));
	  } catch(IoUtilException e) {
	  	throw new IllegalArgumentException(e);
	  }
  }

  @Override
  public FileValueBuilder file(byte[] bytes) {
    fileValue.setValue(bytes);
    return this;
  }

  @Override
  public FileValueBuilder encoding(Charset encoding) {
    fileValue.setEncoding(encoding);
    return this;
  }

  @Override
  public FileValueBuilder encoding(String encoding) {
    fileValue.setEncoding(encoding);
    return this;
  }

  @Override
  public FileValueBuilder setTransient(boolean isTransient) {
    fileValue.setTransient(isTransient);
    return this;
  }

}
