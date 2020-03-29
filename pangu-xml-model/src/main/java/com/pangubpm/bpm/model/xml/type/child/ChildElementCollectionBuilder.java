/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.child;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceCollectionBuilder;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface ChildElementCollectionBuilder<T extends ModelElementInstance> {

  ChildElementCollectionBuilder<T> immutable();

  ChildElementCollectionBuilder<T> required();

  ChildElementCollectionBuilder<T> minOccurs(int i);

  ChildElementCollectionBuilder<T> maxOccurs(int i);

  ChildElementCollection<T> build();

  <V extends ModelElementInstance> ElementReferenceCollectionBuilder<V,T> qNameElementReferenceCollection(Class<V> referenceTargetType);

  <V extends ModelElementInstance> ElementReferenceCollectionBuilder<V, T> idElementReferenceCollection(Class<V> referenceTargetType);

  <V extends ModelElementInstance> ElementReferenceCollectionBuilder<V, T> idsElementReferenceCollection(Class<V> referenceTargetType);

  <V extends ModelElementInstance> ElementReferenceCollectionBuilder<V, T> uriElementReferenceCollection(Class<V> referenceTargetType);

}
