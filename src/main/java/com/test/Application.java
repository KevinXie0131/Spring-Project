package com.test;

import com.test.filter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContextListener;

@SpringBootApplication(scanBasePackages = {"com.test"})
@EnableScheduling
@EnableAsync
@EnableCaching
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting").allowedOrigins("http://localhost:8080");
            }
        };
    }

    // Register Servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new MyServlet(), "/myServlet");
        return bean;
    }

    // Register Filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new MyFilter());
        // Mapping filter to a Servlet
        bean.addServletRegistrationBeans(new ServletRegistrationBean[] {
                servletRegistrationBean()
        });
        return bean;
    }

    // Register ServletContextListener
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
        ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean<>();
        bean.setListener(new MyServletContextListener());
        return bean;
    }

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> loggingFilter(){
        FilterRegistrationBean<RequestResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestResponseLoggingFilter());
        registrationBean.addUrlPatterns("/greet/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<TransactionFilter> transactionFilter(){
        FilterRegistrationBean<TransactionFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new TransactionFilter());
        registrationBean.addUrlPatterns("/greet/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

}