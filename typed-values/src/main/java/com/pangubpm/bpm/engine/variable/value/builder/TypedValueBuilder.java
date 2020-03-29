/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.value.builder;

import com.pangubpm.bpm.engine.variable.value.TypedValue;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface TypedValueBuilder<T extends TypedValue> {

  T create();

  TypedValueBuilder<T> setTransient(boolean isTransient);

}
