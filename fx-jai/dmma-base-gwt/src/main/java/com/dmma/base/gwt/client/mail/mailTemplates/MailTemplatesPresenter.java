package com.dmma.base.gwt.client.mail.mailTemplates;

import java.util.ArrayList;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.presenter.IPresenter;
import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.client.rpc.MyAsyncCallback;
import com.dmma.base.gwt.client.services.factory.BaseServiceFactory;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class MailTemplatesPresenter implements IPresenter{  
	public static final String PRESENTER_ID = "mailTemplates";
	
	private GetDataCallback        getDatacallback;
	private MailTemplatesDisplay   display;

	public MailTemplatesPresenter(){
		// WE ARE LAZY. WE WILL BE INITIALAZEY ONLY AFTER FIRST CALL
	}

	
	@Override
	public IPresenterDisplay getPresenterDisplay() {
		if(display ==null)
			init();
		return display;
	}
	
	private void init() {
		display = new MailTemplatesView();
		getDatacallback = new GetDataCallback();
		
		this.display.getRefreshButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				requestMailTemplates();
			}
		});
	}
	
	@Override
	public void applyNewParams(AppEvent e) {
		requestMailTemplates();
	}

	private void requestMailTemplates() {
		display.setDataRequested();
		BaseServiceFactory.getMailService().findAllTemplates(getDatacallback); 
	}

	private class GetDataCallback extends MyAsyncCallback<ArrayList<MailTemplateDTO>>{
		@Override
		public void onSuccess(ArrayList<MailTemplateDTO> result) {
			display.setData(result); 
		}
	}
	
	@Override
	public String getPresenterId() {
		return PRESENTER_ID;
	}
}
