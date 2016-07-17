/**
 * 
 */
package com.jci.po;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableResourceServer
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class,args);
		
	}
}
