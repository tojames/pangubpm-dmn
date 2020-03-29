/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.bpm.model.xml.impl.util;

import com.pangubpm.bpm.model.xml.ModelException;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.net.URL;


/**
 * @author Tom Baeyens
 */
public abstract class ReflectUtil {

  public static InputStream getResourceAsStream(String name) {
    // Try the current Thread context class loader
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream resourceStream = classLoader.getResourceAsStream(name);
    if (resourceStream == null) {
      // Finally, try the class loader for this class
      classLoader = ReflectUtil.class.getClassLoader();
      resourceStream = classLoader.getResourceAsStream(name);
    }

    return resourceStream;
  }

  public static URL getResource(String name) {
    return getResource(name, null);
  }

  public static URL getResource(String name, ClassLoader classLoader) {
    if(classLoader == null) {
      // Try the current Thread context class loader
      classLoader = Thread.currentThread().getContextClassLoader();
    }
    URL url = classLoader.getResource(name);
    if (url == null) {
      // Finally, try the class loader for this class
      classLoader = ReflectUtil.class.getClassLoader();
      url = classLoader.getResource(name);
    }

    return url;
  }

  public static File getResourceAsFile(String path) {
    URL resource = getResource(path);
    try {
      return new File(resource.toURI());
    } catch (URISyntaxException e) {
      throw new ModelException("Exception while loading resource file " + path, e);
    }
  }

  /**
   * Create a new instance of the provided type
   *
   * @param type the class to create a new instance of
   * @param parameters the parameters to pass to the constructor
   * @return the created instance
   */
  public static <T> T createInstance(Class<T> type, Object... parameters) {

    // get types for parameters
    Class<?>[] parameterTypes = new Class<?>[parameters.length];
    for (int i = 0; i < parameters.length; i++) {
      Object parameter = parameters[i];
      parameterTypes[i] = parameter.getClass();
    }

    try {
      // create instance
      Constructor<T> constructor = type.getConstructor(parameterTypes);
      return constructor.newInstance(parameters);

    } catch (Exception e) {
      throw new ModelException("Exception while creating an instance of type "+type, e);
    }
  }

}
