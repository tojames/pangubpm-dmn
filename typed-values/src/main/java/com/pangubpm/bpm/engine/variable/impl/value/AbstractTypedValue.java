/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.impl.value;

import com.pangubpm.bpm.engine.variable.type.ValueType;
import com.pangubpm.bpm.engine.variable.value.TypedValue;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class AbstractTypedValue<T> implements TypedValue {

  private static final long serialVersionUID = 1L;

  protected T value;

  protected ValueType type;

  protected boolean isTransient;

  public AbstractTypedValue(T value, ValueType type) {
    this.value = value;
    this.type = type;
  }

  public T getValue() {
    return value;
  }

  public ValueType getType() {
    return type;
  }

  public String toString() {
    return "Value '" + value + "' of type '" + type + "', isTransient=" + isTransient;
  }

  @Override
  public boolean isTransient() {
    return isTransient;
  }

  public void setTransient(boolean isTransient) {
    this.isTransient = isTransient;
  }

}
