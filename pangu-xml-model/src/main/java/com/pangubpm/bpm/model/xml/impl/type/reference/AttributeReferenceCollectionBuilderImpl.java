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
import com.pangubpm.bpm.model.xml.impl.ModelBuildOperation;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.impl.type.attribute.AttributeImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceCollection;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceCollectionBuilder;

/**
 * @author Roman Smirnov
 * @author pangubpm( pangubpm@139.com )
 *
 */
public class AttributeReferenceCollectionBuilderImpl<T extends ModelElementInstance> implements AttributeReferenceCollectionBuilder<T>, ModelBuildOperation {

  private final AttributeImpl<String> referenceSourceAttribute;
  protected AttributeReferenceCollection<T> attributeReferenceCollection;
  private final Class<T> referenceTargetElement;

  @SuppressWarnings({ "unchecked", "rawtypes" })
  public AttributeReferenceCollectionBuilderImpl(AttributeImpl<String> attribute, Class<T> referenceTargetElement, Class<? extends AttributeReferenceCollection> attributeReferenceCollection) {
    this.referenceSourceAttribute = attribute;
    this.referenceTargetElement = referenceTargetElement;
    try {
      this.attributeReferenceCollection = (AttributeReferenceCollection<T>) attributeReferenceCollection.getConstructor(AttributeImpl.class)
        .newInstance(referenceSourceAttribute);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public AttributeReferenceCollection<T> build() {
    referenceSourceAttribute.registerOutgoingReference(attributeReferenceCollection);
    return attributeReferenceCollection;
  }

  @SuppressWarnings("unchecked")
  public void performModelBuild(Model model) {
    // register declaring type as a referencing type of referenced type
    ModelElementTypeImpl referenceTargetType = (ModelElementTypeImpl) model.getType(referenceTargetElement);

    // the actual referenced type
    attributeReferenceCollection.setReferenceTargetElementType(referenceTargetType);

    // the referenced attribute may be declared on a base type of the referenced type.
    AttributeImpl<String> idAttribute = (AttributeImpl<String>) referenceTargetType.getAttribute("id");
    if(idAttribute != null) {
      idAttribute.registerIncoming(attributeReferenceCollection);
      attributeReferenceCollection.setReferenceTargetAttribute(idAttribute);
    } else {
      throw new ModelException("Element type " + referenceTargetType.getTypeNamespace() + ":" + referenceTargetType.getTypeName() + " has no id attribute");
    }
  }

}
