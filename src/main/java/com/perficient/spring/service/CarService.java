package com.perficient.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.perficient.spring.model.Car;

@Service
public class CarService {
	
	@Autowired
	@Qualifier("hi")
	private Car hiCar;

	@Autowired
	@Qualifier("hello")
	private Car helloCar;
	
	@Autowired
	private List<Car> cars;

	public Car getHiCar() {
		return hiCar;
	}

	public Car getHelloCar() {
		return helloCar;
	}

	public List<Car> getCars() {
		return cars;
	}

	public List<Car> addCar(Car car) {
		getCars().add(car);
		return getCars();
	}

	public List<Car> findCars(String name) {
		if (!StringUtils.hasText(name)) {
			return getCars();
		}
		List<Car> returnList = new ArrayList<>();
		for (Car car : getCars()) {
			if (car.getName().equals(name)) {
				returnList.add(car);
			}
		}
		return returnList;
	}

}
