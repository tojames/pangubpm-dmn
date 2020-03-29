/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.attribute;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceBuilder;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceCollection;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceCollectionBuilder;

/**
 * @author pangubpm( pangubpm@139.com )
 *
 */
public interface StringAttributeBuilder extends AttributeBuilder<String> {

  StringAttributeBuilder namespace(String namespaceUri);

  StringAttributeBuilder defaultValue(String defaultValue);

  StringAttributeBuilder required();

  StringAttributeBuilder idAttribute();

  <V extends ModelElementInstance> AttributeReferenceBuilder<V> qNameAttributeReference(Class<V> referenceTargetElement);

  <V extends ModelElementInstance> AttributeReferenceBuilder<V> idAttributeReference(Class<V>  referenceTargetElement);

  @SuppressWarnings("rawtypes")
  <V extends ModelElementInstance> AttributeReferenceCollectionBuilder<V> idAttributeReferenceCollection(Class<V> referenceTargetElement, Class<? extends AttributeReferenceCollection> attributeReferenceCollection);

}
