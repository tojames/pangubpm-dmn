/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.ModelBuilder;
import com.pangubpm.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import com.pangubpm.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeBuilderImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.ModelElementTypeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * This builder is used to define and create a new model.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class ModelBuilderImpl extends ModelBuilder {

  private final List<ModelElementTypeBuilderImpl> typeBuilders = new ArrayList<ModelElementTypeBuilderImpl>();
  private final ModelImpl model;

  public ModelBuilderImpl(String modelName) {
    model = new ModelImpl(modelName);
  }

  public ModelBuilder alternativeNamespace(String alternativeNs, String actualNs) {
    model.declareAlternativeNamespace(alternativeNs, actualNs);
    return this;
  }

  public ModelElementTypeBuilder defineType(Class<? extends ModelElementInstance> modelInstanceType, String typeName) {
    ModelElementTypeBuilderImpl typeBuilder = new ModelElementTypeBuilderImpl(modelInstanceType, typeName, model);
    typeBuilders.add(typeBuilder);
    return typeBuilder;
  }

  public ModelElementType defineGenericType(String typeName, String typeNamespaceUri) {
    ModelElementTypeBuilder typeBuilder = defineType(ModelElementInstance.class, typeName)
      .namespaceUri(typeNamespaceUri)
      .instanceProvider(new ModelElementTypeBuilder.ModelTypeInstanceProvider<ModelElementInstance>() {
        public ModelElementInstance newInstance(ModelTypeInstanceContext instanceContext) {
          return new ModelElementInstanceImpl(instanceContext);
        }
      });

    return typeBuilder.build();
  }

  public Model build() {
    for (ModelElementTypeBuilderImpl typeBuilder : typeBuilders) {
      typeBuilder.buildTypeHierarchy(model);
    }
    for (ModelElementTypeBuilderImpl typeBuilder : typeBuilders) {
      typeBuilder.performModelBuild(model);
    }
    return model;
  }

}
