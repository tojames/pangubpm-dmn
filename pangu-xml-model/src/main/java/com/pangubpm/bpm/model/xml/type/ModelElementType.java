/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.ModelInstance;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.attribute.Attribute;

import java.util.Collection;
import java.util.List;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface ModelElementType {

  String getTypeName();

  String getTypeNamespace();

  Class<? extends ModelElementInstance> getInstanceType();

  List<Attribute<?>> getAttributes();

  ModelElementInstance newInstance(ModelInstance modelInstance);

  ModelElementType getBaseType();

  boolean isAbstract();

  Collection<ModelElementType> getExtendingTypes();

  Collection<ModelElementType> getAllExtendingTypes();

  Attribute<?> getAttribute(String attributeName);

  Model getModel();

  Collection<ModelElementInstance> getInstances(ModelInstance modelInstanceImpl);

  List<ModelElementType> getChildElementTypes();

  List<ModelElementType> getAllChildElementTypes();

}
