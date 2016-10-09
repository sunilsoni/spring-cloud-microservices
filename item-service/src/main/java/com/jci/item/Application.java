/**
 * 
 */
package com.jci.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.jci.ConfigApplication;


/**
 * The Class Application.
 */
@EnableEurekaClient
@SpringBootApplication
@Import(ConfigApplication.class) 
public class Application {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}
	
}
