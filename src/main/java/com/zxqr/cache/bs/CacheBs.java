package com.zxqr.cache.bs;

import com.zxqr.cache.core.Cache;
import com.zxqr.cache.core.ICache;
import com.zxqr.cache.evict.CacheEvictFIFO;
import com.zxqr.cache.evict.ICacheEvict;
import com.zxqr.cache.expire.ICacheExpire;
import com.zxqr.cache.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存引导类
 */
public final class CacheBs<K,V> {

    private CacheBs(){}

    /**
     * 创建对象实例
     */
    public static <K,V> CacheBs<K,V> newInstance() {
        return new CacheBs<>();
    }


    /**
     * 过期策略
     * 暂时不做暴露
     */
    private ICacheExpire<K,V> cacheExpire;

    /**
     * 设置过期策略
     */
    public CacheBs<K, V> cacheExpire(ICacheExpire<K, V> cacheExpire) {
        this.cacheExpire = cacheExpire;
        return this;
    }
    /**
     * map 实现
     */
    private Map<K,V> map = new HashMap<>();

    /**
     * 大小限制
     */
    private int size = Integer.MAX_VALUE;

    /**
     * 驱除策略
     */
    private ICacheEvict<K,V> evict = new CacheEvictFIFO<>();

    /**
     * map 实现
     */
    public CacheBs<K, V> map(Map<K, V> map) {
        Utils.notNull(map, "map");

        this.map = map;
        return this;
    }

    /**
     * 设置 size 信息
     */
    public CacheBs<K, V> size(int size) {
        Utils.notNegative(size, "size");

        this.size = size;
        return this;
    }

    /**
     * 设置驱除策略
     */
    public CacheBs<K, V> evict(ICacheEvict<K, V> evict) {
        this.evict = evict;
        return this;
    }

    /**
     * 构建缓存信息
     */
    public ICache<K,V> build() {
        Cache<K,V> cache = new Cache();
        cache.evict(evict);
        cache.map(map);
        cache.sizeLimit(size);


        return cache;
    }

}