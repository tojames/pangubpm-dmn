/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.type;

import java.util.Collection;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public interface ValueTypeResolver {

  void addType(ValueType type);

  ValueType typeForName(String typeName);

  /**
   * Returns all (transitive) sub types of the provided type
   * given they are not abstract
   *
   * @return
   */
  Collection<ValueType> getSubTypes(ValueType type);
}
