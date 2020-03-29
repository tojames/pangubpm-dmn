/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.attribute;


/**
 * @author pangubpm(pangubpm@139.com)
 *
 * @param <T> the type of the {@link Attribute}
 */
public interface AttributeBuilder<T> {

  AttributeBuilder<T> namespace(String namespaceUri);

  AttributeBuilder<T> defaultValue(T defaultValue);

  AttributeBuilder<T> required();

  AttributeBuilder<T> idAttribute();

  Attribute<T> build();

}
