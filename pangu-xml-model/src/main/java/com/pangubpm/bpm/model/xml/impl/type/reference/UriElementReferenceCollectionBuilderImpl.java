/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.impl.type.child.ChildElementCollectionImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

public class UriElementReferenceCollectionBuilderImpl<Target extends ModelElementInstance, Source extends ModelElementInstance> extends ElementReferenceCollectionBuilderImpl<Target, Source> {

  public UriElementReferenceCollectionBuilderImpl(Class<Source> childElementType, Class<Target> referenceTargetClass, ChildElementCollectionImpl<Source> collection) {
    super(childElementType, referenceTargetClass, collection);
    this.elementReferenceCollectionImpl = new UriElementReferenceCollectionImpl<Target, Source>(collection);
  }

}
