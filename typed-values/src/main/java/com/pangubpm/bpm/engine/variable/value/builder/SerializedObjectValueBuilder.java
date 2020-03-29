/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.value.builder;

import com.pangubpm.bpm.engine.variable.value.SerializationDataFormat;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface SerializedObjectValueBuilder extends ObjectValueBuilder {

  SerializedObjectValueBuilder serializedValue(String value);

  SerializedObjectValueBuilder objectTypeName(String typeName);

  SerializedObjectValueBuilder serializationDataFormat(String dataFormatName);

  SerializedObjectValueBuilder serializationDataFormat(SerializationDataFormat dataFormat);

}
