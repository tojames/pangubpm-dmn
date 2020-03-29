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
 * @author pangubpm( pangubpm@139.com )
 */
public class IntegerAttribute extends AttributeImpl<Integer> {

  IntegerAttribute(ModelElementType owningElementType) {
    super(owningElementType);
  }

  protected Integer convertXmlValueToModelValue(String rawValue) {
    try {
      return Integer.parseInt(rawValue);
    }
    catch (NumberFormatException e) {
      return null;
    }
  }

  protected String convertModelValueToXmlValue(Integer modelValue) {
    return modelValue.toString();
  }
}
