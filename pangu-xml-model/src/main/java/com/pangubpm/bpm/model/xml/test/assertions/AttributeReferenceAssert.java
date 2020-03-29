/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.test.assertions;

import com.pangubpm.bpm.model.xml.type.attribute.Attribute;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReference;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class AttributeReferenceAssert extends AbstractReferenceAssert<AttributeReferenceAssert, AttributeReference<?>> {

  protected AttributeReferenceAssert(AttributeReference<?> actual) {
    super(actual, AttributeReferenceAssert.class);
  }

  public AttributeReferenceAssert hasSourceAttribute(Attribute<?> sourceAttribute) {
    isNotNull();

    Attribute<String> actualSourceAttribute = actual.getReferenceSourceAttribute();

    if (!sourceAttribute.equals(actualSourceAttribute)) {
      failWithMessage("Expected reference <%s> to have source attribute <%s> but was <%s>", actual, sourceAttribute, actualSourceAttribute);
    }

    return this;
  }


}
