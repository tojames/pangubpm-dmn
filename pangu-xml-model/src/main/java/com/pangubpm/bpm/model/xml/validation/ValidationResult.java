/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.validation;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

/**
 * An individual validation result.
 *
 * @author pangubpm(pangubpm@139.com)
 * @since 7.6
 */
public interface ValidationResult {

  /**
   * @return The type of the result.
   */
  ValidationResultType getType();

  /**
   * @return the element
   */
  ModelElementInstance getElement();

  /**
   * @return A human consumable detail message about the validation
   */
  String getMessage();

  /**
   * @return A reference code for this validation result
   */
  int getCode();

}
