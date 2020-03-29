/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.dmn.engine.util;

import static com.pangubpm.dmn.engine.test.asserts.DmnEngineTestAssertions.assertThat;

import java.io.InputStream;

import com.pangubpm.bpm.dmn.engine.DmnDecision;
import com.pangubpm.bpm.dmn.engine.DmnDecisionTableResult;
import com.pangubpm.bpm.dmn.engine.DmnEngine;
import com.pangubpm.bpm.engine.variable.VariableMap;
import com.pangubpm.bpm.engine.variable.Variables;
import com.pangubpm.commons.utils.IoUtil;

public final class DmnExampleVerifier {

  public static final String EXAMPLE_DMN = "com/pangubpm/bpm/dmn/engine/api/Example.dmn";

  public static void assertExample(DmnEngine engine) {
    InputStream inputStream = IoUtil.fileAsStream(EXAMPLE_DMN);
    DmnDecision decision = engine.parseDecision("decision", inputStream);
    assertExample(engine, decision);
  }

  public static void assertExample(DmnEngine engine, DmnDecision decision) {
    VariableMap variables = Variables.createVariables();
    variables.put("status", "bronze");
    variables.put("sum", 200);

    DmnDecisionTableResult results = engine.evaluateDecisionTable(decision, variables);
    assertThat(results)
      .hasSingleResult()
      .containsKeys("result", "reason")
      .containsEntry("result", "notok")
      .containsEntry("reason", "work on your status first, as bronze you're not going to get anything");

    variables.put("status", "silver");

    results = engine.evaluateDecisionTable(decision, variables);
    assertThat(results)
      .hasSingleResult()
      .containsKeys("result", "reason")
      .containsEntry("result", "ok")
      .containsEntry("reason", "you little fish will get what you want");

    variables.put("sum", 1200);

    results = engine.evaluateDecisionTable(decision, variables);
    assertThat(results)
      .hasSingleResult()
      .containsKeys("result", "reason")
      .containsEntry("result", "notok")
      .containsEntry("reason", "you took too much man, you took too much!");

    variables.put("status", "gold");
    variables.put("sum", 200);

    results = engine.evaluateDecisionTable(decision, variables);
    assertThat(results)
      .hasSingleResult()
      .containsKeys("result", "reason")
      .containsEntry("result", "ok")
      .containsEntry("reason", "you get anything you want");
  }

}
