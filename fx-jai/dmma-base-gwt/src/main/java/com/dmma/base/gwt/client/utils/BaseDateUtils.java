package com.dmma.base.gwt.client.utils;

import java.util.Date;

public class BaseDateUtils {
	
	public static Date getToday(boolean toMorning){
		if(toMorning)
			return addDays(new Date(), 0);  
		else{
			return new Date(addDays(new Date(), 1).getTime() - 1);
		} 
	}

	
	
		
	/**
	 * Add days to the Date object.
	 * !!! will trunc time to 00:00:000
	 * @param date
	 *            The Date to modify
	 * @param days
	 *            Number of day to add
	 * @return The modified Date object
	 */
	@SuppressWarnings("deprecation")
	public static Date addDays(Date date, int days) {
		return new Date(date.getYear(), date.getMonth(), date.getDate() + days);
	}

	/**
	 * Add months to the Date object.
	 * 
	 * @param date
	 *            The Date to modify
	 * @param months
	 *            Number of month to add
	 * @return The modified Date object
	 */
	@SuppressWarnings("deprecation")
	public static Date addMonths(Date date, int months) {
		int day = date.getDate();
		int nextMonthLastDay = getMonthLastDay(new Date(date.getYear(), date.getMonth() + months, 1)).getDate(); 
		return new Date(date.getYear(), date.getMonth() + months, day > nextMonthLastDay ? nextMonthLastDay : day);
	}

	/**
	 * Test if two Date objects represent the same day. It tests if the days,
	 * the months and the years are equals.
	 * 
	 * @param date1
	 *            First Date
	 * @param date2
	 *            Second Date
	 * @return true if the days are the same
	 */
	@SuppressWarnings("deprecation")
	public static boolean areEquals(Date date1, Date date2) {
		return date1.getDate() == date2.getDate()
				&& date1.getMonth() == date2.getMonth()
				&& date1.getYear() == date2.getYear();
	}

	/**
	 * Return a Date object with represents the first day of a month contained
	 * in another Date object.
	 * 
	 * @param date The Date containing the month
	 * @return The first day of the month
	 */
	@SuppressWarnings("deprecation")
	public static Date getMonthFirstDay(Date date) {
		Date current = date;
		while (current.getDate() != 1) {
			current = new Date(current.getYear(), current.getMonth(), current
					.getDate() - 1);
		}
		return current;
	}
	
	/**
	 * Returns the first day of the current Month.
	 * @return Date pointing to the first day 
	 */
	public static Date getThisMonthFirstDay() {
		return getMonthFirstDay(new Date());
	}
	
	

	@SuppressWarnings("deprecation")
	public static Date getMonthLastDay(Date date){
		Date firstDay = getMonthFirstDay(date);
		return addDays(new Date(firstDay.getYear(), firstDay.getMonth() + 1, firstDay.getDate()), -1);
	}		
	
	/**
	 * Returns the place of the day in the week.
	 * Example : sunday = 0, monday = 1 ....
	 * Depends on the locale. 
	 * @param day The day
	 * @return The place of the day
	 */
	@SuppressWarnings("deprecation")
	public static int getWeekDayIndex(Date day) {
		Date mond =  new Date(2010-1900, 0, 4); // first Monday in 2010 
		day = addDays(day, 0);  // truncate to date only, set time to 00:00:000
		
		long wday = (day.getTime() - mond.getTime())/1000/60/60/24 +1;
		int index  = (int) wday%7;
		return index+1;  // mon = 1 , sun = 7
	}

	/**
	 * Returns the first day of the current week.
	 * @return Date pointing to the first day 
	 */
	public static Date getThisWeekFirstDay() {
		return getWeekFirstDay(new Date());
	}

	/**
	 * Returns the first day of the week containing a Date object.
	 * @param date The Date
	 * @return The Date pointing to the first day
	 */
	@SuppressWarnings("deprecation")
	public static Date getWeekFirstDay(Date date) {
		int index = getWeekDayIndex(date);
		//DateLocale local = (DateLocale) GWT.create(DateLocale.class);
		//int firstDay = local.getDAY_ORDER()[0];
		//while (current.getDay() != firstDay) {
		Date current = new Date(date.getYear(), date.getMonth(), date.getDate() -index+ 1);
		//}
		return current;
	}

	/**
	 * Test if a day is a weekend day.
	 * @param day The Date to test
	 * @return true if the Date is a weekend day
	 */
	@SuppressWarnings("deprecation")
	public static boolean isInWeekEnd(Date day) {
		int dayIndex = day.getDay();
		return (dayIndex == 0 | dayIndex == 6) ? true : false;
	}




	public static Date getLastWeekFirstDay() {
		Date date = getThisWeekFirstDay();
		date =  addDays(date, -1);
		return getWeekFirstDay(date);
	}




	public static Date getLastWeekLastDay() {
		Date d = getThisWeekFirstDay();
		return addDays(d, -1);
	}




	public static Date getLastMonthFirstDay() {
		Date date = getThisMonthFirstDay();
		date =  addDays(date, -1);
		return getMonthFirstDay(date);
	}




	public static Date getLastMonthLastDay() {
		Date d = getThisMonthFirstDay();
		return addDays(d, -1);
	}

	
	
	@SuppressWarnings("deprecation")
	public static int getDayInMonth(Date date){
		return date.getDate();
	}
	
	@SuppressWarnings("deprecation")
	public static int getMonthDayCount(Date date){
		Date d = getMonthFirstDay(date);
		d = addMonths(d, 1);
		d = addDays(d, -1);
		return d.getDate();
	}
	
	
	
}
