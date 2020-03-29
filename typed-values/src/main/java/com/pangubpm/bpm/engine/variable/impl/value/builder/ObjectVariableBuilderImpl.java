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
import com.pangubpm.bpm.engine.variable.value.builder.ObjectValueBuilder;
import com.pangubpm.bpm.engine.variable.value.builder.TypedValueBuilder;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ObjectVariableBuilderImpl implements ObjectValueBuilder {

  protected ObjectValueImpl variableValue;

  public ObjectVariableBuilderImpl(Object value) {
    variableValue = new ObjectValueImpl(value);
  }

  public ObjectVariableBuilderImpl(ObjectValue value) {
    variableValue = (ObjectValueImpl) value;
  }

  public ObjectValue create() {
    return variableValue;
  }

  public ObjectValueBuilder serializationDataFormat(String dataFormatName) {
    variableValue.setSerializationDataFormat(dataFormatName);
    return this;
  }

  public ObjectValueBuilder serializationDataFormat(SerializationDataFormat dataFormat) {
    return serializationDataFormat(dataFormat.getName());
  }

  @Override
  public TypedValueBuilder<ObjectValue> setTransient(boolean isTransient) {
    variableValue.setTransient(isTransient);
    return this;
  }

}
