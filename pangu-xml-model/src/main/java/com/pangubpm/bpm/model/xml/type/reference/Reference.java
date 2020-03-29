/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.reference;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.attribute.Attribute;

import java.util.Collection;

/**
 *
 * @author pangubpm( pangubpm@139.com )
 *
 * @param <T> the type of the referenced element
 */
public interface Reference<T extends ModelElementInstance> {

  /**
   * Get the reference identifier which is set in the reference source
   *
   * @param referenceSourceElement the reference source model element instance
   * @return the reference identifier
   */
  String getReferenceIdentifier(ModelElementInstance referenceSourceElement);

  T getReferenceTargetElement(ModelElementInstance modelElement);

  void setReferenceTargetElement(ModelElementInstance referenceSourceElement, T referenceTargetElement);

  Attribute<String> getReferenceTargetAttribute();

  /**
   * Find all reference source element instances of the reference target model element instance
   *
   * @param referenceTargetElement the reference target model element instance
   * @return the collection of all reference source element instances
   */
  Collection<ModelElementInstance> findReferenceSourceElements(ModelElementInstance referenceTargetElement);

  /** @return the {@link ModelElementType} of the source element.
   * */
  ModelElementType getReferenceSourceElementType();
}
