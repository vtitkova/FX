package com.dmma.base.gwt.client.utils;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

public class BaseFormats {

	public static NumberFormat    CURRENCY_FORMAT;
	public static NumberFormat    NUMBER_FORMAT;
	public static DateTimeFormat  DATE_FORMAT;
	public static DateTimeFormat  DATE_TIME_FORMAT;
	public static DateTimeFormat  TIME_FORMAT;

	static {
		CURRENCY_FORMAT    = NumberFormat.getFormat("0.00");
		DATE_FORMAT        = DateTimeFormat.getFormat("dd.MM.yyyy");
		DATE_TIME_FORMAT   = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm");
		TIME_FORMAT        = DateTimeFormat.getFormat("HH:mm"); 
		NUMBER_FORMAT      = NumberFormat.getFormat("00000");
	}

	public static String getFormattedCurency(Double amount) {
		if(amount==null) return "0.00";
		return CURRENCY_FORMAT.format(amount);
	}

	public static String getFormattedNumber(Integer nr) {
		if(nr==null) return " - ";
		return NUMBER_FORMAT.format(nr);
	}

	public static String getFormattedDate(Date date) {
		if(date==null) return " - ";
		return DATE_FORMAT.format(date);
	}

	public static String getFormattedDateTime(Date date) {
		if(date==null) return " - ";
		return DATE_TIME_FORMAT.format(date);
	}

	public static String getFormattedTime(Date time) {
		if(time==null) return " - ";
		return TIME_FORMAT.format(time);
	}
	
	
	public static String getFormattedInterval(Date from , Date to) {
		return getFormattedDateTime(from) + " - " + getFormattedDateTime(to);
	}
	
	
}
