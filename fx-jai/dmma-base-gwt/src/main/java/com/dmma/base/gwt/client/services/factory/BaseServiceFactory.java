package com.dmma.base.gwt.client.services.factory;

import com.dmma.base.gwt.client.services.ClientContextGWTService;
import com.dmma.base.gwt.client.services.ClientContextGWTServiceAsync;
import com.dmma.base.gwt.client.services.MailGWTService;
import com.dmma.base.gwt.client.services.MailGWTServiceAsync;
import com.google.gwt.core.client.GWT;

public class BaseServiceFactory {
	
	private static ClientContextGWTServiceAsync   clientContextService;
	private static MailGWTServiceAsync            mailService;
	
	
	public static ClientContextGWTServiceAsync getClientContextService() {
		if(clientContextService == null)
			clientContextService = GWT.create(ClientContextGWTService.class);
		return clientContextService;
	}
	
	public static MailGWTServiceAsync getMailService() {
		if(mailService == null)
			mailService = GWT.create(MailGWTService.class);
		return mailService;
	}
	

}
