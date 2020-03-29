/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import com.pangubpm.bpm.model.xml.impl.util.StringUtil;
import com.pangubpm.bpm.model.xml.instance.DomDocument;
import com.pangubpm.bpm.model.xml.instance.DomElement;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollection;

public class IdsElementReferenceCollectionImpl<Target extends ModelElementInstance, Source extends ModelElementInstance> extends ElementReferenceCollectionImpl<Target, Source> {

  protected String separator = " ";

  public IdsElementReferenceCollectionImpl(ChildElementCollection<Source> referenceSourceCollection) {
    super(referenceSourceCollection);
  }

  protected List<String> getReferenceIdentifiers(ModelElementInstance referenceSourceElement) {
    String referenceIdentifiers = getReferenceIdentifier(referenceSourceElement);
    return StringUtil.splitListBySeparator(referenceIdentifiers, separator);
  }

  protected void setReferenceIdentifiers(ModelElementInstance referenceSourceElement, List<String> referenceIdentifiers) {
    String referenceIdentifier = StringUtil.joinList(referenceIdentifiers, separator);
    referenceSourceElement.setTextContent(referenceIdentifier);
  }

  @Override
  protected Collection<DomElement> getView(ModelElementInstanceImpl referenceSourceParentElement) {
    DomDocument document = referenceSourceParentElement.getModelInstance().getDocument();
    Collection<Source> referenceSourceElements = getReferenceSourceCollection().get(referenceSourceParentElement);
    Collection<DomElement> referenceTargetElements = new ArrayList<DomElement>();
    for (Source referenceSourceElement : referenceSourceElements) {
      List<String> identifiers = getReferenceIdentifiers(referenceSourceElement);
      for (String identifier : identifiers) {
        DomElement referenceTargetElement = document.getElementById(identifier);
        if (referenceTargetElement != null) {
          referenceTargetElements.add(referenceTargetElement);
        }
        else {
          throw new ModelException("Unable to find a model element instance for id " + identifier);
        }
      }
    }
    return referenceTargetElements;
  }

  @Override
  protected void updateReference(ModelElementInstance referenceSourceElement, String oldIdentifier, String newIdentifier) {
    List<String> referenceIdentifiers = getReferenceIdentifiers(referenceSourceElement);
    if (referenceIdentifiers.contains(oldIdentifier)) {
      int index = referenceIdentifiers.indexOf(oldIdentifier);
      referenceIdentifiers.remove(oldIdentifier);
      referenceIdentifiers.add(index, newIdentifier);
      setReferenceIdentifiers(referenceSourceElement, referenceIdentifiers);
    }
  }

  @Override
  public void referencedElementRemoved(ModelElementInstance referenceTargetElement, Object referenceIdentifier) {
    for (ModelElementInstance referenceSourceElement : findReferenceSourceElements(referenceTargetElement)) {
      List<String> referenceIdentifiers = getReferenceIdentifiers(referenceSourceElement);
      if (referenceIdentifiers.contains(referenceIdentifier)) {
        if (referenceIdentifiers.size() == 1) {
          // remove whole element
          removeReference(referenceSourceElement, referenceTargetElement);
        }
        else {
          // remove only single identifier
          referenceIdentifiers.remove(referenceIdentifier);
          setReferenceIdentifiers(referenceSourceElement, referenceIdentifiers);
        }
      }
    }
  }

}
