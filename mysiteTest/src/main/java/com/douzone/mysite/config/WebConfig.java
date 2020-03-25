package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.douzone.mysite.config.web.MvcConfig;

@Configuration
@Import({MvcConfig.class})
@ComponentScan({"com.douzone.mysite.controller"})
public class WebConfig {

}
