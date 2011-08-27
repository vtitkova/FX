package com.dmma.fxjai.web.gwt.poc.client.services;

import java.util.ArrayList;

import com.dmma.fxjai.web.gwt.poc.shared.entities.PocDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("PocGWTService")
public interface PocGWTService extends RemoteService {
	public ArrayList<PocDTO>   findLastPocs();	
	public Integer             saveOrUpdate(PocDTO entity);
		 
}
