/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.attribute;

import com.pangubpm.bpm.model.xml.type.ModelElementType;

public class NamedEnumAttribute<T extends Enum<T>> extends AttributeImpl<T> {

  protected final Class<T> type;

  public NamedEnumAttribute(ModelElementType owningElementType, Class<T> type) {
    super(owningElementType);
    this.type = type;
  }

  @SuppressWarnings("unchecked")
  protected T convertXmlValueToModelValue(String rawValue) {
    T[] enumConstants = type.getEnumConstants();
    if (rawValue != null && enumConstants != null) {
      for (T enumConstant : enumConstants) {
        if (rawValue.equals(enumConstant.toString())) {
          return enumConstant;
        }
      }
    }
    return null;
  }

  protected String convertModelValueToXmlValue(T modelValue) {
    return modelValue.toString();
  }

}
