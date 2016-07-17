/**
 * 
 */
package com.jci.asn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
//@EnableResourceServer
@EnableCircuitBreaker
public class Application {
	public static void main(String[] args) {
		System.out.println("#### Starting Application. main ####");
		SpringApplication.run(Application.class,args);
		
		System.out.println("#### Ending Application. main ####");
	}
}
