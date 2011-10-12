package com.dmma.base.shared.shared.clazz;

import java.util.Date;

/**
 * This is extension of java.util.Date to display only time 
 */
public class Time extends Date{
	private static final long serialVersionUID = 615497432593223144L;
	
	public Time() {
		super();
	}

	public Time(Date date) {
		super(date.getTime());
	}
	
	public Time(Long time) {
		super(time);
	}
	
}
