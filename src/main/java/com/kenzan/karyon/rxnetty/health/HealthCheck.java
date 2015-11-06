package com.kenzan.karyon.rxnetty.health;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import netflix.karyon.health.HealthCheckHandler;

public class HealthCheck implements HealthCheckHandler {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheck.class);

    @Override
    public int getStatus() {
        logger.info("Health check called.");
        return 200;
    }

}
