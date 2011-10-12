package com.dmma.fxjai.web.gwt.admin.client.mvp.bars;

import java.util.ArrayList;
import java.util.List;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.AppEventManager;
import com.dmma.base.gwt.client.presenter.IPresenter;
import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.client.rpc.MyAsyncCallback;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.base.gwt.shared.keys.AppEPC;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.filters.BarSearchFilter;
import com.dmma.fxjai.shared.shared.keys.FxEPC;
import com.dmma.fxjai.shared.shared.types.PeriodType;
import com.dmma.fxjai.shared.shared.types.SymbolType;
import com.dmma.fxjai.web.gwt.core.client.services.factory.ServiceFactory;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class BarsPresenter implements IPresenter{  
	public static final String PRESENTER_ID    = "bars";
	
	private AppEvent defParams;
	private GetDataCallback               getDatacallback;
	private GetRegisteredAccountsCallback getRegisteredAccountsCallback;
	private BarsDisplay          display;
	
	public BarsPresenter(){
		// WE ARE LAZY. WE WILL BE INITIALAZEY ONLY AFTER FIRST CALL
	}

	
	@Override
	public IPresenterDisplay getPresenterDisplay() {
		if(display ==null){
			init();
		}
		return display;
	}
	
	private void init() {
		display = new BarsView();
		getDatacallback = new GetDataCallback();
		getRegisteredAccountsCallback = new GetRegisteredAccountsCallback();
		this.display.getSearchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doChangeHistory();
				requestBars();
			}
		});
	}
	
	private void doChangeHistory() {
		BarSearchFilter filter = display.getBarSearchFilter();
		if(!filter.isValid())
			return;
		AppEvent e = new AppEvent(PRESENTER_ID, this.getClass().getName());
		if(filter.getAccountId() != null)
			e.addParam(AppEPC.ID, filter.getAccountId());
		if(filter.getSymbol() != null)
			e.addParam(FxEPC.SYMBOL, filter.getSymbol().getId());
		if(filter.getPeriod() != null)
			e.addParam(FxEPC.PERIOD, filter.getPeriod().getId());
		if(filter.getFrom()!=null)
			e.addParam(AppEPC.FROM_DATE, filter.getFrom());
		if(filter.getTo()!=null)
			e.addParam(AppEPC.TO_DATE,   filter.getTo());
		defParams = e;
		AppEventManager.get().changeHistory(defParams, false);
	}
	
	@Override
	public void applyNewParams(AppEvent e) {
		if(e==null) e = new AppEvent(PRESENTER_ID, this.getClass().getName());
		defParams = e;
		display.setSelectedSymbolId(defParams.getParamAsInteger(FxEPC.SYMBOL));
		display.setSelectedPeriodId(defParams.getParamAsInteger(FxEPC.PERIOD));
		display.setFrom(defParams.getParamAsInteger(AppEPC.FROM_DATE));
		display.setTo(defParams.getParamAsInteger(AppEPC.TO_DATE));
		requestRegisteredAccounts();
		display.setSelectedAccountId(defParams.getParamAsInteger(AppEPC.ID));

		requestBars();
	}



	private void requestBars() {
		BarSearchFilter filter = new BarSearchFilter();
		filter.setAccountId(defParams.getParamAsInteger(AppEPC.ID));
		filter.setSymbol(SymbolType.findById(defParams.getParamAsInteger(FxEPC.SYMBOL)));
		filter.setPeriod(PeriodType.findById(defParams.getParamAsInteger(FxEPC.PERIOD)));
		
		filter.setFrom(defParams.getParamAsInteger(AppEPC.FROM_DATE));
		filter.setTo(defParams.getParamAsInteger(AppEPC.TO_DATE));
		
		if(filter.isValid()){
			display.setDataRequested();
			ServiceFactory.getBarService().find(filter, getDatacallback); 
		}else{
			display.setData(null);
		}
	}
	private class GetDataCallback extends MyAsyncCallback<List<BarDTO>>{
		@Override
		public void onSuccess(List<BarDTO> result) {
			display.setData(result); 
		}
	}
	
	private void requestRegisteredAccounts() {
		display.setRegisteredAccountsRequested();
		ServiceFactory.getAccountService().findAllShort(getRegisteredAccountsCallback); 
	}
	private class GetRegisteredAccountsCallback extends MyAsyncCallback<ArrayList<ListBoxDTO>>{
		@Override
		public void onSuccess(ArrayList<ListBoxDTO> result) {
			display.setRegisteredAccounts(result); 
		}
	}
	
	
	
	
	@Override
	public String getPresenterId() {
		return PRESENTER_ID;
	}


}
