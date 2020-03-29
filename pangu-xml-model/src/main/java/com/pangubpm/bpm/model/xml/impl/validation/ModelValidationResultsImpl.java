/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.validation;

import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.validation.ValidationResultFormatter;
import com.pangubpm.bpm.model.xml.validation.ValidationResult;
import com.pangubpm.bpm.model.xml.validation.ValidationResults;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ModelValidationResultsImpl implements ValidationResults {

  protected Map<ModelElementInstance, List<ValidationResult>> collectedResults;

  protected int errorCount;
  protected int warningCount;

  public ModelValidationResultsImpl(Map<ModelElementInstance, List<ValidationResult>> collectedResults, int errorCount, int warningCount) {
    this.collectedResults = collectedResults;
    this.errorCount = errorCount;
    this.warningCount = warningCount;
  }

  @Override
  public boolean hasErrors() {
    return errorCount > 0;
  }

  @Override
  public int getErrorCount() {
    return errorCount;
  }

  @Override
  public int getWarinigCount() {
    return warningCount;
  }

  @Override
  public void write(StringWriter writer, ValidationResultFormatter formatter) {
    for (Entry<ModelElementInstance, List<ValidationResult>> entry : collectedResults.entrySet()) {

      ModelElementInstance element = entry.getKey();
      List<ValidationResult> results = entry.getValue();

      formatter.formatElement(writer, element);

      for (ValidationResult result : results) {
        formatter.formatResult(writer, result);
      }
    }
  }

  @Override
  public Map<ModelElementInstance, List<ValidationResult>> getResults() {
    return collectedResults;
  }

}
