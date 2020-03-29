/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.value;

import com.pangubpm.bpm.engine.variable.Variables;

/**
 * <p>Represents a serialization data format.</p>
 *
 * @author pangubpm(pangubpm@139.com)
 * @see Variables.SerializationDataFormats
 *
 * @since 7.2
 */
public interface SerializationDataFormat {

  /**
   * The name of the dataformat. Example: "application/json"
   *
   * @return the name of the dataformat.
   */
  String getName();

}
