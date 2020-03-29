/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.value;

import java.io.Serializable;

import com.pangubpm.bpm.engine.variable.type.ValueType;

/**
 * <p>A {@link TypedValue} is a value with additional type information (the {@link ValueType}).
 * TypedValues are used for representing variable values.</p>
 *
 * @author pangubpm(pangubpm@139.com)
 * @since 7.2
 */
public interface TypedValue extends Serializable {

  /**
   * The actual value. May be null in case the value is null.
   *
   * @return the value
   */
  Object getValue();

  /**
   * The type of the value. See ValueType for a list of built-in ValueTypes.
   * @return the type of the value.
   */
  ValueType getType();

  /**
   * Indicator for transience of the value
   * @return isTransient
   */
  boolean isTransient();

}
