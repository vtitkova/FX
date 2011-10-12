package com.dmma.fxjai.web.gwt.core.client.services.factory;

import com.dmma.fxjai.web.gwt.core.client.services.AccountGWTService;
import com.dmma.fxjai.web.gwt.core.client.services.AccountGWTServiceAsync;
import com.dmma.fxjai.web.gwt.core.client.services.BarGWTService;
import com.dmma.fxjai.web.gwt.core.client.services.BarGWTServiceAsync;
import com.google.gwt.core.client.GWT;

public class ServiceFactory {
	
	private static AccountGWTServiceAsync   accountGWTService;
	private static BarGWTServiceAsync   barGWTService;
	
	
	public static AccountGWTServiceAsync getAccountService() {
		if(accountGWTService == null)
			accountGWTService = GWT.create(AccountGWTService.class);
		return accountGWTService;
	}
	
	public static BarGWTServiceAsync getBarService() {
		if(barGWTService == null)
			barGWTService = GWT.create(BarGWTService.class);
		return barGWTService;
	}
}
