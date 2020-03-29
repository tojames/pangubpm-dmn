/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.child;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.impl.ModelBuildOperation;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.ElementReferenceCollectionBuilderImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.IdsElementReferenceCollectionBuilderImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.QNameElementReferenceCollectionBuilderImpl;
import com.pangubpm.bpm.model.xml.impl.type.reference.UriElementReferenceCollectionBuilderImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollection;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollectionBuilder;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceCollectionBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ChildElementCollectionBuilderImpl<T extends ModelElementInstance> implements ChildElementCollectionBuilder<T>, ModelBuildOperation {

  /** The {@link ModelElementType} of the element containing the collection */
  protected final ModelElementTypeImpl parentElementType;
  private final ChildElementCollectionImpl<T> collection;
  protected final Class<T> childElementType;

  private ElementReferenceCollectionBuilder<?, ?> referenceBuilder;

  private final List<ModelBuildOperation> modelBuildOperations = new ArrayList<ModelBuildOperation>();

  public ChildElementCollectionBuilderImpl(Class<T> childElementTypeClass, ModelElementType parentElementType) {
    this.childElementType = childElementTypeClass;
    this.parentElementType = (ModelElementTypeImpl) parentElementType;
    this.collection = createCollectionInstance();

  }

  protected ChildElementCollectionImpl<T> createCollectionInstance() {
    return new ChildElementCollectionImpl<T>(childElementType, parentElementType);
  }

  public ChildElementCollectionBuilder<T> immutable() {
    collection.setImmutable();
    return this;
  }

  public ChildElementCollectionBuilder<T> required() {
    collection.setMinOccurs(1);
    return this;
  }

  public ChildElementCollectionBuilder<T> maxOccurs(int i) {
    collection.setMaxOccurs(i);
    return this;
  }

  public ChildElementCollectionBuilder<T> minOccurs(int i) {
    collection.setMinOccurs(i);
    return this;
  }

  public ChildElementCollection<T> build() {
    return collection;
  }

  public <V extends ModelElementInstance> ElementReferenceCollectionBuilder<V,T> qNameElementReferenceCollection(Class<V> referenceTargetType) {
    ChildElementCollectionImpl<T> collection = (ChildElementCollectionImpl<T>) build();
    QNameElementReferenceCollectionBuilderImpl<V,T> builder = new QNameElementReferenceCollectionBuilderImpl<V,T>(childElementType, referenceTargetType, collection);
    setReferenceBuilder(builder);
    return builder;
  }

  public <V extends ModelElementInstance> ElementReferenceCollectionBuilder<V, T> idElementReferenceCollection(Class<V> referenceTargetType) {
    ChildElementCollectionImpl<T> collection = (ChildElementCollectionImpl<T>) build();
    ElementReferenceCollectionBuilder<V,T> builder = new ElementReferenceCollectionBuilderImpl<V,T>(childElementType, referenceTargetType, collection);
    setReferenceBuilder(builder);
    return builder;
  }

  public <V extends ModelElementInstance> ElementReferenceCollectionBuilder<V, T> idsElementReferenceCollection(Class<V> referenceTargetType) {
    ChildElementCollectionImpl<T> collection = (ChildElementCollectionImpl<T>) build();
    ElementReferenceCollectionBuilder<V,T> builder = new IdsElementReferenceCollectionBuilderImpl<V,T>(childElementType, referenceTargetType, collection);
    setReferenceBuilder(builder);
    return builder;
  }

  public <V extends ModelElementInstance> ElementReferenceCollectionBuilder<V, T> uriElementReferenceCollection(Class<V> referenceTargetType) {
    ChildElementCollectionImpl<T> collection = (ChildElementCollectionImpl<T>) build();
    ElementReferenceCollectionBuilder<V,T> builder = new UriElementReferenceCollectionBuilderImpl<V, T>(childElementType, referenceTargetType, collection);
    setReferenceBuilder(builder);
    return builder;
  }

  protected void setReferenceBuilder(ElementReferenceCollectionBuilder<?, ?> referenceBuilder) {
    if (this.referenceBuilder != null) {
      throw new ModelException("An collection cannot have more than one reference");
    }
    this.referenceBuilder = referenceBuilder;
    modelBuildOperations.add(referenceBuilder);
  }

  public void performModelBuild(Model model) {
    ModelElementType elementType = model.getType(childElementType);
    if(elementType == null) {
      throw new ModelException(parentElementType +" declares undefined child element of type "+childElementType+".");
    }
    parentElementType.registerChildElementType(elementType);
    parentElementType.registerChildElementCollection(collection);
    for (ModelBuildOperation modelBuildOperation : modelBuildOperations) {
      modelBuildOperation.performModelBuild(model);
    }
  }

}
