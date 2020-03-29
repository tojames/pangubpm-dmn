/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.child;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.impl.ModelBuildOperation;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.child.ChildElementBuilder;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollectionBuilder;
import com.pangubpm.bpm.model.xml.type.child.SequenceBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public class SequenceBuilderImpl implements SequenceBuilder, ModelBuildOperation {

  private final ModelElementTypeImpl elementType;

  private final List<ModelBuildOperation> modelBuildOperations = new ArrayList<ModelBuildOperation>();

  public SequenceBuilderImpl(ModelElementTypeImpl modelType) {
    this.elementType = modelType;
  }

  public <T extends ModelElementInstance> ChildElementBuilder<T> element(Class<T> childElementType) {
    ChildElementBuilderImpl<T> builder = new ChildElementBuilderImpl<T>(childElementType, elementType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public <T extends ModelElementInstance> ChildElementCollectionBuilder<T> elementCollection(Class<T> childElementType) {
    ChildElementCollectionBuilderImpl<T> builder = new ChildElementCollectionBuilderImpl<T>(childElementType, elementType);
    modelBuildOperations.add(builder);
    return builder;
  }

  public void performModelBuild(Model model) {
    for (ModelBuildOperation operation : modelBuildOperations) {
      operation.performModelBuild(model);
    }
  }

}
