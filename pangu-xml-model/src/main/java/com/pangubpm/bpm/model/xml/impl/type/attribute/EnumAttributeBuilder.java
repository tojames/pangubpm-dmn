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
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class EnumAttributeBuilder<T extends Enum<T>> extends AttributeBuilderImpl<T> {

  public EnumAttributeBuilder(String attributeName, ModelElementTypeImpl modelType, Class<T> type) {
    super(attributeName, modelType, new EnumAttribute<T>(modelType, type));
  }

  @Override
  public EnumAttributeBuilder<T> namespace(String namespaceUri) {
    return (EnumAttributeBuilder<T>) super.namespace(namespaceUri);
  }

  @Override
  public EnumAttributeBuilder<T> defaultValue(T defaultValue) {
    return (EnumAttributeBuilder<T>) super.defaultValue(defaultValue);
  }

  @Override
  public EnumAttributeBuilder<T> required() {
    return (EnumAttributeBuilder<T>) super.required();
  }

  @Override
  public EnumAttributeBuilder<T> idAttribute() {
    return (EnumAttributeBuilder<T>) super.idAttribute();
  }
}
