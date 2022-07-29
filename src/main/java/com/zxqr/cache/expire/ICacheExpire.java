package com.zxqr.cache.expire;

import com.zxqr.cache.core.Cache;

import java.util.Collection;

/**
 * 缓存过期接口
 * @author binbin.hou
 * @since 0.0.3
 */
public interface ICacheExpire<K,V> {

    /**
     * 指定过期信息
     */
    void expire(final K key, final long expireAt);

    /**
     * 惰性删除中需要处理的 keys
     */
    void refreshExpire(final Collection<K> keyList);



//    /**
//     * 待过期的 key
//     * 不存在，则返回 null
//     */
//    Long expireTime(final K key);

}
