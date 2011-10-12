package com.dmma.base.shared.shared.clazz;

import java.util.Date;

/**
 * This is extension of java.util.Date to display Date And Time  
 */
public class DateTime extends Date{
	private static final long serialVersionUID = 615497432593223144L;
	
	public DateTime() {
		super();
	}	
	
	public DateTime(Date date) {
		super(date.getTime());
	}
	
	public DateTime(Long time) {
		super(time);
	}
	
}

