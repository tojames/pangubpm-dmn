/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.type.child;

import com.pangubpm.bpm.model.xml.instance.ModelElementInstance;

/**
 * @author pangubpm(pangubpm@139.com)
 *
 */
public interface SequenceBuilder {

  <T extends ModelElementInstance> ChildElementBuilder<T> element(Class<T> childElementType);

  <T extends ModelElementInstance> ChildElementCollectionBuilder<T> elementCollection(Class<T> childElementType);

}
