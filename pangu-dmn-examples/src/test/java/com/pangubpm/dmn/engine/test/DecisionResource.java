/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.dmn.engine.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DecisionResource {

  /**
   * The DMN resources that contains the decision. The resource
   * can be given with a path or only the filename. For the latter
   * the test class location will be used to load the resource.
   *
   * If omitted the test class and method name will be used
   * to load the DMN the resource.
   */
  String resource() default "";

  /**
   * The id of the decision to use. If omitted the first decision
   * in the DMN resource is used.
   */
  String decisionKey() default "";

}
