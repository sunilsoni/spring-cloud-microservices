/**
 * 
 */
package com.jci.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import com.github.jmnarloch.spring.cloud.feign.EnableFeignAcceptGzipEncoding;

@SpringBootApplication
@EnableCircuitBreaker
//@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
@EnableHystrixDashboard
//@EnableFeignAcceptGzipEncoding
//@EnableFeignContentGzipEncoding
@EnableFeignAcceptGzipEncoding
@EnableFeignClients
@EnableEurekaClient
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}
}
