package com.restapi.integration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@ComponentScan({"com.restapi"})
@EnableAutoConfiguration(exclude = {EmbeddedServletContainerAutoConfiguration.class})
@PropertySource(value = "file:integration.properties", ignoreResourceNotFound = true)
@Configuration
public class SpringConfig {

  private final static Log LOGGER = LogFactory.getLog(SpringConfig.class);

  @Bean
  public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  } 
}
