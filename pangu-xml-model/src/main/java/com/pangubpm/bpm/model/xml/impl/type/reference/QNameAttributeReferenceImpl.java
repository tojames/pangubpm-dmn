/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.impl.type.attribute.AttributeImpl;
import com.pangubpm.bpm.model.xml.impl.util.QName;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

/**
 * @author pangubpm( pangubpm@139.com )
 *
 */
public class QNameAttributeReferenceImpl<T extends ModelElementInstance> extends AttributeReferenceImpl<T> {

  /**
   * Create a new QName reference outgoing from the reference source attribute
   *
   * @param referenceSourceAttribute the reference source attribute
   */
  public QNameAttributeReferenceImpl(AttributeImpl<String> referenceSourceAttribute) {
    super(referenceSourceAttribute);
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
