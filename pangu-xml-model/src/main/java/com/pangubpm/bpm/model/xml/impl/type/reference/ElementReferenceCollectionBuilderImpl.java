/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.impl.type.attribute.AttributeImpl;
import com.pangubpm.bpm.model.xml.impl.type.child.ChildElementCollectionImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceCollection;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceCollectionBuilder;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class ElementReferenceCollectionBuilderImpl<Target extends ModelElementInstance, Source extends ModelElementInstance> implements
  ElementReferenceCollectionBuilder<Target, Source> {

  private final Class<Source> childElementType;
  private final Class<Target> referenceTargetClass;
  protected ElementReferenceCollectionImpl<Target, Source> elementReferenceCollectionImpl;

  public ElementReferenceCollectionBuilderImpl(Class<Source> childElementType, Class<Target> referenceTargetClass, ChildElementCollectionImpl<Source> collection) {
    this.childElementType = childElementType;
    this.referenceTargetClass = referenceTargetClass;
    this.elementReferenceCollectionImpl = new ElementReferenceCollectionImpl<Target, Source>(collection);
  }

  public ElementReferenceCollection<Target, Source> build() {
    return elementReferenceCollectionImpl;
  }

  @SuppressWarnings("unchecked")
  public void performModelBuild(Model model) {
    ModelElementTypeImpl referenceTargetType = (ModelElementTypeImpl) model.getType(referenceTargetClass);
    ModelElementTypeImpl referenceSourceType = (ModelElementTypeImpl) model.getType(childElementType);
    elementReferenceCollectionImpl.setReferenceTargetElementType(referenceTargetType);
    elementReferenceCollectionImpl.setReferenceSourceElementType(referenceSourceType);

    // the referenced attribute may be declared on a base type of the referenced type.
    AttributeImpl<String> idAttribute = (AttributeImpl<String>) referenceTargetType.getAttribute("id");
    if (idAttribute != null) {
      idAttribute.registerIncoming(elementReferenceCollectionImpl);
      elementReferenceCollectionImpl.setReferenceTargetAttribute(idAttribute);
    } else {
      throw new ModelException("Unable to find id attribute of " + referenceTargetClass);
    }
  }
}
