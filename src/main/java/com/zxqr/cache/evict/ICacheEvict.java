package com.zxqr.cache.evict;

/**
 * 驱除策略
 *
 * @author binbin.hou
 * @since 0.0.2
 */
public interface ICacheEvict<K, V> {

    /**
     * 驱除策略
     */
    void evict(final ICacheEvictContext<K, V> context);

//    /**
//     * 更新 key 信息
//     */
//    void updateKey(final K key);
//
//    /**
//     * 删除 key 信息
//     */
//    void removeKey(final K key);

}
