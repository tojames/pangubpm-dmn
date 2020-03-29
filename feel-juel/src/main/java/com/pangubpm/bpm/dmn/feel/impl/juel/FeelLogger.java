/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.dmn.feel.impl.juel;

import com.pangubpm.commons.logging.BaseLogger;

public class FeelLogger extends BaseLogger {

  public static final String PROJECT_CODE = "FEEL";
  public static final String PROJECT_LOGGER= "com.pangubpm.bpm.dmn.feel";

  public static FeelEngineLogger ENGINE_LOGGER = createLogger(FeelEngineLogger.class, PROJECT_CODE, PROJECT_LOGGER, "01");

}
