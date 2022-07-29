package com.zxqr.cache.expire;


import com.zxqr.cache.core.ICache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 缓存过期-普通策略
 *
 * @author binbin.hou
 * @since 0.0.3
 * @param <K> key
 * @param <V> value
 */
public class CacheExpire<K,V> implements ICacheExpire<K,V> {

    /**
     * 过期 map
     */
    private final Map<K, Long> expireMap = new HashMap<>();

    @Override
    public void expire(K key, long expireAt) {
        expireMap.put(key, expireAt);
    }

    /**
     * 单次清空的数量限制
     */
    private static final int LIMIT = 100;

    /**
     * 缓存实现
     */
    private final ICache<K,V> cache;
    /**
     * 线程执行类
     * @since 0.0.3
     */
    private static final ScheduledExecutorService EXECUTOR_SERVICE
            = Executors.newSingleThreadScheduledExecutor();

    public CacheExpire(ICache<K, V> cache) {
        this.cache = cache;
        this.init();
    }
    /**
     * 初始化任务
     * @since 0.0.3
     */
    private void init() {
        EXECUTOR_SERVICE.scheduleAtFixedRate(new ExpireThread(), 100, 100, TimeUnit.MILLISECONDS);
    }

    @Override
    public void refreshExpire(Collection<K> keyList) {
        if(keyList==null || keyList.size()==0) {
            return;
        }

        // 判断大小，小的作为外循环。一般都是过期的 keys 比较小。

        // todo 增加永不过期key的处理逻辑
        if(keyList.size() <= expireMap.size()) {
            for(K key : keyList) {
                Long expireAt = expireMap.get(key);
                expireKey(key,expireAt);
            }
        } else {
            for(Map.Entry<K, Long> entry : expireMap.entrySet()) {
                this.expireKey(entry.getKey(),entry.getValue());
            }
        }
    }

    /**
     * 定时执行任务
     * @since 0.0.3
     */
    private class ExpireThread implements Runnable {
        @Override
        public void run() {
            //1.判断过期Map是否为空
            if(expireMap == null || 0 == expireMap.size()) {
                return;
            }
            //2. 获取 key 进行处理
            int count = 0;
            for(Map.Entry<K, Long> entry : expireMap.entrySet()) {
                if(count >= LIMIT) {
                    return;
                }
                expireKey(entry.getKey(),entry.getValue());
                count++;
            }
        }
    }

    /**
     * 执行过期操作
     */
    private void expireKey(K key,Long expireAt) {
        // 删除的逻辑处理
        long currentTime = System.currentTimeMillis();
        if(currentTime >= expireAt) {
            expireMap.remove(key);
            // 再移除缓存，后续可以通过惰性删除做补偿
            System.out.println("系统时间："+System.currentTimeMillis()+"  删除key："+key);
            cache.remove(key);
        }
    }
}
