package com.dmma.base.gwt.client.mail.mailtemplateform;

import java.util.ArrayList;
import java.util.HashMap;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.AppEventManager;
import com.dmma.base.gwt.client.mail.MailTemplateSuggestion;
import com.dmma.base.gwt.client.mail.mailTemplates.MailTemplatesPresenter;
import com.dmma.base.gwt.client.presenter.IPresenter;
import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.rpc.MyAsyncCallback;
import com.dmma.base.gwt.client.services.factory.BaseServiceFactory;
import com.dmma.base.gwt.client.ui.dialog.AppDialog;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.dmma.base.gwt.shared.keys.AppEPC;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class MailTemplatePresenter implements IPresenter {  
	public static String PRESENTER_ID = "mailTemplate";

	private final HashMap<String, MailTemplateSuggestion> mailTemplateSuggestions; 
	private GetDataCallback  getDatacallback;
	private GetRegisteredTemplateKeysCallback getRegisteredTemplateKeys;
	
	private SaveCallback     saveCallback;
	private MailTemplateDisplay   display;

	/** we need to provide programmatic suggestions for template, 
	 * as only developers will know what templates will be used
	 * */
	public MailTemplatePresenter(HashMap<String, MailTemplateSuggestion> mailTemplateSuggestions){
		this.mailTemplateSuggestions = mailTemplateSuggestions;
		// WE ARE LAZY. WE WILL BE INITIALAZEY ONLY AFTER FIRST CALL
	}

	@Override
	public IPresenterDisplay getPresenterDisplay() {
		if(display == null)
			init();
		return display;
	}


	private void init() {
		display = new MailTemplateView(mailTemplateSuggestions);
		getDatacallback = new GetDataCallback();
		getRegisteredTemplateKeys = new GetRegisteredTemplateKeysCallback();
		
		this.display.getSaveButton().addClickHandler(new ClickHandler() {   
			public void onClick(ClickEvent event) {
				doSave();
			}
		});
	}

	private void doSave() {
		MailTemplateDTO template = display.getData();
		if(template!=null){
			display.setSaveRequested();
			if(saveCallback == null )
				saveCallback = new SaveCallback();
			BaseServiceFactory.getMailService().saveOrUpdateTemplate(template, saveCallback); 
		}
	}

	private class SaveCallback extends MyAsyncCallback<Integer>{
		@Override
		protected void afterFailure(){
			display.setSaved(false);	
		}
		@Override
		public void onSuccess(Integer result) {
			if(result != null){
				AppDialog.show(BaseMessages.MSG.saveOperationSuccessed(), AppDialog.INFORMATION_MESSAGE);
				display.setSaved(true);	
				AppEvent e = new AppEvent(MailTemplatesPresenter.PRESENTER_ID, this.getClass().getName());
				AppEventManager.get().changeHistory(e);
			}else{
				onFailure(null);
			}
		}
	}


	private void requestMailTemplate(Integer id) {
		display.setDataRequested();
		if(id != null){
			BaseServiceFactory.getMailService().findTemplateById(id,getDatacallback); 
		}else{
			requestRegisteredTemplateKeys();
		}
	}
	private class GetDataCallback extends MyAsyncCallback<MailTemplateDTO>{
		@Override
		public void onSuccess(MailTemplateDTO result) {
			display.setData(result); 
		}
	}
	
	
	private void requestRegisteredTemplateKeys() {
			BaseServiceFactory.getMailService().findAllTemplateNames(getRegisteredTemplateKeys); 
	}
	private class GetRegisteredTemplateKeysCallback extends MyAsyncCallback<ArrayList<String>>{
		@Override
		public void onSuccess(ArrayList<String> result) {
			display.setRegisteredTemplateKeys(result); 
			display.setData(null);
		}
	}
	
	@Override
	public void applyNewParams(AppEvent e) {
		Integer id = e.getParamAsInteger(AppEPC.ID);
		
		
		requestMailTemplate(id);
	}

	@Override
	public String getPresenterId() {
		return PRESENTER_ID;
	}

}
