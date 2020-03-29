/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.child;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceBuilder;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface ChildElementBuilder<T extends ModelElementInstance> extends ChildElementCollectionBuilder<T> {

  ChildElementBuilder<T> immutable();

  ChildElementBuilder<T> required();

  ChildElementBuilder<T> minOccurs(int i);

  ChildElementBuilder<T> maxOccurs(int i);

  ChildElement<T> build();

  <V extends ModelElementInstance> ElementReferenceBuilder<V,T> qNameElementReference(Class<V> referenceTargetType);

  <V extends ModelElementInstance> ElementReferenceBuilder<V, T> idElementReference(Class<V> referenceTargetType);

  <V extends ModelElementInstance> ElementReferenceBuilder<V, T> uriElementReference(Class<V> referenceTargetType);

}
