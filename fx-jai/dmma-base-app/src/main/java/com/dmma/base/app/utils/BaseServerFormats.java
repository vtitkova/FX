package com.dmma.base.app.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dmma.base.shared.shared.clazz.Currency;


public class BaseServerFormats {
	public static SimpleDateFormat DATE_TIME_FORMAT  = new SimpleDateFormat("dd.MM.yyyy HH:mm");
	public static SimpleDateFormat TIME_FORMAT       = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat DATE_FORMAT       = new SimpleDateFormat("dd.MM.yyyy");
	public static NumberFormat     CURRENCY_FORMAT   = new DecimalFormat("#0.00");
	
	public static String getFormattedDateTime(Date date) {
		if(date==null) return "-";
		return DATE_TIME_FORMAT.format(date);
	}

	public static String getFormattedTime(Date date) {
		if(date==null) return "-";
		return TIME_FORMAT.format(date);
	}

	public static String getFormattedDate(Date date) {
		if(date==null) return "-";
		return DATE_FORMAT.format(date);
	}
	
	public static String getFormattedCurrency(Double value) {
		if(value==null) return "-";
		return CURRENCY_FORMAT.format(value);
	}
	
	public static String getFormattedCurrency(Currency value) {
		if(value==null) return "-";
		return getFormattedCurrency(value.getValue());
	}
	
	

	
}
