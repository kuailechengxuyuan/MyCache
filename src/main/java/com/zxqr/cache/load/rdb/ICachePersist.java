package com.zxqr.cache.load.rdb;

import com.zxqr.cache.core.ICache;

public interface ICachePersist<K, V> {

    /**
     * 持久化缓存信息
     * @param cache 缓存
     * @since 0.0.7
     */
    void persist(final ICache<K, V> cache);

}