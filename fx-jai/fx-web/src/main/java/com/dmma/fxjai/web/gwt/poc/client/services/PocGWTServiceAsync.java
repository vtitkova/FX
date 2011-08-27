package com.dmma.fxjai.web.gwt.poc.client.services;

import java.util.ArrayList;

import com.dmma.fxjai.web.gwt.poc.shared.entities.PocDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>PocGWTService</code>.
 */
public interface PocGWTServiceAsync {
	public void findLastPocs(AsyncCallback<ArrayList<PocDTO>> callback);	
	public void saveOrUpdate(PocDTO entity, AsyncCallback<Integer> callback);
	
}
