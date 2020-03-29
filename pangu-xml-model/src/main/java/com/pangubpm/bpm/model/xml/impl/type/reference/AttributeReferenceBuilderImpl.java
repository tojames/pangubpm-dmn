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
import com.pangubpm.bpm.model.xml.type.reference.AttributeReference;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReferenceBuilder;

/**
 * A builder for a attribute model reference based on a QName
 *
 * @author pangubpm( pangubpm@139.com )
 *
 */
public class AttributeReferenceBuilderImpl<T extends ModelElementInstance> implements AttributeReferenceBuilder<T>, ModelBuildOperation {

  private final AttributeImpl<String> referenceSourceAttribute;
  protected AttributeReferenceImpl<T> attributeReferenceImpl;
  private final Class<T> referenceTargetElement;

  /**
   * Create a new {@link AttributeReferenceBuilderImpl} from the reference source attribute
   * to the reference target model element instance
   *
   * @param referenceSourceAttribute the reference source attribute
   * @param referenceTargetElement the reference target model element instance
   */
  public AttributeReferenceBuilderImpl(AttributeImpl<String> referenceSourceAttribute, Class<T> referenceTargetElement) {
    this.referenceSourceAttribute = referenceSourceAttribute;
    this.referenceTargetElement = referenceTargetElement;
    this.attributeReferenceImpl = new AttributeReferenceImpl<T>(referenceSourceAttribute);
  }

  public AttributeReference<T> build() {
    referenceSourceAttribute.registerOutgoingReference(attributeReferenceImpl);
    return attributeReferenceImpl;
  }

  @SuppressWarnings("unchecked")
  public void performModelBuild(Model model) {
    // register declaring type as a referencing type of referenced type
    ModelElementTypeImpl referenceTargetType = (ModelElementTypeImpl) model.getType(referenceTargetElement);

    // the actual referenced type
    attributeReferenceImpl.setReferenceTargetElementType(referenceTargetType);

    // the referenced attribute may be declared on a base type of the referenced type.
    AttributeImpl<String> idAttribute = (AttributeImpl<String>) referenceTargetType.getAttribute("id");
    if(idAttribute != null) {
      idAttribute.registerIncoming(attributeReferenceImpl);
      attributeReferenceImpl.setReferenceTargetAttribute(idAttribute);
    } else {
      throw new ModelException("Element type " + referenceTargetType.getTypeNamespace() + ":" + referenceTargetType.getTypeName() + " has no id attribute");
    }
  }

}
