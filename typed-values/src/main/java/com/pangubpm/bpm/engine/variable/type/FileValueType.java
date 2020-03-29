/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.engine.variable.type;

/**
 * @author pangubpm( pangubpm@139.com )
 * @since 7.4
 */
public interface FileValueType extends ValueType {

  /**
   * Identifies the file's name as specified on value creation.
   */
  String VALUE_INFO_FILE_NAME = "filename";

  /**
   * Identifies the file's mime type as specified on value creation.
   */
  String VALUE_INFO_FILE_MIME_TYPE = "mimeType";

  /**
   * Identifies the file's encoding as specified on value creation.
   */
  String VALUE_INFO_FILE_ENCODING = "encoding";

}
