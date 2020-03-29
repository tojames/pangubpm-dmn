/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.attribute;

import com.pangubpm.bpm.model.xml.impl.util.ModelUtil;
import com.pangubpm.bpm.model.xml.type.ModelElementType;

/**
 * <p>class for providing Boolean value attributes. Takes care of type conversion and
 * the interaction with the underlying Xml model model.</p>
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class BooleanAttribute extends AttributeImpl<Boolean> {

  public BooleanAttribute(ModelElementType owningElementType) {
    super(owningElementType);
  }

  protected Boolean convertXmlValueToModelValue(String rawValue) {
    return ModelUtil.valueAsBoolean(rawValue);
  }

  protected String convertModelValueToXmlValue(Boolean modelValue) {
    return ModelUtil.valueAsString(modelValue);
  }

}
