package com.accenture.powerup.bookmng.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.accenture.powerup.bookmng.fliter.CorsSettingFilter;
import com.accenture.powerup.bookmng.fliter.ExceptionHandlerFilter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

    @Value("#{'${cors.accessControlAllowOrigin}'.split(',')}")
    private String[] accessControlAllowOriginArray;

    @Value("${cors.accessControlAllowHeaders}")
    private String accessControlAllowHeaders;

    /**
     * CorsSettingFilter。
     *
     * @return bean
     */
    @Bean
    public FilterRegistrationBean<CorsSettingFilter> corsSettingFilter() {
        FilterRegistrationBean<CorsSettingFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CorsSettingFilter(accessControlAllowOriginArray, accessControlAllowHeaders));
        bean.setOrder(1);
        return bean;
    }

    /**
     * ExceptionHandlerFilter。
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> exceptionHandlerFilter() {
        FilterRegistrationBean<ExceptionHandlerFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new ExceptionHandlerFilter());
        bean.setOrder(2);
        return bean;
    }

}
