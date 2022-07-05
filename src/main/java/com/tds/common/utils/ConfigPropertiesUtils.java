package com.tds.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources({
        @PropertySource("classpath:config.properties"),
})
public class ConfigPropertiesUtils {

    @Value("${upload.file.path}")
    public String uploadFilePath;

    @Value("${toZip.file.path}")
    public String toZipFilePath;
}
