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
import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.ModelInstance;
import com.pangubpm.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import com.pangubpm.bpm.model.xml.impl.util.ModelUtil;
import com.pangubpm.bpm.model.xml.impl.validation.ModelInstanceValidator;
import com.pangubpm.bpm.model.xml.instance.DomDocument;
import com.pangubpm.bpm.model.xml.instance.DomElement;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.validation.ModelElementValidator;
import com.pangubpm.bpm.model.xml.validation.ValidationResults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An instance of a model
 *
 * @author pangubpm(pangubpm@139.com)
 * @author pangubpm( pangubpm@139.com )
 *
 */
public class ModelInstanceImpl implements ModelInstance {

  protected final DomDocument document;
  protected ModelImpl model;
  protected final ModelBuilder modelBuilder;

  public ModelInstanceImpl(ModelImpl model, ModelBuilder modelBuilder, DomDocument document) {
    this.model = model;
    this.modelBuilder = modelBuilder;
    this.document = document;
  }

  public DomDocument getDocument() {
    return document;
  }

  public ModelElementInstance getDocumentElement() {
    DomElement rootElement = document.getRootElement();
    if(rootElement != null) {
      return ModelUtil.getModelElement(rootElement, this);
    } else {
      return null;
    }
  }

  public void setDocumentElement(ModelElementInstance modelElement) {
    ModelUtil.ensureInstanceOf(modelElement, ModelElementInstanceImpl.class);
    DomElement domElement = modelElement.getDomElement();
    document.setRootElement(domElement);
  }

  public <T extends ModelElementInstance> T newInstance(Class<T> type) {
    return newInstance(type, null);
  }

  public <T extends ModelElementInstance> T newInstance(Class<T> type, String id) {
    ModelElementType modelElementType = model.getType(type);
    if(modelElementType != null) {
      return newInstance(modelElementType, id);
    } else {
      throw new ModelException("Cannot create instance of ModelType "+type+": no such type registered.");
    }
  }

  public <T extends ModelElementInstance> T newInstance(ModelElementType type) {
    return newInstance(type, null);
  }

  @SuppressWarnings("unchecked")
  public <T extends ModelElementInstance> T newInstance(ModelElementType type, String id) {
    ModelElementInstance modelElementInstance = type.newInstance(this);
    if (id != null && !id.isEmpty()) {
      ModelUtil.setNewIdentifier(type, modelElementInstance, id, false);
    } else {
      ModelUtil.setGeneratedUniqueIdentifier(type, modelElementInstance, false);
    }
    return (T) modelElementInstance;
  }

  public Model getModel() {
    return model;
  }

  public ModelElementType registerGenericType(String namespaceUri, String localName) {
    ModelElementType elementType = model.getTypeForName(namespaceUri, localName);
    if (elementType == null) {
      elementType = modelBuilder.defineGenericType(localName, namespaceUri);
      model = (ModelImpl) modelBuilder.build();
    }
    return elementType;
  }

  @SuppressWarnings("unchecked")
  public <T extends ModelElementInstance> T getModelElementById(String id) {
    if (id == null) {
      return null;
    }

    DomElement element = document.getElementById(id);
    if(element != null) {
      return (T) ModelUtil.getModelElement(element, this);
    } else {
      return null;
    }
  }

  public Collection<ModelElementInstance> getModelElementsByType(ModelElementType type) {
    Collection<ModelElementType> extendingTypes = type.getAllExtendingTypes();

    List<ModelElementInstance> instances = new ArrayList<ModelElementInstance>();
    for (ModelElementType modelElementType : extendingTypes) {
      if(!modelElementType.isAbstract()) {
        instances.addAll(modelElementType.getInstances(this));
      }
    }
    return instances;
  }

  @SuppressWarnings("unchecked")
  public <T extends ModelElementInstance> Collection<T> getModelElementsByType(Class<T> referencingClass) {
    return (Collection<T>) getModelElementsByType(getModel().getType(referencingClass));
  }

  @Override
  public ModelInstance clone() {
      return new ModelInstanceImpl(model, modelBuilder, document.clone());
  }

  @Override
  public ValidationResults validate(Collection<ModelElementValidator<?>> validators) {
    return new ModelInstanceValidator(this, validators).validate();
  }

}
