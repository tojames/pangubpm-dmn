/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.validation;

import com.pangubpm.bpm.model.xml.ModelInstance;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

/**
 * A validator for model element instances.
 *
 * @see ModelInstance#validate(java.util.Collection)
 * @param <T> the type of the elements to validate.
 * @since 7.6
 */
public interface ModelElementValidator<T extends ModelElementInstance> {

  /**
   * <p>The type of the element this validator is applied to. The validator is applied to all
   * instances implementing this type.</p>
   *
   * <p>Example from BPMN: Assume the type returned is 'Task'. Then the validator is invoked for
   * all instances of task, including instances of 'ServiceTask', 'UserTask', ...</p>
   *
   * @return the type of the element this validator is applied to.
   */
  Class<T> getElementType();

  /**
   * Validate an element.
   *
   * @param element the element to validate
   * @param validationResultCollector object used to collect validation results for this element.
   */
  void validate(T element, ValidationResultCollector validationResultCollector);
}
