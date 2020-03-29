/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type;

import com.pangubpm.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.attribute.AttributeBuilder;
import com.pangubpm.bpm.model.xml.type.attribute.StringAttributeBuilder;
import com.pangubpm.bpm.model.xml.type.child.SequenceBuilder;

/**
 * @author pangubpm(pangubpm@139.com)
 */
public interface ModelElementTypeBuilder {

  ModelElementTypeBuilder namespaceUri(String namespaceUri);

  ModelElementTypeBuilder extendsType(Class<? extends ModelElementInstance> extendedType);

  <T extends ModelElementInstance> ModelElementTypeBuilder instanceProvider(ModelTypeInstanceProvider<T> instanceProvider);

  ModelElementTypeBuilder abstractType();

  AttributeBuilder<Boolean> booleanAttribute(String attributeName);

  StringAttributeBuilder stringAttribute(String attributeName);

  AttributeBuilder<Integer> integerAttribute(String attributeName);

  AttributeBuilder<Double> doubleAttribute(String attributeName);

  <V extends Enum<V>> AttributeBuilder<V> enumAttribute(String attributeName, Class<V> enumType);

  <V extends Enum<V>> AttributeBuilder<V> namedEnumAttribute(String attributeName, Class<V> enumType);

  SequenceBuilder sequence();

  ModelElementType build();


  interface ModelTypeInstanceProvider<T extends ModelElementInstance> {
    T newInstance(ModelTypeInstanceContext instanceContext);
  }

}
