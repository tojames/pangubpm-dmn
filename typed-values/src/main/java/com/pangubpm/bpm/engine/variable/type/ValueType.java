/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.type;

import java.io.Serializable;
import java.util.Map;

import com.pangubpm.bpm.engine.variable.impl.type.FileValueTypeImpl;
import com.pangubpm.bpm.engine.variable.impl.type.ObjectTypeImpl;
import com.pangubpm.bpm.engine.variable.value.TypedValue;
import com.pangubpm.bpm.engine.variable.impl.type.PrimitiveValueTypeImpl;

/**
 *
 * @author pangubpm( pangubpm@139.com )
 * @author pangubpm(pangubpm@139.com)
 *
 * @since 7.2
 */
public interface ValueType extends Serializable {

  PrimitiveValueType NULL = new PrimitiveValueTypeImpl.NullTypeImpl();

  PrimitiveValueType BOOLEAN = new PrimitiveValueTypeImpl.BooleanTypeImpl();

  PrimitiveValueType SHORT = new PrimitiveValueTypeImpl.ShortTypeImpl();

  PrimitiveValueType LONG = new PrimitiveValueTypeImpl.LongTypeImpl();

  PrimitiveValueType DOUBLE = new PrimitiveValueTypeImpl.DoubleTypeImpl();

  PrimitiveValueType STRING = new PrimitiveValueTypeImpl.StringTypeImpl();

  PrimitiveValueType INTEGER = new PrimitiveValueTypeImpl.IntegerTypeImpl();

  PrimitiveValueType DATE = new PrimitiveValueTypeImpl.DateTypeImpl();

  PrimitiveValueType BYTES = new PrimitiveValueTypeImpl.BytesTypeImpl();

  PrimitiveValueType NUMBER = new PrimitiveValueTypeImpl.NumberTypeImpl();

  SerializableValueType OBJECT = new ObjectTypeImpl();

  FileValueType FILE = new FileValueTypeImpl();

  String VALUE_INFO_TRANSIENT = "transient";
  /**
   * Returns the name of the variable type
   */
  String getName();

  /**
   * Indicates whether this type is primitive valued. Primitive valued types can be handled
   * natively by the process engine.
   *
   * @return true if this is a primitive valued type. False otherwise
   */
  boolean isPrimitiveValueType();

  /**
   * Get the value info (meta data) for a {@link TypedValue}.
   * The keys of the returned map for a {@link TypedValue} are available
   * as constants in the value's {@link ValueType} interface.
   *
   * @param typedValue
   * @return
   */
  Map<String, Object> getValueInfo(TypedValue typedValue);

  /**
   * Creates a new TypedValue using this type.
   * @param value the value
   * @return the typed value for the value
   */
  public TypedValue createValue(Object value, Map<String, Object> valueInfo);

  /**
   * <p>Gets the parent value type.</p>
   *
   * <p>Value type hierarchy is only relevant for queries and has the
   * following meaning: When a value query is made
   * (e.g. all tasks with a certain variable value), a "child" type's value
   * also matches a parameter value of the parent type. This is only
   * supported when the parent value type's implementation of {@link #isAbstract()}
   * returns <code>true</code>.</p>
   */
  ValueType getParent();

  /**
   * Determines whether the argument typed value can be converted to a
   * typed value of this value type.
   */
  boolean canConvertFromTypedValue(TypedValue typedValue);

  /**
   * Converts a typed value to a typed value of this type.
   * This does not suceed if {@link #canConvertFromTypedValue(TypedValue)}
   * returns <code>false</code>.
   */
  TypedValue convertFromTypedValue(TypedValue typedValue);

  /**
   * <p>Returns whether the value type is abstract. This is <b>not related
   * to the term <i>abstract</i> in the Java language.</b></p>
   *
   * Abstract value types cannot be used as types for variables but only used for querying.
   */
  boolean isAbstract();


}
