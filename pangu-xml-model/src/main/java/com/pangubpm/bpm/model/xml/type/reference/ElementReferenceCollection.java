/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.reference;

import com.pangubpm.bpm.model.xml.impl.instance.ModelElementInstanceImpl;
import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;
import com.pangubpm.bpm.model.xml.type.child.ChildElementCollection;

import java.util.Collection;

/**
 * @author pangubpm( pangubpm@139.com )
 */
public interface ElementReferenceCollection<Target extends ModelElementInstance, Source extends ModelElementInstance> extends Reference<Target> {

  ChildElementCollection<Source> getReferenceSourceCollection();

  Collection<Target> getReferenceTargetElements(ModelElementInstanceImpl referenceSourceElement);
}
