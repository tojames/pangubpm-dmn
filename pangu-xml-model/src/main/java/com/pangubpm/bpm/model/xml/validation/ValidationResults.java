/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.validation;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import com.pangubpm.bpm.model.xml.ModelInstance;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

/**
 * Object in which the results of a model validation are collected.
 * See: {@link ModelInstance#validate(java.util.Collection)}.
 *
 * @author pangubpm(pangubpm@139.com)
 * @since 7.6
 */
public interface ValidationResults {

  /**
   * @return true if there are {@link ValidationResult} of type {@link ValidationResultType#ERROR}
   */
  boolean hasErrors();

  /**
   * @return the count of {@link ValidationResult} of type {@link ValidationResultType#ERROR}
   */
  int getErrorCount();

  /**
   * @return the count of {@link ValidationResult} of type {@link ValidationResultType#WARNING}
   */
  int getWarinigCount();

  /**
   * @return the individual results of the validation grouped by element.
   */
  Map<ModelElementInstance, List<ValidationResult>> getResults();

  /**
   * Utility method to print out a summary of the validation results.
   *
   * @param writer a {@link StringWriter} to which the result should be printed
   * @param printer formatter for printing elements and validation results
   */
  void write(StringWriter writer, ValidationResultFormatter printer);

}
