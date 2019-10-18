package com.test.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent e) {
        log.info("MyServletContextListener Context Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent e) {
        log.info("MyServletContextListener Context Destroyed");
    }

}
