package com.growsari.application.war.listener;

import ch.qos.logback.classic.LoggerContext;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;

@WebListener
public class StartupContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(StartupContextListener.class);
    private static final String UNREGISTER_JDBC_DRIVER_MSG = "Unregistering of jdbc driver %s failed";

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Application started");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Application stopped");

        stopLogback();
    }

    private void stopLogback() {
        if (LoggerFactory.getILoggerFactory() instanceof LoggerContext) {
            ((LoggerContext) LoggerFactory.getILoggerFactory()).stop();
        }
    }

    private void unloadJDBCDrivers() {

        Enumeration<Driver> drivers = DriverManager.getDrivers();

        Collections.list(drivers).forEach(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
                logger.error(String.format(UNREGISTER_JDBC_DRIVER_MSG, driver), ex);
            }
        });

        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
