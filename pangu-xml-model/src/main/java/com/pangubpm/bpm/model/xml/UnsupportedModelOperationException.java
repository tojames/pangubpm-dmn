/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml;

import java.util.Collection;

/**
 * Exception thrown if a user attempts to perform an unsupported
 * model operation. Example: call {@link Collection#add(Object)} on an
 * immutable collection.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class UnsupportedModelOperationException extends UnsupportedOperationException {

  private static final long serialVersionUID = 1L;

  public UnsupportedModelOperationException(String operationName, String reason) {
    super("The operation " + operationName + " is unsupported: " + reason + ".");
  }

}
