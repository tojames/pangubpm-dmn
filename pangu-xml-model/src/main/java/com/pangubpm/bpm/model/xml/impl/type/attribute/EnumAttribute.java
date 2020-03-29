/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.attribute;

import com.pangubpm.bpm.model.xml.type.ModelElementType;

/**
 * <p>An attribute exposing an Enum value</p>
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class EnumAttribute<T extends Enum<T>> extends AttributeImpl<T> {

  private final Class<T> type;

  public EnumAttribute(ModelElementType owningElementType, Class<T> type) {
    super(owningElementType);
    this.type = type;
  }

  protected T convertXmlValueToModelValue(String rawValue) {
    if (rawValue != null) {
      return Enum.valueOf(type, rawValue);
    }
    else {
      return null;
    }
  }

  protected String convertModelValueToXmlValue(T modelValue) {
    return modelValue.name();
  }

}
