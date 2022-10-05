package com.perficient.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.perficient.spring.model.Car;

@Configuration
public class BeanConfiguration {
	
	@Bean(name="hello")
	public Car createHelloCar() {
		Car car = new Car();
		car.setName("hello");
		car.setYear("1989");
		return car;
	}
	
	@Bean(name="hi")
	public Car createHiCar() {
		Car car = new Car();
		car.setName("hi");
		car.setYear("1800");
		return car;
	}

}
