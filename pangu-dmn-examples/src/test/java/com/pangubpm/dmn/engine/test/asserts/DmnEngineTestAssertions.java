/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.dmn.engine.test.asserts;

import org.assertj.core.api.Assertions;

import com.pangubpm.bpm.dmn.engine.DmnDecisionTableResult;

public class DmnEngineTestAssertions extends Assertions {

  public static DmnDecisionTableResultAssert assertThat(DmnDecisionTableResult dmnDecisionTableResult) {
    return new DmnDecisionTableResultAssert(dmnDecisionTableResult);
  }

}
