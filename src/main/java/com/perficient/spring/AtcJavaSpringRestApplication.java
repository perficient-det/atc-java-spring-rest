package com.perficient.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AtcJavaSpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtcJavaSpringRestApplication.class, args);
	}

}
