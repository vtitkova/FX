package com.dmma.base.app.o2xml.test.fatory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dmma.base.app.o2xml.test.entity.Car;
import com.dmma.base.app.o2xml.test.entity.CarModel;
import com.dmma.base.app.o2xml.test.entity.CarStock;
import com.dmma.base.shared.shared.clazz.Currency;
import com.dmma.base.shared.shared.clazz.DateTime;
import com.dmma.base.shared.shared.clazz.Time;

public class CarStockFactory {
	
	
	
	public static CarStock generateCarStock(){
		CarStock carStock = new CarStock();
		
		carStock.setCreated(new DateTime());
		carStock.setDelery(new Time((new Date()).getTime() + 100100));
		carStock.setTotalAmount(new Currency(new Double(154.982)));
		
		carStock.setOwners(new ArrayList<String>(3));
		carStock.getOwners().add("Vera");
		carStock.getOwners().add("Dima");
		carStock.getOwners().add("Vova");
		
		carStock.setCars(generateCars());
		return carStock;
	}

	private static List<Car> generateCars() {
		List<Car> retVal = new ArrayList<Car>();
		for(int i = 0; i < carModels.size() ; i ++){
			retVal.add(generateCar(i));
		}
		return retVal;
	}

	private static Car generateCar(int i) {
		Car c = new Car();
		c.setId(i);
		c.setModel(carModels.get(i));
		c.setRegNumber("RR TT X"+i);
		c.setDate(new Date());
		return c;
	} 
	
	
	private static List<CarModel> carModels;
	static{
		carModels = new ArrayList<CarModel>();
		carModels.add(new CarModel("A6"   , "Audi"));
		carModels.add(new CarModel("350"  , "BMW"));
		carModels.add(new CarModel("Civic", "Honda"));
		carModels.add(new CarModel("A5"   , "Audi"));
		carModels.add(new CarModel("A4"   , "Audi"));
	}
	
	
	
}

