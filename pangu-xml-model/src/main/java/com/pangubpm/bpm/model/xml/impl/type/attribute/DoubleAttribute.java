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
public class DoubleAttribute extends AttributeImpl<Double> {

  DoubleAttribute(ModelElementType owningElementType) {
    super(owningElementType);
  }

  protected Double convertXmlValueToModelValue(String rawValue) {
    if (rawValue != null) {
      try {
        return Double.parseDouble(rawValue);
      }
      catch (NumberFormatException e) {
        return null;
      }
    }
    else {
      return null;
    }
  }

  protected String convertModelValueToXmlValue(Double modelValue) {
    return modelValue.toString();
  }
}
