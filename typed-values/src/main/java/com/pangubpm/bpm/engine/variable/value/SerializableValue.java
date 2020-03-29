/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.value;

import com.pangubpm.bpm.engine.variable.type.SerializableValueType;

/**
 * A {@link TypedValue} for which a serialized value can be obtained and specified
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface SerializableValue extends TypedValue {

  /**
   * Returns true in case the value is deserialized. If this method returns true,
   * it is safe to call the {@link #getValue()} method
   *
   * @return true if the object is deserialized.
   */
  boolean isDeserialized();

  /**
   * Returns the value or null in case the value is null.
   *
   * @return the value represented by this TypedValue.
   * @throws IllegalStateException in case the value is not deserialized. See {@link #isDeserialized()}.
   */
  Object getValue();

  /**
   * Returns the serialized value. In case the serializaton data format
   * (as returned by {@link #getSerializationDataFormat()}) is not text based,
   * a base 64 encoded representation of the value is returned
   *
   * The serialized value is a snapshot of the state of the value as it is
   * serialized to the process engine database.
   */
  String getValueSerialized();

  /**
   * The serialization format used to serialize this value.
   *
   * @return the serialization format used to serialize this variable.
   */
  String getSerializationDataFormat();

  SerializableValueType getType();

}
