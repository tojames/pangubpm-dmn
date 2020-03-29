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
 * An {@link VariableContext} allowing to resolve a single variable only.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class SingleVariableContext implements VariableContext {

  protected final TypedValue typedValue;
  protected String name;

  public SingleVariableContext(String name, TypedValue typedValue) {
    this.name = name;
    this.typedValue = typedValue;
  }

  public TypedValue resolve(String variableName) {
    if(containsVariable(variableName)) {
      return typedValue;
    }
    else {
      return null;
    }
  }

  public boolean containsVariable(String name) {
    if(this.name == null) {
      return name == null;
    }
    else {
      return this.name.equals(name);
    }
  }

  public Set<String> keySet() {
    return Collections.singleton(name);
  }

  public static SingleVariableContext singleVariable(String name, TypedValue value) {
    return new SingleVariableContext(name, value);
  }

}
