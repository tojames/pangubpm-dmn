/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.child;

import com.pangubpm.bpm.model.xml.impl.type.reference.ElementReferenceBuilderImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.QNameElementReferenceBuilderImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.UriElementReferenceBuilderImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.child.ChildElement;
import com.pangubpm.bpm.model.xml.type.child.ChildElementBuilder;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceBuilder;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ChildElementBuilderImpl<T extends ModelElementInstance> extends ChildElementCollectionBuilderImpl<T> implements ChildElementBuilder<T> {

  public ChildElementBuilderImpl(Class<T> childElementTypeClass, ModelElementType parentElementType) {
    super(childElementTypeClass, parentElementType);
  }

  @Override
  protected ChildElementCollectionImpl<T> createCollectionInstance() {
    return new ChildElementImpl<T>(childElementType, parentElementType);
  }

  public ChildElementBuilder<T> immutable() {
    super.immutable();
    return this;
  }

  public ChildElementBuilder<T> required() {
    super.required();
    return this;
  }

  public ChildElementBuilder<T> minOccurs(int i) {
    super.minOccurs(i);
    return this;
  }

  public ChildElementBuilder<T> maxOccurs(int i) {
    super.maxOccurs(i);
    return this;
  }

  public ChildElement<T> build() {
    return (ChildElement<T>) super.build();
  }

  public <V extends ModelElementInstance> ElementReferenceBuilder<V, T> qNameElementReference(Class<V> referenceTargetType) {
    ChildElementImpl<T> child = (ChildElementImpl<T>) build();
    QNameElementReferenceBuilderImpl<V,T> builder = new QNameElementReferenceBuilderImpl<V, T>(childElementType, referenceTargetType, child);
    setReferenceBuilder(builder);
    return builder;
  }

  public <V extends ModelElementInstance> ElementReferenceBuilder<V, T> idElementReference(Class<V> referenceTargetType) {
    ChildElementImpl<T> child = (ChildElementImpl<T>) build();
    ElementReferenceBuilderImpl<V, T> builder = new ElementReferenceBuilderImpl<V, T>(childElementType, referenceTargetType, child);
    setReferenceBuilder(builder);
    return builder;
  }

  public <V extends ModelElementInstance> ElementReferenceBuilder<V, T> uriElementReference(Class<V> referenceTargetType) {
    ChildElementImpl<T> child = (ChildElementImpl<T>) build();
    ElementReferenceBuilderImpl<V, T> builder = new UriElementReferenceBuilderImpl<V, T>(childElementType, referenceTargetType, child);
    setReferenceBuilder(builder);
    return builder;
  }

}
