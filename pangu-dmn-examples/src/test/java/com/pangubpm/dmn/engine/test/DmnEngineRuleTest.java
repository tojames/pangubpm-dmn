/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.dmn.engine.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import com.pangubpm.bpm.dmn.engine.DmnEngine;
import com.pangubpm.bpm.dmn.engine.DmnEngineConfiguration;
import com.pangubpm.bpm.dmn.engine.impl.DefaultDmnEngine;
import com.pangubpm.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import com.pangubpm.bpm.dmn.engine.test.DmnEngineRule;

public class DmnEngineRuleTest {

  @Rule
  public DmnEngineRule engineRule = new DmnEngineRule();

  @Rule
  public DmnEngineRule nullEngineRule = new DmnEngineRule(null);

  @Rule
  public DmnEngineRule customEngineRule = new DmnEngineRule(initConfiguration());

  public DmnEngineConfiguration customConfiguration;

  public DmnEngineConfiguration initConfiguration() {
    customConfiguration = new DefaultDmnEngineConfiguration();
    return customConfiguration;
  }

  @Test
  public void shouldCreateDefaultDmnEngineWithoutConfiguration() {
    DmnEngine dmnEngine = engineRule.getDmnEngine();
    assertThat(dmnEngine)
      .isInstanceOf(DefaultDmnEngine.class)
      .isNotNull();
  }

  @Test
  public void shouldCreateDefaultDmnEngineWithNullConfiguration() {
    DmnEngine dmnEngine = nullEngineRule.getDmnEngine();
    assertThat(dmnEngine)
      .isInstanceOf(DefaultDmnEngine.class)
      .isNotNull();
  }

  @Test
  public void shouldCreateEngineFromCustomConfiguration() {
    DmnEngine dmnEngine = customEngineRule.getDmnEngine();
    assertThat(dmnEngine)
      .isNotNull();

    assertThat(dmnEngine.getConfiguration())
      .isEqualTo(customConfiguration);
  }

}
