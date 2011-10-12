package com.dmma.base.gwt.client.services;

import java.util.HashMap;

import com.dmma.base.gwt.shared.dtos.ClientContextDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ClientContextGWTService")
public interface ClientContextGWTService extends RemoteService{
	public ClientContextDTO getAppModel();
	public Boolean  updateOrSetParam(HashMap<String,String> paramMap );
	public Boolean pingSession();
	
}
