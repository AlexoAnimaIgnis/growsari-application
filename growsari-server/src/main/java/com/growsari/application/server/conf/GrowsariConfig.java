package com.growsari.application.server.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("growsariConfig")
@ManagedResource(objectName = "com.growsari.application:type=config,name=growsariConfig",
        description = "config for growsari")
public class GrowsariConfig {
    private static final Logger logger = LoggerFactory.getLogger(GrowsariConfig.class);

    @PostConstruct
    public void afterPropertiesSet() {
        logger.info("Config: " + toString());
    }
}
