/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.impl.value.builder;

import com.pangubpm.bpm.engine.variable.impl.value.ObjectValueImpl;
import com.pangubpm.bpm.engine.variable.value.ObjectValue;
import com.pangubpm.bpm.engine.variable.value.SerializationDataFormat;
import com.pangubpm.bpm.engine.variable.value.builder.SerializedObjectValueBuilder;
import com.pangubpm.bpm.engine.variable.value.builder.TypedValueBuilder;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class SerializedObjectValueBuilderImpl implements SerializedObjectValueBuilder {

  protected ObjectValueImpl variableValue;

  public SerializedObjectValueBuilderImpl() {
    variableValue = new ObjectValueImpl(null, null, null, null, false);
  }

  public SerializedObjectValueBuilderImpl(ObjectValue value) {
    variableValue = (ObjectValueImpl) value;
  }

  public SerializedObjectValueBuilder serializationDataFormat(String dataFormatName) {
    variableValue.setSerializationDataFormat(dataFormatName);
    return this;
  }

  public SerializedObjectValueBuilder serializationDataFormat(SerializationDataFormat dataFormat) {
    return serializationDataFormat(dataFormat.getName());
  }

  public ObjectValue create() {
    return variableValue;
  }

  public SerializedObjectValueBuilder objectTypeName(String typeName) {
    variableValue.setObjectTypeName(typeName);
    return this;
  }

  public SerializedObjectValueBuilder serializedValue(String value) {
    variableValue.setSerializedValue(value);
    return this;
  }

  @Override
  public TypedValueBuilder<ObjectValue> setTransient(boolean isTransient) {
    variableValue.setTransient(isTransient);
    return this;
  }

}
