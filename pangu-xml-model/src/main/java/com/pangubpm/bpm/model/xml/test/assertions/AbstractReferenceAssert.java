/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.test.assertions;

import org.assertj.core.api.AbstractAssert;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.attribute.Attribute;
import com.pangubpm.bpm.model.xml.type.reference.Reference;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public abstract class AbstractReferenceAssert<S extends AbstractReferenceAssert<S, T>, T extends Reference<?>> extends AbstractAssert<S, T> {

  protected AbstractReferenceAssert(T actual, Class<?> selfType) {
    super(actual, selfType);
  }

  public S hasIdentifier(ModelElementInstance instance, String identifier) {
    isNotNull();

    String actualIdentifier = actual.getReferenceIdentifier(instance);

    if (!identifier.equals(actualIdentifier)) {
      failWithMessage("Expected reference <%s> to have identifier <%s> but was <%s>", actual, identifier, actualIdentifier);
    }

    return myself;
  }

  public S hasTargetElement(ModelElementInstance instance, ModelElementInstance targetElement) {
    isNotNull();

    ModelElementInstance actualTargetElement = actual.getReferenceTargetElement(instance);

    if (!targetElement.equals(actualTargetElement)) {
      failWithMessage("Expected reference <%s> to have target element <%s> but was <%s>", actual, targetElement, actualTargetElement);
    }

    return myself;
  }

  public S hasNoTargetElement(ModelElementInstance instance) {
    isNotNull();

    ModelElementInstance actualTargetElement = actual.getReferenceTargetElement(instance);

    if (actualTargetElement != null) {
      failWithMessage("Expected reference <%s> to have no target element but has <%s>", actualTargetElement, actualTargetElement);
    }

    return myself;
  }

  public S hasTargetAttribute(Attribute<?> targetAttribute) {
    isNotNull();

    Attribute<String> actualTargetAttribute = actual.getReferenceTargetAttribute();

    if (!targetAttribute.equals(actualTargetAttribute)) {
      failWithMessage("Expected reference <%s> to have target attribute <%s> but was <%s>", actual, targetAttribute, actualTargetAttribute);
    }

    return myself;
  }
}
