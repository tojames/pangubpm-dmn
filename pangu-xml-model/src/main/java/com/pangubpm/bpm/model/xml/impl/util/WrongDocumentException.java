/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.util;

import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.instance.DomDocument;
import org.w3c.dom.Node;

/**
 * <p>Thrown when a Model Element is added to the wrong document</p>
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class WrongDocumentException extends ModelException {

  private static final long serialVersionUID = 1L;

  public WrongDocumentException(Node nodeToAdd, DomDocument targetDocument) {
    super("Cannot add attribute '"+ nodeToAdd +"' to document '" + targetDocument + "' not created by document.");
  }

}
