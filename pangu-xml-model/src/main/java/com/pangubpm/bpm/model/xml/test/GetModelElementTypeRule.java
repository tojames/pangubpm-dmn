/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.test;

import com.pangubpm.bpm.model.xml.Model;
import com.pangubpm.bpm.model.xml.ModelInstance;
import com.pangubpm.bpm.model.xml.type.ModelElementType;
import org.junit.rules.TestRule;

public interface GetModelElementTypeRule extends TestRule {

  ModelInstance getModelInstance();

  Model getModel();

  ModelElementType getModelElementType();

}
