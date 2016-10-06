/**
 * 
 */
package com.jci.flatfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
@EnableFeignClients
@EnableHystrix
@EnableEurekaClient
public class Application  extends SpringBootServletInitializer{
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
