/**
 * 
 */
package com.jci.supp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

import com.jci.ConfigApplication;


/**
 * <p>
 * <strong>The Class Application.</strong>
 * <p>
 *
 * @author csonisk
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
