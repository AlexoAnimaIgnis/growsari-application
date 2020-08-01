package com.growsari.application.server.cache;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

public class SpringCacheFactoryBean implements FactoryBean<Cache> {
    private CacheManager cacheManager;
    private String cacheName;

    public SpringCacheFactoryBean(CacheManager cacheManager, String cacheName) {
        this.cacheManager = cacheManager;
        this.cacheName = cacheName;
    }

    @Override
    public Cache getObject() throws Exception {
        return cacheManager.getCache(cacheName);
    }

    @Override
    public Class<Cache> getObjectType() {
        return Cache.class;
    }
}
