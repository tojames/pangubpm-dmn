/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.impl.util.QName;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.child.ChildElement;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class QNameElementReferenceImpl<Target extends ModelElementInstance, Source extends ModelElementInstance> extends ElementReferenceImpl<Target,Source> {

  public QNameElementReferenceImpl(ChildElement<Source> referenceSourceCollection) {
    super(referenceSourceCollection);
  }

  @Override
  public String getReferenceIdentifier(ModelElementInstance referenceSourceElement) {
    String identifier = super.getReferenceIdentifier(referenceSourceElement);
    if (identifier != null) {
      QName qName = QName.parseQName(identifier);
      return qName.getLocalName();
    }
    else {
      return null;
    }
  }
}
