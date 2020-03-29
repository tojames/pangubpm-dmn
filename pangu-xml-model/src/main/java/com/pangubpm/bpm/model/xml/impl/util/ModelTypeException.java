/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.util;

import com.pangubpm.bpm.model.xml.ModelException;

import java.lang.reflect.Type;

/**
 * <p>Thrown in case a value cannot be converted to or from the requested type</p>
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ModelTypeException extends ModelException {

  private static final long serialVersionUID = 1L;

  public ModelTypeException(String message) {
    super(message);
  }

  public ModelTypeException(String value, Type type) {
    super("Illegal value "+value+" for type "+type);
  }

}
