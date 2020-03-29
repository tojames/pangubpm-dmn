/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.child;

import com.pangubpm.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.impl.util.ModelUtil;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.child.ChildElement;

/**
 * Represents a single Child Element (ie. maxOccurs = 1);
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ChildElementImpl<T extends ModelElementInstance> extends ChildElementCollectionImpl<T> implements ChildElement<T> {

  public ChildElementImpl(Class<T> childElementTypeChild, ModelElementTypeImpl parentElementType) {
    super(childElementTypeChild, parentElementType);
    this.maxOccurs = 1;
  }

  /** the add operation replaces the child */
  private void performAddOperation(ModelElementInstanceImpl modelElement, T e) {
    modelElement.setUniqueChildElementByNameNs(e);
  }

  public void setChild(ModelElementInstance element, T newChildElement) {
    performAddOperation((ModelElementInstanceImpl) element, newChildElement);
  }

  @SuppressWarnings("unchecked")
  public T getChild(ModelElementInstance element) {
    ModelElementInstanceImpl elementInstanceImpl = (ModelElementInstanceImpl)element;

    ModelElementInstance childElement = elementInstanceImpl.getUniqueChildElementByType(childElementTypeClass);
    if(childElement != null) {
      ModelUtil.ensureInstanceOf(childElement, childElementTypeClass);
      return (T) childElement;
    } else {
      return null;
    }
  }

  public boolean removeChild(ModelElementInstance element) {
    ModelElementInstanceImpl childElement = (ModelElementInstanceImpl) getChild(element);
    ModelElementInstanceImpl elementInstanceImpl = (ModelElementInstanceImpl) element;
    return elementInstanceImpl.removeChildElement(childElement);
  }
}
