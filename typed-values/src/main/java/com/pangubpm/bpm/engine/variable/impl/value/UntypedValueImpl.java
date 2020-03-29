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
 * Used when the type of an object has not been specified by the user and
 * needs to be autodetected.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class UntypedValueImpl implements TypedValue {

  private static final long serialVersionUID = 1L;

  protected Object value;

  protected boolean isTransient;

  public UntypedValueImpl(Object object) {
    this(object, false);
  }

  public UntypedValueImpl(Object object, boolean isTransient) {
    this.value = object;
    this.isTransient = isTransient;
  }

  public Object getValue() {
    return value;
  }

  public ValueType getType() {
    // no type
    return null;
  }

  @Override
  public String toString() {
    return "Untyped value '"+value+"', isTransient = " + isTransient;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    result = prime * result + (isTransient ? 1 : 0);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UntypedValueImpl other = (UntypedValueImpl) obj;
    if (value == null) {
      if (other.value != null)
        return false;
    } else if (!value.equals(other.value))
      return false;
    if (isTransient != other.isTransient()) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isTransient() {
    return isTransient;
  }

  public void setTransient(boolean isTransient) {
    this.isTransient = isTransient;
  }

}
