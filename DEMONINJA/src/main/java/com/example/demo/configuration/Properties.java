package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/configuration/configuration-dev.properties")
public class Properties {
	

}
