/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.value;

/**
 * <p>A typed value representing a Java Object.</p>
 *
 * @author pangubpm(pangubpm@139.com)
 * @since 7.2
 */
public interface ObjectValue extends SerializableValue {

  /**
   * Returns true in case the object is deserialized. If this method returns true,
   * it is safe to call the methods
   * <ul>
   *   <li>{@link #getValue()} and {@link #getValue(Class)}</li>
   *   <li>{@link #getObjectType()}</li>
   * </ul>
   *
   * @return true if the object is deserialized.
   */
  boolean isDeserialized();

  /**
   * Returns the Object or null in case the value is null.
   *
   * @return the object represented by this TypedValue.
   * @throws IllegalStateException in case the object is not deserialized. See {@link #isDeserialized()}.
   */
  Object getValue();

  /**
   * Returns the object provided by this VariableValue. Allows type-safe access to objects
   * by passing in the class.
   *
   * @param type the java class the value should be cast to
   * @return the object represented by this TypedValue.
   * @throws IllegalStateException in case the object is not deserialized. See {@link #isDeserialized()}.
   */
  <T> T getValue(Class<T> type);

  /**
   * Returns the Class this object is an instance of.
   *
   * @return the Class this object is an instance of
   * @throws IllegalStateException in case the object is not deserialized. See {@link #isDeserialized()}.
   */
  Class<?> getObjectType();

  /**
   * A String representation of the Object's type name.
   * Usually the canonical class name of the Java Class this object
   * is an instance of.
   *
   * @return the Object's type name.
   */
  String getObjectTypeName();

}
