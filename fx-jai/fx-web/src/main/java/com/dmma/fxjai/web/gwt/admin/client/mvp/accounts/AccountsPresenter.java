package com.dmma.fxjai.web.gwt.admin.client.mvp.accounts;

import java.util.ArrayList;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.presenter.IPresenter;
import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.client.rpc.MyAsyncCallback;
import com.dmma.fxjai.web.gwt.core.client.services.factory.ServiceFactory;
import com.dmma.fxjai.web.gwt.core.shared.entities.AccountDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class AccountsPresenter implements IPresenter{  
	public static final String PRESENTER_ID = "accounts";
	
	private GetDataCallback   getDatacallback;
	private AccountsDisplay   display;

	public AccountsPresenter(){
		// WE ARE LAZY. WE WILL BE INITIALAZEY ONLY AFTER FIRST CALL
	}

	
	@Override
	public IPresenterDisplay getPresenterDisplay() {
		if(display ==null)
			init();
		return display;
	}
	
	private void init() {
		display = new AccountsView();
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
		ServiceFactory.getAccountService().findAll(getDatacallback); 
	}

	private class GetDataCallback extends MyAsyncCallback<ArrayList<AccountDTO>>{
		@Override
		public void onSuccess(ArrayList<AccountDTO> result) {
			display.setData(result); 
		}
	}
	
	@Override
	public String getPresenterId() {
		return PRESENTER_ID;
	}
}
