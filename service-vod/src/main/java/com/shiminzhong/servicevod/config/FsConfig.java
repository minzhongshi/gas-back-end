package com.shiminzhong.servicevod.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * The Class FsAutoConfiguration.
 *
 * @author Administrator
 */
@Configuration
public class FsConfig implements WebMvcConfigurer {


    public static final String VEDIO_STOREAGE_PATH = "/opt/storage";


    /**
     * The fs view prefix.
     */
    public static final String FS_CONTEXT_PATH = "/fs";


    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#
     * addResourceHandlers(org.springframework.web.servlet.config.annotation.
     * ResourceHandlerRegistry)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File storeDir = new File(VEDIO_STOREAGE_PATH, FS_CONTEXT_PATH);
        String fsResourcePath = ResourceUtils.FILE_URL_PREFIX + storeDir.getAbsolutePath();
        // 必须以/结尾
        fsResourcePath += StringUtils.endsWithIgnoreCase(fsResourcePath, File.separator) ? "" : File.separator;
        String fsCtxPattern = FS_CONTEXT_PATH + "/**";
        registry.addResourceHandler(fsCtxPattern).addResourceLocations(fsResourcePath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }


}
