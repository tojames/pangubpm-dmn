/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.test.assertions;

import org.assertj.core.api.Assertions;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.attribute.Attribute;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollection;
import com.pangubpm.bpm.model.xml.type.reference.AttributeReference;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceCollection;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class ModelAssertions extends Assertions {

  public static AttributeAssert assertThat(Attribute<?> actual) {
    return new AttributeAssert(actual);
  }

  public static ModelElementTypeAssert assertThat(ModelElementType actual) {
    return new ModelElementTypeAssert(actual);
  }

  public static ChildElementAssert assertThat(ChildElementCollection<?> actual) {
    return new ChildElementAssert(actual);
  }

  public static AttributeReferenceAssert assertThat(AttributeReference<?> actual) {
    return new AttributeReferenceAssert(actual);
  }

  public static ElementReferenceCollectionAssert assertThat(ElementReferenceCollection<?,?> actual) {
    return new ElementReferenceCollectionAssert(actual);
  }

}
