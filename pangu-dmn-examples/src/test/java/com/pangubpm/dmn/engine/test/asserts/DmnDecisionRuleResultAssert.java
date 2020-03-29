/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.dmn.engine.test.asserts;

import org.assertj.core.api.AbstractMapAssert;

import com.pangubpm.bpm.dmn.engine.DmnDecisionRuleResult;
import com.pangubpm.bpm.engine.variable.value.TypedValue;

public class DmnDecisionRuleResultAssert extends AbstractMapAssert<DmnDecisionRuleResultAssert, DmnDecisionRuleResult, String, Object> {

  public DmnDecisionRuleResultAssert(DmnDecisionRuleResult decisionRuleResult) {
    super(decisionRuleResult, DmnDecisionRuleResultAssert.class);
  }

  public DmnDecisionRuleResultAssert hasSingleEntry(Object value) {
    hasSize(1);
    containsValue(value);

    return this;
  }

  public DmnDecisionRuleResultAssert hasSingleEntryTyped(TypedValue value) {
    hasSize(1);

    TypedValue actualValue = actual.getSingleEntryTyped();
    failIfTypedValuesAreNotEqual(value, actualValue);

    return this;
  }

  protected void failIfTypedValuesAreNotEqual(TypedValue expectedValue, TypedValue actualValue) {
    if (actualValue == null && expectedValue != null) {
      failWithMessage("Expected value to be '%s' but was null", expectedValue);
    }
    else if (actualValue != null && !actualValue.equals(expectedValue)) {
      failWithMessage("Expected typed value to be '%s' but was '%s'", expectedValue, actualValue);
    }
  }

}
