/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml;

import com.pangubpm.bpm.model.xml.impl.ModelBuilderImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.ModelElementTypeBuilder;

/**
 * This builder is used to define and create a new model.
 *
 * @author pangubpm(pangubpm@139.com)
 *
 */
public abstract class ModelBuilder {

  public abstract ModelBuilder alternativeNamespace(String alternativeNs, String actualNs);

  public abstract ModelElementTypeBuilder defineType(Class<? extends ModelElementInstance> modelInstanceType, String typeName);

  public abstract ModelElementType defineGenericType(String typeName, String typeNamespaceUri);

  public abstract Model build();

  public static ModelBuilder createInstance(String modelName) {
    return new ModelBuilderImpl(modelName);
  }

}
