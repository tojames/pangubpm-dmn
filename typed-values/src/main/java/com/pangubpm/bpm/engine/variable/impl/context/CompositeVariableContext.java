/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.impl.context;

import java.util.HashSet;
import java.util.Set;

import com.pangubpm.bpm.engine.variable.context.VariableContext;
import com.pangubpm.bpm.engine.variable.value.TypedValue;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class CompositeVariableContext implements VariableContext {

  protected final VariableContext[] delegateContexts;

  public CompositeVariableContext(VariableContext[] delegateContexts) {
    this.delegateContexts = delegateContexts;
  }

  public TypedValue resolve(String variableName) {
    for (VariableContext variableContext : delegateContexts) {
      TypedValue resolvedValue = variableContext.resolve(variableName);
      if(resolvedValue != null) {
        return resolvedValue;
      }
    }

    return null;
  }

  public boolean containsVariable(String name) {
    for (VariableContext variableContext : delegateContexts) {
      if(variableContext.containsVariable(name)) {
        return true;
      }
    }

    return false;
  }

  public Set<String> keySet() {
    Set<String> keySet = new HashSet<String>();
    for (VariableContext variableContext : delegateContexts) {
      keySet.addAll(variableContext.keySet());
    }
    return keySet;
  }

  public static CompositeVariableContext compose(VariableContext... variableContexts) {
    return new CompositeVariableContext(variableContexts);
  }

}
