/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel.transform;

import java.lang.reflect.Method;

public interface FeelToJuelFunctionTransformer extends FeelToJuelTransformer {

  /**
   * Get the name of the function.
   *
   * @return the name of function
   */
  String getName();

  /**
   * Get the method reference which implements the function to transform to.
   * Note: The implementation should resolve the method reference only once at creation
   * and not within every call of these method.
   *
   * @return the method reference
   */
  Method getMethod();

}
