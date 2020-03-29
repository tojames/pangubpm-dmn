/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.validation;

import java.util.Collection;

import com.pangubpm.bpm.model.xml.impl.ModelInstanceImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.validation.ModelElementValidator;
import com.pangubpm.bpm.model.xml.validation.ValidationResults;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ModelInstanceValidator {

  protected ModelInstanceImpl modelInstanceImpl;
  protected Collection<ModelElementValidator<?>> validators;

  public ModelInstanceValidator(ModelInstanceImpl modelInstanceImpl, Collection<ModelElementValidator<?>> validators) {
    this.modelInstanceImpl = modelInstanceImpl;
    this.validators = validators;
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public ValidationResults validate() {

    ValidationResultsCollectorImpl resultsCollector = new ValidationResultsCollectorImpl();

    for (ModelElementValidator validator : validators) {

      Class<? extends ModelElementInstance> elementType = validator.getElementType();
      Collection<? extends ModelElementInstance> modelElementsByType = modelInstanceImpl.getModelElementsByType(elementType);

      for (ModelElementInstance element : modelElementsByType) {

        resultsCollector.setCurrentElement(element);

        try {
          validator.validate(element, resultsCollector);
        }
        catch(RuntimeException e) {
          throw new RuntimeException("Validator " + validator + " threw an exception while validating "+element, e);
        }
      }

    }

    return resultsCollector.getResults();
  }

}
