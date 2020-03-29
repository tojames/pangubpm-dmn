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
public class DoubleAttributeBuilder extends AttributeBuilderImpl<Double> {

  public DoubleAttributeBuilder(String attributeName, ModelElementTypeImpl modelType) {
    super(attributeName, modelType, new DoubleAttribute((modelType)));
  }

  @Override
  public DoubleAttributeBuilder namespace(String namespaceUri) {
    return (DoubleAttributeBuilder) super.namespace(namespaceUri);
  }

  @Override
  public DoubleAttributeBuilder defaultValue(Double defaultValue) {
    return (DoubleAttributeBuilder) super.defaultValue(defaultValue);
  }

  @Override
  public DoubleAttributeBuilder required() {
    return (DoubleAttributeBuilder) super.required();
  }

  @Override
  public DoubleAttributeBuilder idAttribute() {
    return (DoubleAttributeBuilder) super.idAttribute();
  }
}
