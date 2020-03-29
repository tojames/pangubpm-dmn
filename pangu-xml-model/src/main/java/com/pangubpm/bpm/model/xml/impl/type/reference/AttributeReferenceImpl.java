/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.impl.type.attribute.AttributeImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.attribute.Attribute;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReference;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class AttributeReferenceImpl<T extends ModelElementInstance> extends ReferenceImpl<T> implements AttributeReference<T> {

  protected final AttributeImpl<String> referenceSourceAttribute;

  public AttributeReferenceImpl(AttributeImpl<String> referenceSourceAttribute) {
    this.referenceSourceAttribute = referenceSourceAttribute;
  }

  public String getReferenceIdentifier(ModelElementInstance referenceSourceElement) {
    return referenceSourceAttribute.getValue(referenceSourceElement);
  }

  protected void setReferenceIdentifier(ModelElementInstance referenceSourceElement, String referenceIdentifier) {
    referenceSourceAttribute.setValue(referenceSourceElement, referenceIdentifier);
  }

  /**
   * Get the reference source attribute
   *
   * @return the reference source attribute
   */
  public Attribute<String> getReferenceSourceAttribute() {
    return referenceSourceAttribute;
  }

  public ModelElementType getReferenceSourceElementType() {
    return referenceSourceAttribute.getOwningElementType();
  }

  protected void updateReference(ModelElementInstance referenceSourceElement, String oldIdentifier, String newIdentifier) {
    String referencingAttributeValue = getReferenceIdentifier(referenceSourceElement);
    if(oldIdentifier != null && oldIdentifier.equals(referencingAttributeValue)) {
      setReferenceIdentifier(referenceSourceElement, newIdentifier);
    }
  }

  protected void removeReference(ModelElementInstance referenceSourceElement, ModelElementInstance referenceTargetElement) {
    referenceSourceAttribute.removeAttribute(referenceSourceElement);
  }

}
