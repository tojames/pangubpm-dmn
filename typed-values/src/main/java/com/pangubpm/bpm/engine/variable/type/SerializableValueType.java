/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.type;

import java.util.Map;

import com.pangubpm.bpm.engine.variable.value.SerializableValue;

/**
 * @author pangubpm(pangubpm@139.com)
 * @since 7.2
 */
public interface SerializableValueType extends ValueType {

  /**
   * Identifies the object's java type name.
   */
  String VALUE_INFO_OBJECT_TYPE_NAME = "objectTypeName";

  /**
   * Identifies the format in which the object is serialized.
   */
  String VALUE_INFO_SERIALIZATION_DATA_FORMAT = "serializationDataFormat";


  /**
   * Creates a new TypedValue using this type.
   * @param serializedValue the value in serialized form
   * @return the typed value for the value
   */
  SerializableValue createValueFromSerialized(String serializedValue, Map<String, Object> valueInfo);

}
