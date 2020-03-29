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
 * @author pangubpm( pangubpm@139.com )
 */
public class IntegerAttributeBuilder extends AttributeBuilderImpl<Integer> {

  public IntegerAttributeBuilder(String attributeName, ModelElementTypeImpl modelType) {
    super(attributeName, modelType, new IntegerAttribute(modelType));
  }

  @Override
  public IntegerAttributeBuilder namespace(String namespaceUri) {
    return (IntegerAttributeBuilder) super.namespace(namespaceUri);
  }

  @Override
  public IntegerAttributeBuilder defaultValue(Integer defaultValue) {
    return (IntegerAttributeBuilder) super.defaultValue(defaultValue);
  }

  @Override
  public IntegerAttributeBuilder required() {
    return (IntegerAttributeBuilder) super.required();
  }

  @Override
  public IntegerAttributeBuilder idAttribute() {
    return (IntegerAttributeBuilder) super.idAttribute();
  }
}
