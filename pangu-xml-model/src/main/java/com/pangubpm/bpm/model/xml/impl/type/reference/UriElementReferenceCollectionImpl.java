/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollection;

public class UriElementReferenceCollectionImpl<Target extends ModelElementInstance, Source extends ModelElementInstance> extends ElementReferenceCollectionImpl<Target, Source> {

  public UriElementReferenceCollectionImpl(ChildElementCollection<Source> referenceSourceCollection) {
    super(referenceSourceCollection);
  }

  @Override
  public String getReferenceIdentifier(ModelElementInstance referenceSourceElement) {
    // TODO: implement something more robust (CAM-4028)
    String identifier = referenceSourceElement.getAttributeValue("href");
    if (identifier != null) {
      String[] parts = identifier.split("#");
      if (parts.length > 1) {
        return parts[parts.length - 1];
      }
      else {
        return parts[0];
      }
    }
    else {
      return null;
    }
  }

  @Override
  protected void setReferenceIdentifier(ModelElementInstance referenceSourceElement, String referenceIdentifier) {
    // TODO: implement something more robust (CAM-4028)
    referenceSourceElement.setAttributeValue("href", "#" + referenceIdentifier);
  }

}
