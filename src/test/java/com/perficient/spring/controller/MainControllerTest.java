package com.perficient.spring.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.spring.AtcJavaSpringRestApplication;
import com.perficient.spring.model.Car;
import com.perficient.spring.model.PingResponse;


/**
 * JUnit test class for the ManagerController class.
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = AtcJavaSpringRestApplication.class)
@AutoConfigureMockMvc
class MainControllerTest {
	@Autowired MockMvc mockController;

	protected String obtainGetResponseString(String url) throws Exception, UnsupportedEncodingException {
		MvcResult result = getMockController().perform(get(url).contentType(MediaType.APPLICATION_JSON)).andReturn();
		String contentAsString = result.getResponse().getContentAsString();
		return contentAsString;
	}

	protected <T> List<T> obtainGetResponseAsList(String url, Class<T> type)
			throws Exception, UnsupportedEncodingException, JsonProcessingException, JsonMappingException {
				ObjectMapper mapper = new ObjectMapper();
				return mapper.readValue(obtainGetResponseString(url), 
						mapper.getTypeFactory().constructCollectionType(List.class, type));
	}
	
	protected <T> T obtainGetResponse(String url, Class<T> type)
			throws Exception, UnsupportedEncodingException, JsonProcessingException, JsonMappingException {
		return new ObjectMapper().readValue(obtainGetResponseString(url),type);
	}
	
	protected <T> T obtainPostResponse(String url, Object body, Class<T> type) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		MvcResult result = getMockController().perform(
				post(url).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(body))).andReturn();
		return mapper.readValue(result.getResponse().getContentAsString(), type);
	}
	
	protected <T> List<T>  obtainPostResponseAsList(String url, Object body, Class<T> type) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		MvcResult result = getMockController().perform(
				post(url).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(body))).andReturn();
		return mapper.readValue(result.getResponse().getContentAsString(), mapper.getTypeFactory().constructCollectionType(List.class, type));
	}
	
	@Test
	public void testPing() throws Exception{
		PingResponse pong = obtainGetResponse("/ping", PingResponse.class);
		assertEquals("pong", pong.getResponse());
	}

	@Test
	public void testGetHelloCar() throws Exception{
		Car helloCar = obtainGetResponse("/hello",Car.class);
		assertEquals("hello", helloCar.getName());
		assertEquals("1989", helloCar.getYear());
	}
	
	@Test
	public void testGetHiCar() throws Exception{
		Car helloCar = obtainGetResponse("/hi",Car.class);
		assertEquals("hi", helloCar.getName());
		assertEquals("1800", helloCar.getYear());
	}
	
	@Test
	public void testGetCars() throws Exception{
		List<Car> cars = obtainGetResponseAsList("/cars",Car.class);
		assertTrue(cars.size() > 0);
	}
	
	@Test
	public void testAddCar() throws Exception{
		Car car = new Car();
		car.setName("Test");
		car.setYear("2022");
		List<Car> cars = obtainPostResponseAsList("/cars", car, Car.class);
		assertTrue(cars.size() > 0);
		assertTrue(cars.contains(car));
	}
	
	@Test
	public void testGetCarsQueryStringParameter() throws Exception{
		List<Car> cars = obtainGetResponseAsList("/cars?name=hello",Car.class);
		assertTrue(cars.size() > 0);
		for (Car car : cars) {
			assertEquals("hello", car.getName());
			assertEquals("1989", car.getYear());
		}
	}
	
	private MockMvc getMockController() {
		return mockController;
	}
	
}