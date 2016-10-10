package com.jci;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



/**
 * The Class Application.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ConfigApplication {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//SpringApplication.run(ConfigApplication.class,args);
	}
	
}
