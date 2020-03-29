/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.impl.type;

import java.util.Map;

import com.pangubpm.bpm.engine.variable.type.ValueType;
import com.pangubpm.bpm.engine.variable.value.TypedValue;

/**
 * @author pangubpm( pangubpm@139.com )
 *
 */
public abstract class AbstractValueTypeImpl implements ValueType {

  private static final long serialVersionUID = 1L;

  protected String name;

  public AbstractValueTypeImpl(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  public boolean isAbstract() {
    return false;
  }

  public ValueType getParent() {
    return null;
  }

  public boolean canConvertFromTypedValue(TypedValue typedValue) {
    return false;
  }

  public TypedValue convertFromTypedValue(TypedValue typedValue) {
    throw unsupportedConversion(typedValue.getType());
  }

  protected IllegalArgumentException unsupportedConversion(ValueType typeToConvertTo) {
    return new IllegalArgumentException("The type " + getName() + " supports no conversion from type: " + typeToConvertTo.getName());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    AbstractValueTypeImpl other = (AbstractValueTypeImpl) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  protected Boolean isTransient(Map<String, Object> valueInfo) {
    if (valueInfo != null && valueInfo.containsKey(VALUE_INFO_TRANSIENT)) {
      Object isTransient = valueInfo.get(VALUE_INFO_TRANSIENT);
      if (isTransient instanceof Boolean) {
        return (Boolean) isTransient;
      } else {
        throw new IllegalArgumentException("The property 'transient' should have a value of type 'boolean'.");
      }
    }
    return false;
  }

}
