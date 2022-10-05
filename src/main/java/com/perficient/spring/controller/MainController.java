package com.perficient.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.perficient.spring.aspect.LogExecutionTime;
import com.perficient.spring.model.Car;
import com.perficient.spring.model.PingResponse;
import com.perficient.spring.service.CarService;

@RestController
public class MainController {
	
	@Autowired
	private CarService service;
	

	@GetMapping("/ping")
	public PingResponse ping() {
		return new PingResponse("pong");
	}
	
	@GetMapping("/hello")
	public Car findHelloCar() {
		return getService().getHelloCar();
	}
	
	@GetMapping("/hi")
	public Car findHiCar() {
		return getService().getHiCar();
	}
	
	@LogExecutionTime
	@GetMapping("/cars")
	public List<Car> findCars(@RequestParam(required = false) String name) throws Throwable {
		Thread.sleep(500);
		return getService().findCars(name);
	}
	
	@PostMapping("/cars")
	public List<Car> addCar(@RequestBody Car car) {
		return getService().addCar(car);
	}
	
	

	private CarService getService() {
		return service;
	}
}
