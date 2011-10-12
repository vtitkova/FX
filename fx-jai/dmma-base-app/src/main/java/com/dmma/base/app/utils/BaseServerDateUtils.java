package com.dmma.base.app.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BaseServerDateUtils {
	
	
	public static Date getToday(boolean toMorning){
		GregorianCalendar cal = new GregorianCalendar();
		if(toMorning){
			cal.set(GregorianCalendar.HOUR_OF_DAY, 0);
			cal.set(GregorianCalendar.MINUTE, 0);
			cal.set(GregorianCalendar.SECOND, 0);
			cal.set(GregorianCalendar.MILLISECOND, 0);
		}else{
			cal.set(GregorianCalendar.HOUR_OF_DAY, 23);
			cal.set(GregorianCalendar.MINUTE, 59);
			cal.set(GregorianCalendar.SECOND, 59);
			cal.set(GregorianCalendar.MILLISECOND, 999);
		} 
		return cal.getTime();
	}

	/**
	 * Return a Date object what represents the first day of a month contained
	 * in another Date object.
	 * 
	 * @param date The Date containing the month
	 * @return The first day of the month date
	 */
	public static Date getMonthFirstDate(Date date){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(GregorianCalendar.DATE, 1);
		return cal.getTime();
	}
	
	/**
	 * Return a Date object what represents the last day of a month contained
	 * in another Date object.
	 * 
	 * @param date The Date containing the month
	 * @return The last day of the month date
	 */
	public static Date getMonthLastDate(Date date){
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(GregorianCalendar.DATE, 1);
		cal.add(GregorianCalendar.MONTH, 1);
		cal.add(GregorianCalendar.DATE, -1);
		return cal.getTime();
	}
	
	
	

	/**
	 * Add days to the Date object.
	 * @param date The Date to modify
	 * @param days Number of day to add
	 * @return The modified Date object
	 */
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, days);
		return cal.getTime();
	}

	
	/**
	 * Add months to the Date object.
	 * @param date    The Date to modify
	 * @param months  Number of month to add
	 * @return The modified Date object
	 */
	public static Date addMonths(Date date, int months) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.MONTH, months);
		return cal.getTime();
	}

	
	
	/**
	 * Returns the first day of the current Month. time will be 00:00:00:000
	 * @return Date pointing to the first day 
	 */
	public static Date getThisMonthBeginingDate() {
		return getMonthFirstDate(getToday(true));
	}
	
	/**
	 * Returns the last day of the current Month. time will be 23:59:59:999
	 * @return Date pointing to the first day 
	 */
	public static Date getThisMonthEndDate() {
		return getMonthLastDate(getToday(false));
	}
	
	
	
	public static Date getMonthBegining(int monthsAgo){
		Date d = getThisMonthBeginingDate();
		return addMonths(d, -monthsAgo);
	}
	
	public static Date getMonthEnding(int monthsAgo){
		Date d = getToday(false);
		d = getMonthFirstDate(d);
		d = addMonths(d, -monthsAgo);
		return getMonthLastDate(d);
	}

	
	/**If dates are in one month then return 1 */
	public static Integer getMonthCount(Date dateA, Date dateB) {
		Date from;
		Date to;
		if(dateA.getTime()>dateB.getTime()){
			from = getMonthFirstDate(dateB);
			to = getMonthFirstDate(dateA);
		}else{
			from = getMonthFirstDate(dateA);
			to   = getMonthFirstDate(dateB);
		}
		int i = 1;
		while(!sameYearSameMonth(from, to)){
			i++;
			from = addMonths(from, 1);
		}
		return i;
	}

	private static boolean sameYearSameMonth(Date from, Date to) {
		GregorianCalendar calFrom = new GregorianCalendar();
		calFrom.setTime(from);
		GregorianCalendar calTo = new GregorianCalendar();
		calTo.setTime(to);
		
		if(calFrom.get(Calendar.YEAR)==calTo.get(Calendar.YEAR))
			if(calFrom.get(Calendar.MONTH)==calTo.get(Calendar.MONTH))
					return true;
		return false;
	}
	
	
	
	
	
}
