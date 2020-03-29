/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.attribute;

import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;

/**
 * <p>Builder for building {@link BooleanAttribute BooleanAttributes}</p>
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class BooleanAttributeBuilder extends AttributeBuilderImpl<Boolean> {

  public BooleanAttributeBuilder(String attributeName, ModelElementTypeImpl modelType) {
    super(attributeName, modelType, new BooleanAttribute(modelType));
  }

}
