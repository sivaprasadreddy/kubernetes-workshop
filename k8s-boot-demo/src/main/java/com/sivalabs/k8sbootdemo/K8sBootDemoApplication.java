package com.sivalabs.k8sbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class K8sBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8sBootDemoApplication.class, args);
	}

}
