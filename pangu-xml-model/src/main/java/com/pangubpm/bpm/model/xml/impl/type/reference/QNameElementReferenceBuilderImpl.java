/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.impl.type.child.ChildElementImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class QNameElementReferenceBuilderImpl<Target extends ModelElementInstance, Source extends ModelElementInstance> extends ElementReferenceBuilderImpl<Target,Source> {

  public QNameElementReferenceBuilderImpl(Class<Source> childElementType, Class<Target> referenceTargetClass, ChildElementImpl<Source> child) {
    super(childElementType, referenceTargetClass, child);
    this.elementReferenceCollectionImpl = new QNameElementReferenceImpl<Target,Source>(child);
  }
}
