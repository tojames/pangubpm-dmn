/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl;

/**
 * Factory to create a instance of a {@link FeelEngine}.
 */
public interface FeelEngineFactory {

  /**
   * Create an instance of a {@link FeelEngine}.
   *
   * @return the instance of a {@link FeelEngine}
   */
  FeelEngine createInstance();

}
