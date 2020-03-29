/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.dmn.engine.test.asserts;

import org.assertj.core.api.AbstractListAssert;

import com.pangubpm.bpm.dmn.engine.DmnDecisionRuleResult;
import com.pangubpm.bpm.dmn.engine.DmnDecisionTableResult;

public class DmnDecisionTableResultAssert extends AbstractListAssert<DmnDecisionTableResultAssert, DmnDecisionTableResult, DmnDecisionRuleResult> {

  public DmnDecisionTableResultAssert(DmnDecisionTableResult decisionTableResult) {
    super(decisionTableResult, DmnDecisionTableResultAssert.class);
  }

  public DmnDecisionRuleResultAssert hasSingleResult() {
    hasSize(1);

    DmnDecisionRuleResult singleResult = actual.getSingleResult();

    return new DmnDecisionRuleResultAssert(singleResult);
  }

}
