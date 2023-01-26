package com.unir.libraryoperator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LibraryOperatorApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

//	@Bean
//	public RestTemplate restTemplate() {
//		RestTemplate build = new RestTemplateBuilder().build();
//		return build;
//	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryOperatorApplication.class, args);
	}

}
