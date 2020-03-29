/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.validation.ValidationResult;
import com.pangubpm.bpm.model.xml.validation.ValidationResultType;
import com.pangubpm.bpm.model.xml.validation.ValidationResults;
import com.pangubpm.bpm.model.xml.validation.ValidationResultCollector;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ValidationResultsCollectorImpl implements ValidationResultCollector {

  protected ModelElementInstance currentElement;

  protected Map<ModelElementInstance, List<ValidationResult>> collectedResults = new HashMap<ModelElementInstance, List<ValidationResult>>();

  protected int errorCount = 0;
  protected int warningCount = 0;

  @Override
  public void addError(int code, String message) {
    resultsForCurrentElement()
      .add(new ModelValidationResultImpl(currentElement, ValidationResultType.ERROR, code, message));

    ++errorCount;
  }

  @Override
  public void addWarning(int code, String message) {
    resultsForCurrentElement()
      .add(new ModelValidationResultImpl(currentElement, ValidationResultType.WARNING, code, message));

    ++warningCount;
  }

  public void setCurrentElement(ModelElementInstance currentElement) {
    this.currentElement = currentElement;
  }

  public ValidationResults getResults() {
    return new ModelValidationResultsImpl(collectedResults, errorCount, warningCount);
  }

  protected List<ValidationResult> resultsForCurrentElement() {
    List<ValidationResult> resultsByElement = collectedResults.get(currentElement);

    if(resultsByElement == null) {
      resultsByElement = new ArrayList<ValidationResult>();
      collectedResults.put(currentElement, resultsByElement);
    }
    return resultsByElement;
  }

}
