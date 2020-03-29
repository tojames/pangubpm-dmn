/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable;

import java.util.Map;

import com.pangubpm.bpm.engine.variable.context.VariableContext;
import com.pangubpm.bpm.engine.variable.value.TypedValue;

/**
 * A Map of variables.
 *
 * @author pangubpm.
 * @Email pangubpm@139.com
 */
public interface VariableMap extends Map<String, Object> {

  // fluent api for collecting variables ////////////////////////

  VariableMap putValue(String name, Object value);

  VariableMap putValueTyped(String name, TypedValue value);

  // retrieving variables ///////////////////////////////////////

  <T> T getValue(String name, Class<T> type);

  <T extends TypedValue> T getValueTyped(String name);

  /**
   * Interprets the variable map as variable context
   *
   * @return A VariableContext which is capable of resolving all variables in the map
   */
  VariableContext asVariableContext();
}
