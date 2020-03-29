/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.attribute;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.impl.ModelBuildOperation;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.AttributeReferenceBuilderImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.AttributeReferenceCollectionBuilderImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.QNameAttributeReferenceBuilderImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.attribute.StringAttributeBuilder;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceBuilder;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceCollection;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceCollectionBuilder;

/**
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class StringAttributeBuilderImpl extends AttributeBuilderImpl<String> implements StringAttributeBuilder {

  private AttributeReferenceBuilder<?> referenceBuilder;

  public StringAttributeBuilderImpl(String attributeName, ModelElementTypeImpl modelType) {
    super(attributeName, modelType, new StringAttribute(modelType));
  }

  @Override
  public StringAttributeBuilder namespace(String namespaceUri) {
    return (StringAttributeBuilder) super.namespace(namespaceUri);
  }

  @Override
  public StringAttributeBuilder defaultValue(String defaultValue) {
    return (StringAttributeBuilder) super.defaultValue(defaultValue);
  }

  @Override
  public StringAttributeBuilder required() {
    return (StringAttributeBuilder) super.required();
  }

  @Override
  public StringAttributeBuilder idAttribute() {
    return (StringAttributeBuilder) super.idAttribute();
  }

  /**
   * Create a new {@link AttributeReferenceBuilder} for the reference source element instance
   *
   * @param referenceTargetElement the reference target model element instance
   * @return the new attribute reference builder
   */
  public <V extends ModelElementInstance> AttributeReferenceBuilder<V> qNameAttributeReference(Class<V> referenceTargetElement) {
    AttributeImpl<String> attribute = (AttributeImpl<String>) build();
    AttributeReferenceBuilderImpl<V> referenceBuilder = new QNameAttributeReferenceBuilderImpl<V>(attribute, referenceTargetElement);
    setAttributeReference(referenceBuilder);
    return referenceBuilder;
  }

  public <V extends ModelElementInstance> AttributeReferenceBuilder<V> idAttributeReference(Class<V> referenceTargetElement) {
    AttributeImpl<String> attribute = (AttributeImpl<String>) build();
    AttributeReferenceBuilderImpl<V> referenceBuilder = new AttributeReferenceBuilderImpl<V>(attribute, referenceTargetElement);
    setAttributeReference(referenceBuilder);
    return referenceBuilder;
  }

  @SuppressWarnings("rawtypes")
  public <V extends ModelElementInstance> AttributeReferenceCollectionBuilder<V> idAttributeReferenceCollection(Class<V> referenceTargetElement, Class<? extends AttributeReferenceCollection> attributeReferenceCollection) {
    AttributeImpl<String> attribute = (AttributeImpl<String>) build();
    AttributeReferenceCollectionBuilder<V> referenceBuilder = new AttributeReferenceCollectionBuilderImpl<V>(attribute, referenceTargetElement, attributeReferenceCollection);
    setAttributeReference(referenceBuilder);
    return referenceBuilder;
  }

  protected <V extends ModelElementInstance> void setAttributeReference(AttributeReferenceBuilder<V> referenceBuilder) {
    if (this.referenceBuilder != null) {
      throw new ModelException("An attribute cannot have more than one reference");
    }
    this.referenceBuilder = referenceBuilder;
  }


  @Override
  public void performModelBuild(Model model) {
    super.performModelBuild(model);
    if (referenceBuilder != null) {
      ((ModelBuildOperation) referenceBuilder).performModelBuild(model);
    }
  }

}
