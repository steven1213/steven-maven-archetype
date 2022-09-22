package com.steven.maven.archetype.start.config;

import com.steven.maven.archetype.start.interceptor.SignVerifyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author: steven.cao.
 * @version: 1.8.
 */
@Configuration
@Component
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private SignVerifyInterceptor signVerifyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signVerifyInterceptor)
                .excludePathPatterns("/swagger-ui.html",
                        "/v2/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/app/generator",
                        "/app/app-secret/reset",
                        "/favicon.ico")
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
