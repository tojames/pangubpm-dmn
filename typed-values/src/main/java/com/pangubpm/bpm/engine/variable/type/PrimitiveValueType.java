/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.type;


/**
 * @author pangubpm(pangubpm@139.com)
 * @since 7.2
 */
public interface PrimitiveValueType extends ValueType {

  Class<?> getJavaType();

}
