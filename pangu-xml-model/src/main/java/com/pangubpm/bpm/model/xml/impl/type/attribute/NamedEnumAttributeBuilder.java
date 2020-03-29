/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.attribute;

import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;

public class NamedEnumAttributeBuilder<T extends Enum<T>> extends AttributeBuilderImpl<T> {

  public NamedEnumAttributeBuilder(String attributeName, ModelElementTypeImpl modelType, Class<T> type) {
    super(attributeName, modelType, new NamedEnumAttribute<T>(modelType, type));
  }

  @Override
  public NamedEnumAttributeBuilder<T> namespace(String namespaceUri) {
    return (NamedEnumAttributeBuilder<T>) super.namespace(namespaceUri);
  }

  @Override
  public NamedEnumAttributeBuilder<T> defaultValue(T defaultValue) {
    return (NamedEnumAttributeBuilder<T>) super.defaultValue(defaultValue);
  }

  @Override
  public NamedEnumAttributeBuilder<T> required() {
    return (NamedEnumAttributeBuilder<T>) super.required();
  }

  @Override
  public NamedEnumAttributeBuilder<T> idAttribute() {
    return (NamedEnumAttributeBuilder<T>) super.idAttribute();
  }

}
