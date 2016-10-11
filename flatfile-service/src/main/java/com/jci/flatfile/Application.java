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
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jci.ConfigApplication;
/**
 * The Class Application.
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
