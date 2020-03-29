/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.dmn.engine.test;


import static com.pangubpm.dmn.engine.test.asserts.DmnEngineTestAssertions.assertThat;

import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;

import com.pangubpm.bpm.dmn.engine.DmnDecision;
import com.pangubpm.bpm.dmn.engine.DmnDecisionTableResult;
import com.pangubpm.bpm.dmn.engine.DmnEngine;
import com.pangubpm.bpm.dmn.engine.DmnEngineConfiguration;
import com.pangubpm.bpm.engine.variable.VariableMap;
import com.pangubpm.bpm.engine.variable.Variables;
import com.pangubpm.commons.utils.IoUtil;

import com.pangubpm.dmn.engine.test.asserts.DmnDecisionTableResultAssert;

public abstract class DmnEngineTest {

  @Rule
  public DmnEngineTestRule dmnEngineRule = new DmnEngineTestRule(getDmnEngineConfiguration());

  public DmnEngine dmnEngine;
  public DmnDecision decision;
  public VariableMap variables;

  public DmnEngineConfiguration getDmnEngineConfiguration() {
    return null;
  }

  @Before
  public void initDmnEngine() {
    dmnEngine = dmnEngineRule.getDmnEngine();
  }

  @Before
  public void initDecision() {
    decision = dmnEngineRule.getDecision();
  }

  @Before
  public void initVariables() {
    variables = Variables.createVariables();
  }

  // parsing //////////////////////////////////////////////////////////////////

  public List<DmnDecision> parseDecisionsFromFile(String filename) {
    InputStream inputStream = IoUtil.fileAsStream(filename);
    return dmnEngine.parseDecisions(inputStream);
  }

  public DmnDecision parseDecisionFromFile(String decisionKey, String filename) {
    InputStream inputStream = IoUtil.fileAsStream(filename);
    return dmnEngine.parseDecision(decisionKey, inputStream);
  }

  // evaluations //////////////////////////////////////////////////////////////

  public DmnDecisionTableResult evaluateDecisionTable() {
    return dmnEngine.evaluateDecisionTable(decision, variables);
  }

  // assertions ///////////////////////////////////////////////////////////////

  public DmnDecisionTableResultAssert assertThatDecisionTableResult() {
    DmnDecisionTableResult results = evaluateDecisionTable();
    return assertThat(results);
  }

}
