/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.test;

import static com.pangubpm.bpm.model.xml.test.assertions.ModelAssertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.assertj.core.api.Assertions;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.ModelInstance;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.impl.util.ModelTypeException;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.test.assertions.AttributeAssert;
import com.pangubpm.bpm.model.xml.test.assertions.ChildElementAssert;
import com.pangubpm.bpm.model.xml.test.assertions.ModelElementTypeAssert;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import org.junit.Test;
import org.w3c.dom.DOMException;

import com.pangubpm.bpm.model.xml.test.assertions.ModelAssertions;

public abstract class AbstractModelElementInstanceTest {


  protected class TypeAssumption {

    public final String namespaceUri;
    public final ModelElementType extendsType;
    public final boolean isAbstract;

    public TypeAssumption(boolean isAbstract) {
      this(getDefaultNamespace(), isAbstract);
    }

    public TypeAssumption(String namespaceUri, boolean isAbstract) {
      this(namespaceUri, null, isAbstract);
    }

    public TypeAssumption(Class<? extends ModelElementInstance> extendsType, boolean isAbstract) {
      this(getDefaultNamespace(), extendsType, isAbstract);
    }

    public TypeAssumption(String namespaceUri, Class<? extends ModelElementInstance> extendsType, boolean isAbstract) {
      this.namespaceUri = namespaceUri;
      this.extendsType = model.getType(extendsType);
      this.isAbstract = isAbstract;
    }
  }

  protected class ChildElementAssumption {

    public final String namespaceUri;
    public final ModelElementType childElementType;
    public final int minOccurs;
    public final int maxOccurs;

    public ChildElementAssumption(Class<? extends ModelElementInstance> childElementType) {
      this(childElementType, 0, -1);
    }

    public ChildElementAssumption(String namespaceUri, Class<? extends ModelElementInstance> childElementType) {
      this(namespaceUri, childElementType, 0, -1);
    }

    public ChildElementAssumption(Class<? extends ModelElementInstance> childElementType, int minOccurs) {
      this(childElementType, minOccurs, -1);
    }

    public ChildElementAssumption(String namespaceUri, Class<? extends ModelElementInstance> childElementType, int minOccurs) {
      this(namespaceUri, childElementType, minOccurs, -1);
    }

    public ChildElementAssumption(Class<? extends ModelElementInstance> childElementType, int minOccurs, int maxOccurs) {
      this(getDefaultNamespace(), childElementType, minOccurs, maxOccurs);
    }

    public ChildElementAssumption(String namespaceUri, Class<? extends ModelElementInstance> childElementType, int minOccurs, int maxOccurs) {
      this.namespaceUri = namespaceUri;
      this.childElementType = model.getType(childElementType);
      this.minOccurs = minOccurs;
      this.maxOccurs = maxOccurs;
    }
  }

  protected class AttributeAssumption {

    public final String attributeName;
    public final String namespace;
    public final boolean isIdAttribute;
    public final boolean isRequired;
    public final Object defaultValue;

    public AttributeAssumption(String attributeName) {
      this(attributeName, false, false);
    }

    public AttributeAssumption(String namespace, String attributeName) {
      this(namespace, attributeName, false, false);
    }

    public AttributeAssumption(String attributeName, boolean isIdAttribute) {
      this(attributeName, isIdAttribute, false);
    }

    public AttributeAssumption(String namespace, String attributeName, boolean isIdAttribute) {
      this(namespace, attributeName, isIdAttribute, false);
    }

    public AttributeAssumption(String attributeName, boolean isIdAttribute, boolean isRequired) {
      this(attributeName, isIdAttribute, isRequired, null);
    }

    public AttributeAssumption(String namespace, String attributeName, boolean isIdAttribute, boolean isRequired) {
      this(namespace, attributeName, isIdAttribute, isRequired, null);
    }

    public AttributeAssumption(String attributeName, boolean isIdAttribute, boolean isRequired, Object defaultValue) {
      this(null, attributeName, isIdAttribute, isRequired, defaultValue);
    }

    public AttributeAssumption(String namespace, String attributeName, boolean isIdAttribute, boolean isRequired, Object defaultValue) {
      this.attributeName = attributeName;
      this.namespace = namespace;
      this.isIdAttribute = isIdAttribute;
      this.isRequired = isRequired;
      this.defaultValue = defaultValue;
    }
  }

  public static ModelInstance modelInstance;
  public static Model model;
  public static ModelElementType modelElementType;

  public static void initModelElementType(GetModelElementTypeRule modelElementTypeRule) {
    modelInstance = modelElementTypeRule.getModelInstance();
    model = modelElementTypeRule.getModel();
    modelElementType = modelElementTypeRule.getModelElementType();
    Assertions.assertThat(modelInstance).isNotNull();
    Assertions.assertThat(model).isNotNull();
    ModelAssertions.assertThat(modelElementType).isNotNull();
  }

  public abstract String getDefaultNamespace();
  public abstract TypeAssumption getTypeAssumption();
  public abstract Collection<ChildElementAssumption> getChildElementAssumptions();
  public abstract Collection<AttributeAssumption> getAttributesAssumptions();


  public ModelElementTypeAssert assertThatType() {
    return ModelAssertions.assertThat(modelElementType);
  }

  public AttributeAssert assertThatAttribute(String attributeName) {
    return ModelAssertions.assertThat(modelElementType.getAttribute(attributeName));
  }

  public ChildElementAssert assertThatChildElement(ModelElementType childElementType) {
    ModelElementTypeImpl modelElementTypeImpl = (ModelElementTypeImpl) modelElementType;
    return assertThat(modelElementTypeImpl.getChildElementCollection(childElementType));
  }

  public ModelElementType getType(Class<? extends ModelElementInstance> instanceClass) {
    return model.getType(instanceClass);
  }

  @Test
  public void testType() {
    assertThatType().isPartOfModel(model);

    TypeAssumption assumption = getTypeAssumption();
    assertThatType().hasTypeNamespace(assumption.namespaceUri);

    if (assumption.isAbstract) {
      assertThatType().isAbstract();
    }
    else {
      assertThatType().isNotAbstract();
    }
    if (assumption.extendsType == null) {
      assertThatType().extendsNoType();
    }
    else {
      assertThatType().extendsType(assumption.extendsType);
    }

    if (assumption.isAbstract) {
      try {
        modelInstance.newInstance(modelElementType);
        fail("Element type " + modelElementType.getTypeName() + " is abstract.");
      }
      catch (DOMException e) {
        // expected exception
      }
      catch (ModelTypeException e) {
        // expected exception
      }
      catch (Exception e) {
        fail("Unexpected exception " + e.getMessage());
      }
    }
    else {
      ModelElementInstance modelElementInstance = modelInstance.newInstance(modelElementType);
      Assertions.assertThat(modelElementInstance).isNotNull();
    }
  }

  @Test
  public void testChildElements() {
    Collection<ChildElementAssumption> childElementAssumptions = getChildElementAssumptions();
    if (childElementAssumptions == null) {
      assertThatType().hasNoChildElements();
    }
    else {
      Assertions.assertThat(modelElementType.getChildElementTypes().size()).isEqualTo(childElementAssumptions.size());
      for (ChildElementAssumption assumption : childElementAssumptions) {
        assertThatType().hasChildElements(assumption.childElementType);
        if (assumption.namespaceUri != null) {
          ModelAssertions.assertThat(assumption.childElementType).hasTypeNamespace(assumption.namespaceUri);
        }
        assertThatChildElement(assumption.childElementType)
          .occursMinimal(assumption.minOccurs)
          .occursMaximal(assumption.maxOccurs);
      }
    }
  }

  @Test
  public void testAttributes() {
    Collection<AttributeAssumption> attributesAssumptions = getAttributesAssumptions();
    if (attributesAssumptions == null) {
      assertThatType().hasNoAttributes();
    }
    else {
      Assertions.assertThat(attributesAssumptions).hasSameSizeAs(modelElementType.getAttributes());
      for (AttributeAssumption assumption : attributesAssumptions) {
        assertThatType().hasAttributes(assumption.attributeName);
        AttributeAssert attributeAssert = assertThatAttribute(assumption.attributeName);

        attributeAssert.hasOwningElementType(modelElementType);

        if (assumption.namespace != null) {
          attributeAssert.hasNamespaceUri(assumption.namespace);
        }
        else {
          attributeAssert.hasNoNamespaceUri();
        }

        if (assumption.isIdAttribute) {
          attributeAssert.isIdAttribute();
        }
        else {
          attributeAssert.isNotIdAttribute();
        }

        if (assumption.isRequired) {
          attributeAssert.isRequired();
        }
        else {
          attributeAssert.isOptional();
        }

        if (assumption.defaultValue == null) {
          attributeAssert.hasNoDefaultValue();
        }
        else {
          attributeAssert.hasDefaultValue(assumption.defaultValue);
        }

      }
    }
  }
}
