package com.vanda.tlzbfz.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private SystemInterceptor systemInterceptor;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
     /*   // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");*/

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(systemInterceptor).addPathPatterns("/**")
                .excludePathPatterns(
                        "/*.html",
                        "/*.js",
                        "/*.jpg",
                        "/*.png",
                        "/*.gif",
                        "/*.css",
                        "/*.ts",
                        "/sys/gettoken"
                )
            .excludePathPatterns(
                    "/swagger-resources/**",
                    "/webjars/**",
                    // "/v2/api-docs",
                    // "/swagger-ui.html",
                    "/v2/**",//swagger2的路径，不能拦截
                    "/swagger-ui.html/**");
        super.addInterceptors(registry);
    }


}
