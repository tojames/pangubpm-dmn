/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.validation;

/**
 * Object passed to the {@link ModelElementValidator} to collect validation results.
 *
 * @author pangubpm(pangubpm@139.com)
 * @since 7.6
 */
public interface ValidationResultCollector {

  /**
   * Adds an error
   *
   * @param code a reference code for the error
   * @param message a human consumable error message
   */
  void addError(int code, String message);

  /**
   * Adds a warining
   *
   * @param code a reference code for the error
   * @param message a human consumable error message
   */
  void addWarning(int code, String message);

}
