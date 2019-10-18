package com.test.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class TransactionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TransactionFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Starting a transaction for req : {}", req.getRequestURI());

        chain.doFilter(request, response);
        log.info("Committing a transaction for req : {}", req.getRequestURI());
    }

    // other methods
}