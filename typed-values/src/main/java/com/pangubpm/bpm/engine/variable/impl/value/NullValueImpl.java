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
 * Untyped Null
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class NullValueImpl implements TypedValue {

  private static final long serialVersionUID = 1L;

  private boolean isTransient;

  // null is always null
  public static final NullValueImpl INSTANCE = new NullValueImpl(false);
  public static final NullValueImpl INSTANCE_TRANSIENT = new NullValueImpl(true);

  private NullValueImpl(boolean isTransient) {
    this.isTransient = isTransient;
  }

  public Object getValue() {
    return null;
  }

  public ValueType getType() {
    return ValueType.NULL;
  }

  public String toString() {
    return "Untyped 'null' value";
  }

  @Override
  public boolean isTransient() {
    return isTransient;
  }

}
