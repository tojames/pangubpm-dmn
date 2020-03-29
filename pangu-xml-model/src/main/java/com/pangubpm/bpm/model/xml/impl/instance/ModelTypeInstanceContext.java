/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.instance;

import com.pangubpm.bpm.model.xml.impl.ModelInstanceImpl;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.instance.DomElement;

/**
 * @author pangubpm(pangubpm@139.com)
 * @author pangubpm( pangubpm@139.com )
 *
 */
public final class ModelTypeInstanceContext {

  private final ModelInstanceImpl model;
  private final DomElement domElement;
  private final ModelElementTypeImpl modelType;

  public ModelTypeInstanceContext(DomElement domElement, ModelInstanceImpl model, ModelElementTypeImpl modelType) {
    this.domElement = domElement;
    this.model = model;
    this.modelType = modelType;
  }

  /**
   * @return the dom element
   */
  public DomElement getDomElement() {
    return domElement;
  }

  /**
   * @return the model
   */
  public ModelInstanceImpl getModel() {
    return model;
  }

  /**
   * @return the modelType
   */
  public ModelElementTypeImpl getModelType() {
    return modelType;
  }

}
