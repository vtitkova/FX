package com.dmma.fxjai.web.gwt.core.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface FXMessages extends Constants{
	public static final FXMessages  MSG = (FXMessages)     GWT.create(FXMessages.class);
		
	public String menuHome();
	public String menuBars();
	public String menuBarsVisual();
	public String menuSetups();
	public String welcomeMailTemplate();
	
	public String menuAccounts();
	public String account();
	public String accountType();
	public String accountUserName();
	public String accountServerName();
	public String periodType();
	public String symbolType();
	
	
}
