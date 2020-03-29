/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.ModelReferenceException;
import com.pangubpm.bpm.model.xml.impl.ModelInstanceImpl;
import com.pangubpm.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.child.ChildElement;
import com.pangubpm.bpm.model.xml.type.reference.ElementReference;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class ElementReferenceImpl<Target extends ModelElementInstance, Source extends ModelElementInstance>  extends ElementReferenceCollectionImpl<Target,Source> implements
  ElementReference<Target, Source> {


  public ElementReferenceImpl(ChildElement<Source> referenceSourceCollection) {
    super(referenceSourceCollection);
  }

  private ChildElement<Source> getReferenceSourceChild() {
    return (ChildElement<Source>) getReferenceSourceCollection();
  }

  public Source getReferenceSource(ModelElementInstance referenceSourceParent) {
    return getReferenceSourceChild().getChild(referenceSourceParent);
  }

  private void setReferenceSource(ModelElementInstance referenceSourceParent, Source referenceSource) {
    getReferenceSourceChild().setChild(referenceSourceParent, referenceSource);
  }

  @SuppressWarnings("unchecked")
  public Target getReferenceTargetElement(ModelElementInstanceImpl referenceSourceParentElement) {
    Source referenceSource = getReferenceSource(referenceSourceParentElement);
    if (referenceSource != null) {
      String identifier = getReferenceIdentifier(referenceSource);
      ModelElementInstance referenceTargetElement = referenceSourceParentElement.getModelInstance().getModelElementById(identifier);
      if (referenceTargetElement != null) {
        return (Target) referenceTargetElement;
      }
      else {
        throw new ModelException("Unable to find a model element instance for id " + identifier);
      }
    }
    else {
      return null;
    }
  }

  public void setReferenceTargetElement(ModelElementInstanceImpl referenceSourceParentElement, Target referenceTargetElement) {
    ModelInstanceImpl modelInstance = referenceSourceParentElement.getModelInstance();
    String identifier = referenceTargetAttribute.getValue(referenceTargetElement);
    ModelElementInstance existingElement = modelInstance.getModelElementById(identifier);

    if (existingElement == null || !existingElement.equals(referenceTargetElement)) {
      throw new ModelReferenceException("Cannot create reference to model element " + referenceTargetElement
        +": element is not part of model. Please connect element to the model first.");
    }
    else {
      Source referenceSourceElement = modelInstance.newInstance(getReferenceSourceElementType());
      setReferenceSource(referenceSourceParentElement, referenceSourceElement);
      setReferenceIdentifier(referenceSourceElement, identifier);
    }
  }

  public void clearReferenceTargetElement(ModelElementInstanceImpl referenceSourceParentElement) {
    getReferenceSourceChild().removeChild(referenceSourceParentElement);
  }
}
