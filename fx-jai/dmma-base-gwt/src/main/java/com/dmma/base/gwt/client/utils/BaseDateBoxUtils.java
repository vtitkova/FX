package com.dmma.base.gwt.client.utils;

import com.google.gwt.user.datepicker.client.DateBox;

public class BaseDateBoxUtils {

	public static void setFormat(DateBox dateBox) {
		dateBox.setFormat(new DateBox.DefaultFormat(BaseFormats.DATE_FORMAT));
	}
	
	
}


