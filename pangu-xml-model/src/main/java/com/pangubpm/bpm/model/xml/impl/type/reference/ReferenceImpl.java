/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import java.util.Collection;
import java.util.Collections;

import com.pangubpm.bpm.model.xml.ModelInstance;
import com.pangubpm.bpm.model.xml.ModelReferenceException;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.impl.type.attribute.AttributeImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.attribute.Attribute;
import com.pangubpm.bpm.model.xml.type.reference.Reference;

/**
 * @author pangubpm( pangubpm@139.com )
 *
 */
public abstract class ReferenceImpl<T extends ModelElementInstance> implements Reference<T> {

  protected AttributeImpl<String> referenceTargetAttribute;

  /** the actual type, may be different (a subtype of) {@link AttributeImpl#getOwningElementType()} */
  private ModelElementTypeImpl referenceTargetElementType;


  /**
   * Set the reference identifier in the reference source
   *
   * @param referenceSourceElement the reference source model element instance
   * @param referenceIdentifier the new reference identifier
   */
  protected abstract void setReferenceIdentifier(ModelElementInstance referenceSourceElement, String referenceIdentifier);

   /**
   * Get the reference target model element instance
   *
   * @param referenceSourceElement the reference source model element instance
   * @return the reference target model element instance or null if not set
   */
  @SuppressWarnings("unchecked")
  public T getReferenceTargetElement(ModelElementInstance referenceSourceElement) {
    String identifier = getReferenceIdentifier(referenceSourceElement);
    ModelElementInstance referenceTargetElement = referenceSourceElement.getModelInstance().getModelElementById(identifier);
    if (referenceTargetElement != null) {
      try {
        return (T) referenceTargetElement;

      } catch(ClassCastException e) {
        throw new ModelReferenceException("Element " + referenceSourceElement + " references element " + referenceTargetElement + " of wrong type. "
          + "Expecting " + referenceTargetAttribute.getOwningElementType() + " got " + referenceTargetElement.getElementType());
      }
    }
    else {
      return null;
    }
  }

  /**
   * Set the reference target model element instance
   *
   * @param referenceSourceElement the reference source model element instance
   * @param referenceTargetElement the reference target model element instance
   * @throws ModelReferenceException if element is not already added to the model
   */
  public void setReferenceTargetElement(ModelElementInstance referenceSourceElement, T referenceTargetElement) {
    ModelInstance modelInstance = referenceSourceElement.getModelInstance();
    String referenceTargetIdentifier = referenceTargetAttribute.getValue(referenceTargetElement);
    ModelElementInstance existingElement = modelInstance.getModelElementById(referenceTargetIdentifier);

    if(existingElement == null || !existingElement.equals(referenceTargetElement)) {
      throw new ModelReferenceException("Cannot create reference to model element " + referenceTargetElement
          +": element is not part of model. Please connect element to the model first.");
    } else {
      setReferenceIdentifier(referenceSourceElement, referenceTargetIdentifier);
    }
  }

  /**
   * Set the reference target attribute
   *
   * @param referenceTargetAttribute the reference target string attribute
   */
  public void setReferenceTargetAttribute(AttributeImpl<String> referenceTargetAttribute) {
    this.referenceTargetAttribute = referenceTargetAttribute;
  }

  /**
   * Get the reference target attribute
   *
   * @return the reference target string attribute
   */
  public Attribute<String> getReferenceTargetAttribute() {
    return referenceTargetAttribute;
  }

  /**
   * Set the reference target model element type
   *
   * @param referenceTargetElementType the referenceTargetElementType to set
   */
  public void setReferenceTargetElementType(ModelElementTypeImpl referenceTargetElementType) {
    this.referenceTargetElementType = referenceTargetElementType;
  }

  public Collection<ModelElementInstance> findReferenceSourceElements(ModelElementInstance referenceTargetElement) {
    if(referenceTargetElementType.isBaseTypeOf(referenceTargetElement.getElementType())) {
      ModelElementType owningElementType = getReferenceSourceElementType();
      return referenceTargetElement.getModelInstance().getModelElementsByType(owningElementType);
    }
    else {
      return Collections.emptyList();
    }
  }

  /**
   * Update the reference identifier of the reference source model element instance
   *
   * @param referenceSourceElement the reference source model element instance
   * @param oldIdentifier the old reference identifier
   * @param newIdentifier the new reference identifier
   */
  protected abstract void updateReference(ModelElementInstance referenceSourceElement, String oldIdentifier, String newIdentifier);

  /**
   * Update the reference identifier
   *
   * @param referenceTargetElement the reference target model element instance
   * @param oldIdentifier the old reference identifier
   * @param newIdentifier the new reference identifier
   */
  public void referencedElementUpdated(ModelElementInstance referenceTargetElement, String oldIdentifier, String newIdentifier) {
    for (ModelElementInstance referenceSourceElement : findReferenceSourceElements(referenceTargetElement)) {
      updateReference(referenceSourceElement, oldIdentifier, newIdentifier);
    }
  }

  /**
   * Remove the reference in the reference source model element instance
   *
   * @param referenceSourceElement the reference source model element instance
   */
  protected abstract void removeReference(ModelElementInstance referenceSourceElement, ModelElementInstance referenceTargetElement);

  /**
   * Remove the reference if the target element is removed
   *
   * @param referenceTargetElement  the reference target model element instance, which is removed
   * @param referenceIdentifier  the identifier of the reference to filter reference source elements
   */
  public void referencedElementRemoved(ModelElementInstance referenceTargetElement, Object referenceIdentifier) {
    for (ModelElementInstance referenceSourceElement : findReferenceSourceElements(referenceTargetElement)) {
      if (referenceIdentifier.equals(getReferenceIdentifier(referenceSourceElement))) {
        removeReference(referenceSourceElement, referenceTargetElement);
      }
    }
  }

}
