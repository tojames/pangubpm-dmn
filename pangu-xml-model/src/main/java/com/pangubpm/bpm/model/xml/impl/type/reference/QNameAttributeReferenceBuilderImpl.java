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

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class QNameAttributeReferenceBuilderImpl<T extends ModelElementInstance> extends AttributeReferenceBuilderImpl<T> {

  /**
   * Create a new {@link AttributeReferenceBuilderImpl} from the reference source attribute
   * to the reference target model element instance
   *
   * @param referenceSourceAttribute the reference source attribute
   * @param referenceTargetElement   the reference target model element instance
   */
  public QNameAttributeReferenceBuilderImpl(AttributeImpl<String> referenceSourceAttribute, Class<T> referenceTargetElement) {
    super(referenceSourceAttribute, referenceTargetElement);
    this.attributeReferenceImpl = new QNameAttributeReferenceImpl<T>(referenceSourceAttribute);
  }
}
