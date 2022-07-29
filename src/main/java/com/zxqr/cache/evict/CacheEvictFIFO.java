package com.zxqr.cache.evict;

import com.zxqr.cache.core.ICache;

import java.util.LinkedList;
import java.util.Queue;

public class CacheEvictFIFO<K,V> implements ICacheEvict<K,V> {

    /**
     * queue 信息
     * @since 0.0.2
     */
    private Queue<K> queue = new LinkedList<>();

    @Override
    public void evict(ICacheEvictContext<K, V> context) {
        final ICache<K,V> cache = context.cache();
        // 超过限制，执行移除
        if(cache.size() >= context.size()) {
            K evictKey = queue.remove();
            // 移除最开始的元素
            cache.remove(evictKey);
        }

        // 将新加的元素放入队尾
        final K key = context.key();
        queue.add(key);
    }

}