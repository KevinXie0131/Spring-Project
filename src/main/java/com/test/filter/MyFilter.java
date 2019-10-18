package com.test.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        log.info("MyFilter doFilter() is invoked.");
        Enumeration<String> params = req.getParameterNames();
        while (params.hasMoreElements()) {
            String param=params.nextElement();
            log.info("Parameter:"+param+"\tValue:"+req.getParameter(param));
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        log.info("Filter in initialized");
    }

    @Override
    public void destroy() {

    }

}