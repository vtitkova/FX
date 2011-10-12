package com.dmma.base.app.o2xml.test.entity;

import java.util.Date;

import com.dmma.base.app.o2xml.annotations.O2XElement;

@O2XElement(nameInXml="car")
public class Car {
	private Integer  id;
	private CarModel model;
	private Date     date;
	private String   regNumber;
	
	public Car() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}


	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
