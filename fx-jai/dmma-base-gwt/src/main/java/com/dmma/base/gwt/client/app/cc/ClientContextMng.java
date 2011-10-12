package com.dmma.base.gwt.client.app.cc;

import java.util.HashMap;
import java.util.Map.Entry;

import com.dmma.base.gwt.client.event.AppEvent;
import com.dmma.base.gwt.client.event.AppEventManager;
import com.dmma.base.gwt.client.services.ClientContextGWTService;
import com.dmma.base.gwt.client.services.ClientContextGWTServiceAsync;
import com.dmma.base.gwt.shared.dtos.ClientContextDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ClientContextMng {
	public static String APP_MODEL_SYNCHRONISED =  "APP_MODEL_SYNCHRONISED";


	/** instance of application model */
	private static ClientContextMng INSTANCE; 

	/** remote service to manage appModel */
	private ClientContextGWTServiceAsync appModelService = GWT.create(ClientContextGWTService.class);

	private ClientContextDTO appModel;

	private ClientContextMng() {
		appModel = new ClientContextDTO();
	}


	public static ClientContextMng get(){
		if(INSTANCE==null){
			INSTANCE = new ClientContextMng();
			INSTANCE.requestModel();
		}
		return INSTANCE;
	}


	/**
	 * request model update from server (model stored in session)*/
	private void requestModel(){
		appModelService.getAppModel(requestModelCallback);
	}

	private void modelReceived(ClientContextDTO result){
		if(result!=null){
			appModel  = result;
		}
		AppEvent event = new AppEvent(ClientContextDTO.APP_MODEL, this.getClass().getName());
		AppEventManager.get().fireEvent(event);
	}
	
	public Boolean isSynchronised() {
		return appModel.getIsSynchronised();
	}
	
	
	/**
	 * Setup parameter map to application model
	 * and update server stored model
	 * @param name - key name of parameter, see <code>ReceiptWizardConstants</code>
	 * @param value - parameter value, could be NULL if you want to remove this parameter from CCM
	 */
	public void setAndSyncParam(String name, String value) {
		appModel.addParam(name, value);
		HashMap<String,String> paramMap = new HashMap<String, String>();
		paramMap.put(name, value);
		appModelService.updateOrSetParam(paramMap, paramUpdateCallback);
	}

	/**
	 * Setup parameters map to application model
	 * and update server stored model
	 * @param params - map of parameters: <Name, Value> 
	 * value could be NULL if you want to remove this parameter from CCM 
	 * 
	 */
	public void setAndSyncParams(HashMap<String,String> params) {
		if(params!=null&&params.size()>0){
			for(Entry<String, String> entry:params.entrySet()){
				appModel.addParam(entry.getKey(), entry.getValue());
			}
			appModelService.updateOrSetParam(params, paramUpdateCallback);
		}
	}

	
		

	private void paramUpdated(Boolean result) {
	}



	public Integer getParamAsInteger(String name) {
		return appModel.getParamAsInteger(name);
	}

	public String getParamAsString(String name) {
		return appModel.getParamAsString(name);
	}




	public ClientContextDTO getModel(){
		return appModel;
	}

	private ParamUpdateCallback paramUpdateCallback = new ParamUpdateCallback(); 
	private class ParamUpdateCallback implements AsyncCallback<Boolean>{
		@Override
		public void onFailure(Throwable arg0) {
			paramUpdated(false);
		}
		@Override
		public void onSuccess(Boolean result) {
			paramUpdated(result);
		}
	}

	private RequestModelCallback requestModelCallback = new RequestModelCallback(); 
	private class RequestModelCallback implements AsyncCallback<ClientContextDTO>{
		@Override
		public void onFailure(Throwable caught) {
			modelReceived(null);
		}

		@Override
		public void onSuccess(ClientContextDTO result) {
			modelReceived(result);		
		}
	}
	

}
