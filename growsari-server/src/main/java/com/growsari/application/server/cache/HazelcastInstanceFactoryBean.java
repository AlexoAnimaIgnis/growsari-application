package com.growsari.application.server.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.ConfigLoader;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class HazelcastInstanceFactoryBean implements FactoryBean<HazelcastInstance> {
    private static final Logger logger = LoggerFactory.getLogger(HazelcastInstanceFactoryBean.class);

    @Value("#{systemProperties['hazelcast.config.path'] ?: 'classpath:hazelcast.xml'}")
    private String hazelcastConfigLocation;

    @PostConstruct
    public void init() {
        logger.info("hazelcastConfigLocation: " + hazelcastConfigLocation);
    }

    @Override
    public HazelcastInstance getObject() throws Exception {
        Config config = ConfigLoader.load(hazelcastConfigLocation);

        return Hazelcast.getOrCreateHazelcastInstance(config);
    }

    @Override
    public Class<?> getObjectType() {
        return HazelcastInstance.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
