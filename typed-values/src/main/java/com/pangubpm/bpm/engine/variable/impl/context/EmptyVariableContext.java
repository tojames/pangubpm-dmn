/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.impl.context;

import java.util.Collections;
import java.util.Set;

import com.pangubpm.bpm.engine.variable.context.VariableContext;
import com.pangubpm.bpm.engine.variable.value.TypedValue;

/**
 * An empty variable context implementation which does
 * not allow to resolve any variables.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class EmptyVariableContext implements VariableContext {

  public final static EmptyVariableContext INSTANCE = new EmptyVariableContext();

  private EmptyVariableContext() {
    // hidden
  }

  public TypedValue resolve(String variableName) {
    return null;
  }

  public boolean containsVariable(String variableName) {
    return false;
  }

  public Set<String> keySet() {
    return Collections.emptySet();
  }

}
