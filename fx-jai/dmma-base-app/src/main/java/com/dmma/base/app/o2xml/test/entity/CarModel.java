package com.dmma.base.app.o2xml.test.entity;

import com.dmma.base.app.o2xml.annotations.O2XElement;

@O2XElement()
public class CarModel {
	private String  model;
	private String  brand;
	
	public CarModel() {
	}

	public CarModel(String model, String brand) {
		this.model = model;
		this.brand = brand;
	}

	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	
}
