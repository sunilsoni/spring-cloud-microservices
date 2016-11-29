/**
 * (C) Copyright 2016 Johnson Controls, Inc
 * Use or Copying of all or any part of this program, except as
 * permitted by License Agreement, is prohibited.
 */

package com.jci.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jci.ConfigApplication;
/**
 * <p>
 * <strong> The Scheduler Application Class.</strong>
 * <p>
 *
 * @author csonisk
 */
@SpringBootApplication
@EnableWebMvc
@EnableFeignClients
@EnableHystrix
@EnableEurekaClient
@Import(ConfigApplication.class) 
public class Application  extends SpringBootServletInitializer{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.getProperties().put("http.proxyHost", "10.10.5.18");
    	System.getProperties().put("http.proxyPort", "8080");
    	System.getProperties().put("http.proxyUser", "cdubeyar");
    	System.getProperties().put("http.proxyPassword", "Arun123"); 
		SpringApplication.run(Application.class, args);

	}
	
	/**
	 * Feign logger level.
	 *
	 * @return the feign. logger. level
	 */
	@Bean
	public feign.Logger.Level feignLoggerLevel() {
	    return feign.Logger.Level.FULL;
	}
}
