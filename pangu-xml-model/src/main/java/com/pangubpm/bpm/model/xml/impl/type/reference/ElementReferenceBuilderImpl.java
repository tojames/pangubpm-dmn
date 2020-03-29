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
import com.pangubpm.bpm.model.xml.type.reference.ElementReference;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceBuilder;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class ElementReferenceBuilderImpl<Target extends ModelElementInstance, Source extends ModelElementInstance> extends ElementReferenceCollectionBuilderImpl<Target,Source> implements
  ElementReferenceBuilder<Target, Source> {

  public ElementReferenceBuilderImpl(Class<Source> childElementType, Class<Target> referenceTargetClass, ChildElementImpl<Source> child) {
    super(childElementType, referenceTargetClass, child);
    this.elementReferenceCollectionImpl = new ElementReferenceImpl<Target, Source>(child);
  }

  @SuppressWarnings("unchecked")
  public ElementReference<Target,Source> build() {
    return (ElementReference<Target, Source>) elementReferenceCollectionImpl;
  }

}
