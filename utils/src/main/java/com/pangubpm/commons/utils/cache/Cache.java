/**
 * Copyright (c) 2019-2030 panguBpm All rights reserved.
 * <p>
 * http://www.pangubpm.com/
 * <p>
 * （盘古BPM工作流平台）
 */
package com.pangubpm.commons.utils.cache;

import java.util.Set;

/**
 * A Map-like data structure that stores key-value pairs and provides temporary
 * access to it.
 *
 * @param <K> the type of keys
 * @param <V> the type of mapped values
 */
public interface Cache<K, V> {

  /**
   * Gets an entry from the cache.
   *
   * @param key the key whose associated value is to be returned
   * @return the element, or <code>null</code>, if it does not exist.
   */
  V get(K key);

  /**
   * Associates the specified value with the specified key in the cache.
   *
   * @param key   key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @throws NullPointerException if key is <code>null</code> or if value is <code>null</code>
   */
  void put(K key, V value);

  /**
   * Clears the contents of the cache.
   */
  void clear();

  /**
   * Removes an entry from the cache.
   *
   * @param key key with which the specified value is to be associated.
   */
  void remove(K key);

  /**
   * Returns a Set view of the keys contained in this cache.
   */
  public Set<K> keySet();

  /**
   * @return the current size of the cache
   */
  public int size();

  /**
   * Returns <code>true</code> if this cache contains no key-value mappings.
   */
  public boolean isEmpty();

}
