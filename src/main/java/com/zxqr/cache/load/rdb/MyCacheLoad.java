package com.zxqr.cache.load.rdb;

import com.zxqr.cache.core.ICache;

public class MyCacheLoad implements ICacheLoad<String,String> {

    @Override
    public void load(ICache<String, String> cache) {
        cache.put("1", "1");
        cache.put("2", "2");
    }

}