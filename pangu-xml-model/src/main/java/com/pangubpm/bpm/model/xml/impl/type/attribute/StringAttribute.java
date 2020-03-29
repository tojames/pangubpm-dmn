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
 * <p>Base class for String attributes
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class StringAttribute extends AttributeImpl<String> {

  public StringAttribute(ModelElementType owningElementType) {
    super(owningElementType);
  }

  protected String convertXmlValueToModelValue(String rawValue) {
    return rawValue;
  }

  protected String convertModelValueToXmlValue(String modelValue) {
    return modelValue;
  }

}
