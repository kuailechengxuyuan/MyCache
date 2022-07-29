package com.zxqr.cache.evict;

import com.zxqr.cache.core.ICache;

/**
 * 驱除策略
 *
 * 1. 新加的 key
 * 2. map 实现
 * 3. 淘汰监听器
 *
 * @author zhangsanqiren
 * @since 0.0.1
 * @param <K> key
 * @param <V> value
 */
public interface ICacheEvictContext<K,V> {

    /**
     * 新加的 key
     * @return key
     * @since 0.0.1
            */
    K key();

    /**
     * cache 实现
     * @return map
     * @since 0.0.1
     */
    ICache<K, V> cache();

    /**
     * 获取大小
     * @return 大小
     * @since 0.0.1
     */
    int size();

}
