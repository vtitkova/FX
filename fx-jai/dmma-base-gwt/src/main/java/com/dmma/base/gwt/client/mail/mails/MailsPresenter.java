package com.dmma.base.gwt.client.mail.mails;

import java.util.ArrayList;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.AppEventManager;
import com.dmma.base.gwt.client.presenter.IPresenter;
import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.client.rpc.MyAsyncCallback;
import com.dmma.base.gwt.client.services.factory.BaseServiceFactory;
import com.dmma.base.gwt.client.ui.pager.PagerListener;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.base.gwt.shared.dtos.PagedRequestDTO;
import com.dmma.base.gwt.shared.dtos.PagedResponseDTO;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.gwt.shared.filters.MailSearchFilter;
import com.dmma.base.gwt.shared.keys.AppEPC;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class MailsPresenter implements IPresenter, PagerListener{  
	public static final String PRESENTER_ID    = "mail";
	public static final Integer defItemsOnPage = 20;
	
	private AppEvent defParams;
	private GetDataCallback         getDatacallback;
	private GetAllTemplatesCallback getAllTemplatesCallback;
	private MailsDisplay   display;
	
	public MailsPresenter(){
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
		display = new MailsView();
		getDatacallback = new GetDataCallback();
		getAllTemplatesCallback = new GetAllTemplatesCallback();
		this.display.getSearchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doChangeHistory();
				requestMails();
			}
		});
		display.setPagerListener(this);
	}
	
	private void doChangeHistory() {
		MailSearchFilter wrapper = display.getMailSearchFilter();
		AppEvent e = new AppEvent(PRESENTER_ID, this.getClass().getName());
		if(wrapper.getStatusIdId()!=null)
			e.addParam(AppEPC.statusId, wrapper.getStatusIdId());
		if(wrapper.getMailTemplateId() != null)
			e.addParam(AppEPC.TEMPLATE_NAME, wrapper.getMailTemplateId());
		
		if(wrapper.getDateFrom()!=null)
			e.addParam(AppEPC.FROM_DATE, wrapper.getDateFrom());
		if(wrapper.getDateTo()!=null)
			e.addParam(AppEPC.TO_DATE,   wrapper.getDateTo());
		defParams = e;
		AppEventManager.get().changeHistory(defParams, false);
	}
	
	@Override
	public void applyNewParams(AppEvent e) {
		if(e==null) e = new AppEvent(PRESENTER_ID, this.getClass().getName());
		defParams = e;
		display.setSelectedStatusId(defParams.getParamAsInteger(AppEPC.statusId));
		display.setDateFrom(defParams.getParamAsDate(AppEPC.FROM_DATE));
		display.setDateTo(defParams.getParamAsDate(AppEPC.TO_DATE));
		
		requestAvailableTemplateNames();
		display.setSelectedTemplateId(defParams.getParamAsInteger(AppEPC.TEMPLATE_NAME));

		requestMails();
	}



	private void requestMails() {
		MailSearchFilter filter = new MailSearchFilter();
		filter.setDateFrom(defParams.getParamAsDate(AppEPC.FROM_DATE));
		filter.setDateTo(defParams.getParamAsDate(AppEPC.TO_DATE));
		filter.setMailTemplateId(defParams.getParamAsInteger(AppEPC.TEMPLATE_NAME));
		filter.setStatusId(defParams.getParamAsInteger(AppEPC.statusId));
		
		PagedRequestDTO<MailSearchFilter> request = new PagedRequestDTO<MailSearchFilter>();
		request.setFilter(filter);
		
		request.setPage(defParams.getParamAsInteger(AppEPC.PAGE));
		
		Integer iop = defParams.getParamAsInteger(AppEPC.ITEMS_ON_PAGE);
		if(iop == null)
			iop = defItemsOnPage;
		request.setItemsOnPage(iop);
		
		display.setDataRequested();
		BaseServiceFactory.getMailService().findAllMails(request, getDatacallback); 
	}
	private class GetDataCallback extends MyAsyncCallback<PagedResponseDTO<MailDTO>>{
		@Override
		public void onSuccess(PagedResponseDTO<MailDTO> result) {
			display.setData(result); 
		}
	}
	
	private void requestAvailableTemplateNames() {
		display.setAvailableTemplatesRequested();
		BaseServiceFactory.getMailService().findAllTemplateListBoxDTOs(getAllTemplatesCallback); 
	}
	private class GetAllTemplatesCallback extends MyAsyncCallback<ArrayList<ListBoxDTO>>{
		@Override
		public void onSuccess(ArrayList<ListBoxDTO> result) {
			display.setAvailableTemplates(result); 
		}
	}
	
	
	
	
	@Override
	public String getPresenterId() {
		return PRESENTER_ID;
	}


	@Override
	public void onItemsOnPageChanged(Integer newPageValue, Integer newItemsOnPageValue) {
		if(defParams!=null){
			defParams.addParam(AppEPC.PAGE, newPageValue);
			defParams.addParam(AppEPC.ITEMS_ON_PAGE, newItemsOnPageValue);
			AppEventManager.get().changeHistory(defParams, false);
			requestMails();
		}
	}


	@Override
	public void onPageChanged(Integer newValue) {
		if(defParams!=null){
			defParams.addParam(AppEPC.PAGE, newValue);
			AppEventManager.get().changeHistory(defParams, false);
			requestMails();
		}
	}
	






}
