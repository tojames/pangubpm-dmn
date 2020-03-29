/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.type.reference;

import com.pangubpm.bpm.model.xml.ModelException;
import com.pangubpm.bpm.model.xml.ModelReferenceException;
import com.pangubpm.bpm.model.xml.UnsupportedModelOperationException;
import com.pangubpm.bpm.model.xml.impl.ModelInstanceImpl;
import com.pangubpm.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import com.pangubpm.bpm.model.xml.impl.type.ModelElementTypeImpl;
import com.pangubpm.bpm.model.xml.impl.util.ModelUtil;
import com.pangubpm.bpm.model.xml.instance.DomDocument;
import com.pangubpm.bpm.model.xml.instance.DomElement;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollection;
import com.pangubpm.bpm.model.xml.type.reference.ElementReferenceCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public class ElementReferenceCollectionImpl<Target extends ModelElementInstance, Source extends ModelElementInstance> extends  ReferenceImpl<Target> implements
  ElementReferenceCollection<Target, Source> {

  private final ChildElementCollection<Source> referenceSourceCollection;
  private ModelElementTypeImpl referenceSourceType;

  public ElementReferenceCollectionImpl(ChildElementCollection<Source> referenceSourceCollection) {
    this.referenceSourceCollection = referenceSourceCollection;
  }

  public ChildElementCollection<Source> getReferenceSourceCollection() {
    return referenceSourceCollection;
  }

  @Override
  protected void setReferenceIdentifier(ModelElementInstance referenceSourceElement, String referenceIdentifier) {
    referenceSourceElement.setTextContent(referenceIdentifier);
  }

  protected void performAddOperation(ModelElementInstanceImpl referenceSourceParentElement, Target referenceTargetElement) {
    ModelInstanceImpl modelInstance = referenceSourceParentElement.getModelInstance();
    String referenceTargetIdentifier = referenceTargetAttribute.getValue(referenceTargetElement);
    ModelElementInstance existingElement = modelInstance.getModelElementById(referenceTargetIdentifier);

    if (existingElement == null || !existingElement.equals(referenceTargetElement)) {
      throw new ModelReferenceException("Cannot create reference to model element " + referenceTargetElement
        +": element is not part of model. Please connect element to the model first.");
    }
    else {
      Collection<Source> referenceSourceElements = referenceSourceCollection.get(referenceSourceParentElement);
      Source referenceSourceElement = modelInstance.newInstance(referenceSourceType);
      referenceSourceElements.add(referenceSourceElement);
      setReferenceIdentifier(referenceSourceElement, referenceTargetIdentifier);
    }
  }

  protected void performRemoveOperation(ModelElementInstanceImpl referenceSourceParentElement, Object referenceTargetElement) {
    Collection<ModelElementInstance> referenceSourceChildElements = referenceSourceParentElement.getChildElementsByType(referenceSourceType);
    for (ModelElementInstance referenceSourceChildElement : referenceSourceChildElements) {
      if (getReferenceTargetElement(referenceSourceChildElement).equals(referenceTargetElement)) {
        referenceSourceParentElement.removeChildElement(referenceSourceChildElement);
      }
    }
  }

  protected void performClearOperation(ModelElementInstanceImpl referenceSourceParentElement, Collection<DomElement> elementsToRemove) {
    for (DomElement element: elementsToRemove) {
      referenceSourceParentElement.getDomElement().removeChild(element);
    }
  }

  public String getReferenceIdentifier(ModelElementInstance referenceSourceElement) {
    return referenceSourceElement.getTextContent();
  }

  @Override
  protected void updateReference(ModelElementInstance referenceSourceElement, String oldIdentifier, String newIdentifier) {
    String referencingTextContent = getReferenceIdentifier(referenceSourceElement);
    if (oldIdentifier != null && oldIdentifier.equals(referencingTextContent)) {
      setReferenceIdentifier(referenceSourceElement, newIdentifier);
    }
  }

  @Override
  protected void removeReference(ModelElementInstance referenceSourceElement, ModelElementInstance referenceTargetElement) {
    ModelElementInstance parentElement = referenceSourceElement.getParentElement();
    Collection<Source> childElementCollection = referenceSourceCollection.get(parentElement);
    childElementCollection.remove(referenceSourceElement);
  }

  public void setReferenceSourceElementType(ModelElementTypeImpl referenceSourceType) {
    this.referenceSourceType = referenceSourceType;
  }

  public ModelElementType getReferenceSourceElementType() {
    return referenceSourceType;
  }

  protected Collection<DomElement> getView(ModelElementInstanceImpl referenceSourceParentElement) {
    DomDocument document = referenceSourceParentElement.getModelInstance().getDocument();
    Collection<Source> referenceSourceElements = referenceSourceCollection.get(referenceSourceParentElement);
    Collection<DomElement> referenceTargetElements = new ArrayList<DomElement>();
    for (Source referenceSourceElement : referenceSourceElements) {
      String identifier = getReferenceIdentifier(referenceSourceElement);
      DomElement referenceTargetElement = document.getElementById(identifier);
      if (referenceTargetElement != null) {
        referenceTargetElements.add(referenceTargetElement);
      }
      else {
        throw new ModelException("Unable to find a model element instance for id " + identifier);
      }
    }
    return referenceTargetElements;
  }

  public Collection<Target> getReferenceTargetElements(final ModelElementInstanceImpl referenceSourceParentElement) {

    return new Collection<Target>() {

      public int size() {
        return getView(referenceSourceParentElement).size();
      }

      public boolean isEmpty() {
        return getView(referenceSourceParentElement).isEmpty();
      }

      public boolean contains(Object o) {
        if (o == null) {
          return false;
        }
        else if (!(o instanceof ModelElementInstanceImpl)) {
          return false;
        }
        else {
          return getView(referenceSourceParentElement).contains(((ModelElementInstanceImpl)o).getDomElement());
        }
      }

      public Iterator<Target> iterator() {
        Collection<Target> modelElementCollection = ModelUtil.getModelElementCollection(getView(referenceSourceParentElement), referenceSourceParentElement.getModelInstance());
        return modelElementCollection.iterator();
      }

      public Object[] toArray() {
        Collection<Target> modelElementCollection = ModelUtil.getModelElementCollection(getView(referenceSourceParentElement), referenceSourceParentElement.getModelInstance());
        return modelElementCollection.toArray();
      }

      public <T1> T1[] toArray(T1[] a) {
        Collection<Target> modelElementCollection = ModelUtil.getModelElementCollection(getView(referenceSourceParentElement), referenceSourceParentElement.getModelInstance());
        return modelElementCollection.toArray(a);
      }

      public boolean add(Target t) {
        if (referenceSourceCollection.isImmutable()) {
          throw new UnsupportedModelOperationException("add()", "collection is immutable");
        }
        else {
          if (!contains(t)) {
            performAddOperation(referenceSourceParentElement, t);
          }
          return true;
        }
      }

      public boolean remove(Object o) {
        if (referenceSourceCollection.isImmutable()) {
          throw new UnsupportedModelOperationException("remove()", "collection is immutable");
        }
        else {
          ModelUtil.ensureInstanceOf(o, ModelElementInstanceImpl.class);
          performRemoveOperation(referenceSourceParentElement, o);
          return true;
        }
      }

      public boolean containsAll(Collection<?> c) {
        Collection<Target> modelElementCollection = ModelUtil.getModelElementCollection(getView(referenceSourceParentElement), referenceSourceParentElement.getModelInstance());
        return modelElementCollection.containsAll(c);
      }

      public boolean addAll(Collection<? extends Target> c) {
        if (referenceSourceCollection.isImmutable()) {
          throw new UnsupportedModelOperationException("addAll()", "collection is immutable");
        }
        else {
          boolean result = false;
          for (Target o: c) {
            result |= add(o);
          }
          return result;
        }

      }

      public boolean removeAll(Collection<?> c) {
        if (referenceSourceCollection.isImmutable()) {
          throw new UnsupportedModelOperationException("removeAll()", "collection is immutable");
        }
        else {
          boolean result = false;
          for (Object o: c) {
            result |= remove(o);
          }
          return result;
        }
      }

      public boolean retainAll(Collection<?> c) {
        throw new UnsupportedModelOperationException("retainAll()", "not implemented");
      }

      public void clear() {
        if (referenceSourceCollection.isImmutable()) {
          throw new UnsupportedModelOperationException("clear()", "collection is immutable");
        }
        else {
          Collection<DomElement> view = new ArrayList<DomElement>();
          for (Source referenceSourceElement : referenceSourceCollection.get(referenceSourceParentElement)) {
            view.add(referenceSourceElement.getDomElement());
          }
          performClearOperation(referenceSourceParentElement, view);
        }
      }
    };
  }
}
