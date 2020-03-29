/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.validation;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.validation.ValidationResult;
import com.pangubpm.bpm.model.xml.validation.ValidationResultType;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ModelValidationResultImpl implements ValidationResult {

  protected int code;
  protected ValidationResultType type;
  protected ModelElementInstance element;
  protected String message;

  public ModelValidationResultImpl(ModelElementInstance element, ValidationResultType type, int code, String message) {
    this.element = element;
    this.type = type;
    this.code = code;
    this.message = message;
  }

  @Override
  public ValidationResultType getType() {
    return type;
  }

  @Override
  public ModelElementInstance getElement() {
    return element;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public int getCode() {
    return code;
  }

}
