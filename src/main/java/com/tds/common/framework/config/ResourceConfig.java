package com.tds.common.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
//    @Autowired
//    private RepeatSubmitInterceptor repeatSubmitInterceptor;

    @Value("${upload.file.path}")
    public String uploadFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**").addResourceLocations("file:"+uploadFilePath);

    }
    /**
     * 自定义拦截规则
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
//    }
}
