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
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollection;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class ChildElementAssert extends AbstractAssert<ChildElementAssert, ChildElementCollection<?>> {

  private final Class<? extends ModelElementInstance> typeClass;

  protected ChildElementAssert(ChildElementCollection<?> actual) {
    super(actual, ChildElementAssert.class);
    typeClass = actual.getChildElementTypeClass();
  }

  public ChildElementAssert occursMinimal(int minOccurs) {
    isNotNull();

    int actualMinOccurs = actual.getMinOccurs();

    if (actualMinOccurs != minOccurs) {
      failWithMessage("Expected child element <%s> to have a min occurs of <%s> but was <%s>", typeClass, minOccurs, actualMinOccurs);
    }

    return this;
  }

  public ChildElementAssert occursMaximal(int maxOccurs) {
    isNotNull();

    int actualMaxOccurs = actual.getMaxOccurs();

    if (actualMaxOccurs != maxOccurs) {
      failWithMessage("Expected child element <%s> to have a max occurs of <%s> but was <%s>", typeClass, maxOccurs, actualMaxOccurs);
    }

    return this;
  }

  public ChildElementAssert isOptional() {
    isNotNull();

    int actualMinOccurs = actual.getMinOccurs();

    if (actualMinOccurs != 0) {
      failWithMessage("Expected child element <%s> to be optional but has min occurs of <%s>", typeClass, actualMinOccurs);
    }

    return this;
  }

  public ChildElementAssert isUnbounded() {
    isNotNull();

    int actualMaxOccurs = actual.getMaxOccurs();

    if (actualMaxOccurs != -1) {
      failWithMessage("Expected child element <%s> to be unbounded but has a max occurs of <%s>", typeClass, actualMaxOccurs);
    }

    return this;
  }

  public ChildElementAssert isMutable() {
    isNotNull();

    boolean actualImmutable = actual.isImmutable();

    if (actualImmutable) {
      failWithMessage("Expected child element <%s> to be mutable but was not", typeClass);
    }

    return this;
  }

  public ChildElementAssert isImmutable() {
    isNotNull();

    boolean actualImmutable = actual.isImmutable();

    if (!actualImmutable) {
      failWithMessage("Expected child element <%s> to be immutable but was not", typeClass);
    }

    return this;
  }

  public ChildElementAssert containsType(Class<? extends ModelElementInstance> childElementTypeClass) {
    isNotNull();

    Class<? extends ModelElementInstance> actualChildElementTypeClass = actual.getChildElementTypeClass();

    if (!childElementTypeClass.equals(actualChildElementTypeClass)) {
      failWithMessage("Expected child element <%s> to contain elements of type <%s> but contains elements of type <%s>", typeClass, childElementTypeClass, actualChildElementTypeClass);
    }

    return this;
  }

  public ChildElementAssert hasParentElementType(ModelElementType parentElementType) {
    isNotNull();

    ModelElementType actualParentElementType = actual.getParentElementType();

    if (!parentElementType.equals(actualParentElementType)) {
      failWithMessage("Expected child element <%s> to have parent element type <%s> but has <%s>", typeClass, parentElementType.getTypeName(), actualParentElementType.getTypeName());
    }

    return this;
  }

  public ChildElementAssert isNotEmpty(ModelElementInstance instance) {
    isNotNull();

    int actualNumberOfChildElements = actual.get(instance).size();

    if (actualNumberOfChildElements == 0) {
      failWithMessage("Expected child element <%s> to contain elements but was not", typeClass);
    }

    return this;
  }

  public ChildElementAssert hasSize(ModelElementInstance instance, int numberOfChildElements) {
    isNotNull();

    int actualNumberOfChildElements = actual.get(instance).size();

    if (actualNumberOfChildElements != numberOfChildElements) {
      failWithMessage("Expected child element <%s> to contain <%s> elements but has <%s>", typeClass, numberOfChildElements, actualNumberOfChildElements);
    }

    return this;
  }

  public ChildElementAssert isEmpty(ModelElementInstance instance) {
    isNotNull();

    int actualNumberOfChildElements = actual.get(instance).size();

    if (actualNumberOfChildElements > 0) {
      failWithMessage("Expected child element <%s> to contain no elements but contains <%s> elements", typeClass, actualNumberOfChildElements);
    }

    return this;
  }
}
