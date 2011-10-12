package com.dmma.base.gwt.client.services;

import java.util.HashMap;

import com.dmma.base.gwt.shared.dtos.ClientContextDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientContextGWTServiceAsync {
	public void getAppModel(AsyncCallback<ClientContextDTO> callback);
	public void updateOrSetParam(HashMap<String,String> paramMap, AsyncCallback<Boolean> callback );
	public void pingSession(AsyncCallback<Boolean> callback);
	
}
