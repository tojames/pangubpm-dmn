/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.commons.utils;

import com.pangubpm.commons.logging.BaseLogger;

/**
 * @author pangubpm.
 * @Email pangubpm@139.com
 */
public class UtilsLogger extends BaseLogger {

  public final static String PROJECT_CODE = "UTILS";

  public final static IoUtilLogger IO_UTIL_LOGGER = BaseLogger.createLogger(IoUtilLogger.class, PROJECT_CODE, "com.pangubpm.commons.utils.io", "01");
  public final static EnsureUtilLogger ENSURE_UTIL_LOGGER = BaseLogger.createLogger(EnsureUtilLogger.class, PROJECT_CODE, "com.pangubpm.commons.utils.ensure", "02");
}
