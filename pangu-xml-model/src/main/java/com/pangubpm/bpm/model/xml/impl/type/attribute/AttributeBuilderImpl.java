/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.attribute;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.impl.ModelBuildOperation;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.type.attribute.Attribute;
import com.pangubpm.bpm.model.xml.type.attribute.AttributeBuilder;

/**
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public abstract class AttributeBuilderImpl<T> implements AttributeBuilder<T>, ModelBuildOperation {

  private final AttributeImpl<T> attribute;
  private final ModelElementTypeImpl modelType;

  AttributeBuilderImpl(String attributeName, ModelElementTypeImpl modelType, AttributeImpl<T> attribute) {
    this.modelType = modelType;
    this.attribute = attribute;
    attribute.setAttributeName(attributeName);
  }

  public AttributeBuilder<T> namespace(String namespaceUri) {
    attribute.setNamespaceUri(namespaceUri);
    return this;
  }

  public AttributeBuilder<T> idAttribute() {
    attribute.setId();
    return this;
  }


  public AttributeBuilder<T> defaultValue(T defaultValue) {
    attribute.setDefaultValue(defaultValue);
    return this;
  }

  public AttributeBuilder<T> required() {
    attribute.setRequired(true);
    return this;
  }

  public Attribute<T> build() {
    modelType.registerAttribute(attribute);
    return attribute;
  }

  public void performModelBuild(Model model) {
    // do nothing
  }

}
