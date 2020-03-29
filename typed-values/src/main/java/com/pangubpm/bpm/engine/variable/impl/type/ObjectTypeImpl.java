/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.impl.type;

import java.util.HashMap;
import java.util.Map;

import com.pangubpm.bpm.engine.variable.Variables;
import com.pangubpm.bpm.engine.variable.type.SerializableValueType;
import com.pangubpm.bpm.engine.variable.value.ObjectValue;
import com.pangubpm.bpm.engine.variable.value.SerializableValue;
import com.pangubpm.bpm.engine.variable.value.TypedValue;
import com.pangubpm.bpm.engine.variable.value.builder.ObjectValueBuilder;
import com.pangubpm.bpm.engine.variable.value.builder.SerializedObjectValueBuilder;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ObjectTypeImpl extends AbstractValueTypeImpl implements SerializableValueType {

  private static final long serialVersionUID = 1L;

  public static final String TYPE_NAME = "object";

  public ObjectTypeImpl() {
    super(TYPE_NAME);
  }

  public boolean isPrimitiveValueType() {
    return false;
  }

  public TypedValue createValue(Object value, Map<String, Object> valueInfo) {
    ObjectValueBuilder builder = Variables.objectValue(value);

    if(valueInfo != null) {
      applyValueInfo(builder, valueInfo);
    }

    return builder.create();
  }

  public Map<String, Object> getValueInfo(TypedValue typedValue) {
    if(!(typedValue instanceof ObjectValue)) {
      throw new IllegalArgumentException("Value not of type Object.");
    }
    ObjectValue objectValue = (ObjectValue) typedValue;

    Map<String, Object> valueInfo = new HashMap<String, Object>();

    String serializationDataFormat = objectValue.getSerializationDataFormat();
    if(serializationDataFormat != null) {
      valueInfo.put(VALUE_INFO_SERIALIZATION_DATA_FORMAT, serializationDataFormat);
    }

    String objectTypeName = objectValue.getObjectTypeName();
    if(objectTypeName != null) {
      valueInfo.put(VALUE_INFO_OBJECT_TYPE_NAME, objectTypeName);
    }

    if (objectValue.isTransient()) {
      valueInfo.put(VALUE_INFO_TRANSIENT, objectValue.isTransient());
    }

    return valueInfo;
  }

  public SerializableValue createValueFromSerialized(String serializedValue, Map<String, Object> valueInfo) {
    SerializedObjectValueBuilder builder = Variables.serializedObjectValue(serializedValue);

    if(valueInfo != null) {
      applyValueInfo(builder, valueInfo);
    }

    return builder.create();
  }

  protected void applyValueInfo(ObjectValueBuilder builder, Map<String, Object> valueInfo) {

    String objectValueTypeName = (String) valueInfo.get(VALUE_INFO_OBJECT_TYPE_NAME);
    if (builder instanceof SerializedObjectValueBuilder) {
      ((SerializedObjectValueBuilder) builder).objectTypeName(objectValueTypeName);
    }

    String serializationDataFormat = (String) valueInfo.get(VALUE_INFO_SERIALIZATION_DATA_FORMAT);
    if(serializationDataFormat != null) {
      builder.serializationDataFormat(serializationDataFormat);
    }

    builder.setTransient(isTransient(valueInfo));
  }

}
