/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.validation;

import java.io.StringWriter;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

/**
 * SPI which can be implemented to print out a summary of a validation result.
 * See {@link ValidationResults#write(StringWriter, ValidationResultFormatter)}
 *
 * @author pangubpm(pangubpm@139.com)
 * @since 7.6
 */
public interface ValidationResultFormatter {

  /**
   * formats an element in the summary
   *
   * @param writer the writer
   * @param element the element to write
   */
  void formatElement(StringWriter writer, ModelElementInstance element);

  /**
   * formats a validation result
   *
   * @param writer the writer
   * @param result the result to format
   */
  void formatResult(StringWriter writer, ValidationResult result);

}
