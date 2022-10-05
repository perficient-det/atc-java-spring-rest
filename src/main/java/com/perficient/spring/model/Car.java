package com.perficient.spring.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {

	@JsonProperty
	private String name;
	
	@JsonProperty
	private String year;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, year);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(name, other.name) && Objects.equals(year, other.year);
	}
	
}
