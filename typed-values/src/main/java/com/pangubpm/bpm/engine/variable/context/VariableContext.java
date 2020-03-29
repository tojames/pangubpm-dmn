/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.context;

import java.util.Set;

import com.pangubpm.bpm.engine.variable.value.TypedValue;

/**
 * A context for variables. Allows resolving variables.
 *
 * An API may choose to accept a VariableContext instead of a map of concrete values
 * in situations where passing all available variables would be expensive and
 * lazy-loading is a desirable optimization.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface VariableContext {

  /**
   * Resolve a value in this context.
   *
   * @param variableName the name of the variable to resolve.
   * @return the value of the variable or null in case the variable does not exist.
   */
  TypedValue resolve(String variableName);

  /**
   * Checks whether a variable with the given name is resolve through this context.
   *
   * @param variableName the name of the variable to check
   * @return true if the variable is resolve.
   */
  boolean containsVariable(String variableName);

  /**
   * @return a set of all variable names resolvable through this Context.
   */
  Set<String> keySet();

}
