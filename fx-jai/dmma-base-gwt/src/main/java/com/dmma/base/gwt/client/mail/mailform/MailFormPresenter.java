package com.dmma.base.gwt.client.mail.mailform;

import java.util.ArrayList;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.AppEventManager;
import com.dmma.base.gwt.client.mail.mails.MailsPresenter;
import com.dmma.base.gwt.client.presenter.IPresenter;
import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.rpc.MyAsyncCallback;
import com.dmma.base.gwt.client.services.factory.BaseServiceFactory;
import com.dmma.base.gwt.client.ui.dialog.AppDialog;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.dmma.base.gwt.shared.keys.AppEPC;
import com.dmma.base.shared.shared.mail.types.MailSendingLogType;
import com.dmma.base.shared.shared.mail.types.MailSendingMethodType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class MailFormPresenter implements IPresenter{  
	public static final String PRESENTER_ID = "mailForm";
	
	private GetDataCallback                getDatacallback;
	private GetAvailableTemplatesCallback  getAvailableTemplatesCallback;
	private SendMailCallback               sendMailCallback;
	
	private MailFormDisplay  display;
	private Integer defId; 
	
	public MailFormPresenter(){
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
		display = new MailFormView();
		getDatacallback               = new GetDataCallback();
		getAvailableTemplatesCallback = new GetAvailableTemplatesCallback();
		sendMailCallback              = new SendMailCallback();
		
		this.display.getSendButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				sendMail();
			}
		});
	}
	
	
	@Override
	public void applyNewParams(AppEvent e) {
		defId = e.getParamAsInteger(AppEPC.ID);
		requestAvailableTemplates();
	}

	
	


	private void requestAvailableTemplates() {
		BaseServiceFactory.getMailService().findAllTemplates(getAvailableTemplatesCallback); 
	}
	private class GetAvailableTemplatesCallback extends MyAsyncCallback<ArrayList<MailTemplateDTO>>{
		@Override
		public void onSuccess(ArrayList<MailTemplateDTO> availableTemplates) {
			display.setAvailableTemplates(availableTemplates);
			if(defId!=null){
				requestMail(defId);
			}
		}
	}
	
	private void requestMail(Integer defId) {
		BaseServiceFactory.getMailService().findMailById(defId, getDatacallback); 
	}
	private class GetDataCallback extends MyAsyncCallback<MailDTO>{
		@Override
		public void onSuccess(MailDTO result) {
			display.setData(result); 
		}
	}
	
	private void sendMail() {
		MailDTO mail = display.getData();
		if(mail != null){
			MailSendingMethodType sendType = MailSendingMethodType.isStandart;
			MailSendingLogType logType = MailSendingLogType.isLog;
			if(!display.getMakeLog()){
				logType = MailSendingLogType.isNoLog;
			}
			BaseServiceFactory.getMailService().sendMail(mail, sendType, logType, sendMailCallback); 
		}
	}
	private class SendMailCallback extends MyAsyncCallback<Integer>{
		@Override
		public void onSuccess(Integer result) {
			AppDialog.show(BaseMessages.MSG.saveOperationSuccessed(), AppDialog.INFORMATION_MESSAGE);
			AppEvent e = new AppEvent(MailsPresenter.PRESENTER_ID, this.getClass().getName());
			AppEventManager.get().changeHistory(e);
		}
	}
	
	@Override
	public String getPresenterId() {
		return PRESENTER_ID;
	}
	






}
