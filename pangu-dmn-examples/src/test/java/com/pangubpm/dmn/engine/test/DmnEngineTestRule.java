/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.dmn.engine.test;

import java.io.InputStream;
import java.util.List;

import org.junit.runner.Description;

import com.pangubpm.bpm.dmn.engine.DmnDecision;
import com.pangubpm.bpm.dmn.engine.DmnEngineConfiguration;
import com.pangubpm.bpm.dmn.engine.test.DmnEngineRule;
import com.pangubpm.commons.utils.IoUtil;

/**
 * JUnit test rule for internal unit tests. Uses The
 * {@link DecisionResource} annotation to load decisions
 * before tests.
 */
public class DmnEngineTestRule extends DmnEngineRule {

  public static final String DMN_SUFFIX = "dmn";

  protected DmnDecision decision;

  public DmnEngineTestRule() {
    super();
  }

  public DmnEngineTestRule(DmnEngineConfiguration dmnEngineConfiguration) {
    super(dmnEngineConfiguration);
  }

  public DmnDecision getDecision() {
    return decision;
  }

  @Override
  protected void starting(Description description) {
    super.starting(description);

    decision = loadDecision(description);
  }

  protected DmnDecision loadDecision(Description description) {
    DecisionResource decisionResource = description.getAnnotation(DecisionResource.class);

    if(decisionResource != null) {

      String resourcePath = decisionResource.resource();

      resourcePath = expandResourcePath(description, resourcePath);

      InputStream inputStream = IoUtil.fileAsStream(resourcePath);

      String decisionKey = decisionResource.decisionKey();

      if (decisionKey == null || decisionKey.isEmpty()) {
        List<DmnDecision> decisions = dmnEngine.parseDecisions(inputStream);
        if (!decisions.isEmpty()) {
          return decisions.get(0);
        }
        else {
          return null;
        }
      } else {
        return dmnEngine.parseDecision(decisionKey, inputStream);
      }
    }
    else {
      return null;
    }
  }

  protected String expandResourcePath(Description description, String resourcePath) {
    if (resourcePath.contains("/")) {
      // already expanded path
      return resourcePath;
    }
    else {
      Class<?> testClass = description.getTestClass();
      if (resourcePath.isEmpty()) {
        // use test class and method name as resource file name
        return testClass.getName().replace(".", "/") + "." + description.getMethodName() + "." + DMN_SUFFIX;
      }
      else {
        // use test class location as resource location
        return testClass.getPackage().getName().replace(".", "/") + "/" + resourcePath;
      }
    }
  }

}
