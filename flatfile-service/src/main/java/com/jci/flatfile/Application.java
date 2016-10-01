/**
 * 
 */
package com.jci.flatfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
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
