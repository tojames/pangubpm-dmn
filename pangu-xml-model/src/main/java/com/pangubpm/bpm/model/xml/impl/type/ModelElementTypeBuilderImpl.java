/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.impl.ModelBuildOperation;
import com.pangubpm.bpm.model.xml.impl.ModelImpl;
import com.pangubpm.bpm.model.xml.impl.type.attribute.*;

import com.pangubpm.bpm.model.xml.impl.type.attribute.BooleanAttributeBuilder;
import com.pangubpm.bpm.model.xml.impl.type.attribute.DoubleAttributeBuilder;
import com.pangubpm.bpm.model.xml.impl.type.attribute.IntegerAttributeBuilder;
import com.pangubpm.bpm.model.xml.impl.type.attribute.StringAttributeBuilderImpl;
import com.pangubpm.bpm.model.xml.impl.type.child.SequenceBuilderImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.ModelElementTypeBuilder;
import com.pangubpm.bpm.model.xml.type.attribute.AttributeBuilder;
import com.pangubpm.bpm.model.xml.type.attribute.StringAttributeBuilder;
import com.pangubpm.bpm.model.xml.type.child.SequenceBuilder;

import java.util.ArrayList;
import java.util.List;

import com.pangubpm.bpm.model.xml.impl.type.attribute.EnumAttributeBuilder;
import com.pangubpm.bpm.model.xml.impl.type.attribute.NamedEnumAttributeBuilder;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ModelElementTypeBuilderImpl implements ModelElementTypeBuilder, ModelBuildOperation {

  private final ModelElementTypeImpl modelType;
  private final ModelImpl model;
  private final Class<? extends ModelElementInstance> instanceType;

  private final List<ModelBuildOperation> modelBuildOperations = new ArrayList<ModelBuildOperation>();
  private Class<? extends ModelElementInstance> extendedType;

  public ModelElementTypeBuilderImpl(Class<? extends ModelElementInstance> instanceType, String name, ModelImpl model) {
    this.instanceType = instanceType;
    this.model = model;
    modelType = new ModelElementTypeImpl(model, name, instanceType);
  }

  public ModelElementTypeBuilder extendsType(Class<? extends ModelElementInstance> extendedType) {
    this.extendedType = extendedType;
    return this;
  }

  public <T extends ModelElementInstance> ModelElementTypeBuilder instanceProvider(ModelTypeInstanceProvider<T> instanceProvider) {
    modelType.setInstanceProvider(instanceProvider);
    return this;
  }

  public ModelElementTypeBuilder namespaceUri(String namespaceUri) {
    modelType.setTypeNamespace(namespaceUri);
    return this;
  }

  public AttributeBuilder<Boolean> booleanAttribute(String attributeName) {
    BooleanAttributeBuilder builder = new BooleanAttributeBuilder(attributeName, modelType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public StringAttributeBuilder stringAttribute(String attributeName) {
    StringAttributeBuilderImpl builder = new StringAttributeBuilderImpl(attributeName, modelType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public AttributeBuilder<Integer> integerAttribute(String attributeName) {
    IntegerAttributeBuilder builder = new IntegerAttributeBuilder(attributeName, modelType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public AttributeBuilder<Double> doubleAttribute(String attributeName) {
    DoubleAttributeBuilder builder = new DoubleAttributeBuilder(attributeName, modelType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public <V extends Enum<V>> AttributeBuilder<V> enumAttribute(String attributeName, Class<V> enumType) {
    EnumAttributeBuilder<V> builder = new EnumAttributeBuilder<V>(attributeName, modelType, enumType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public <V extends Enum<V>> AttributeBuilder<V> namedEnumAttribute(String attributeName, Class<V> enumType) {
    NamedEnumAttributeBuilder<V> builder = new NamedEnumAttributeBuilder<V>(attributeName, modelType, enumType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public ModelElementType build() {
    model.registerType(modelType, instanceType);
    return modelType;
  }

  public ModelElementTypeBuilder abstractType() {
    modelType.setAbstract(true);
    return this;
  }

  public SequenceBuilder sequence() {
    SequenceBuilderImpl builder = new SequenceBuilderImpl(modelType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public void buildTypeHierarchy(Model model) {

    // build type hierarchy
    if(extendedType != null) {
      ModelElementTypeImpl extendedModelElementType = (ModelElementTypeImpl) model.getType(extendedType);
      if(extendedModelElementType == null) {
        throw new ModelException("Type "+modelType+" is defined to extend "+extendedType+" but no such type is defined.");

      } else {
        modelType.setBaseType(extendedModelElementType);
        extendedModelElementType.registerExtendingType(modelType);
      }
    }
  }

  public void performModelBuild(Model model) {
    for (ModelBuildOperation operation : modelBuildOperations) {
      operation.performModelBuild(model);
    }
  }
}
