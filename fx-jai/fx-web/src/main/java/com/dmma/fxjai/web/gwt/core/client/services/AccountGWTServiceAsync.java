package com.dmma.fxjai.web.gwt.core.client.services;

import java.util.ArrayList;

import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.fxjai.web.gwt.core.shared.entities.AccountDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AccountGWTService</code>.
 */
public interface AccountGWTServiceAsync {
	public void findAll(AsyncCallback<ArrayList<AccountDTO>> callback);
	public void findAllShort(AsyncCallback<ArrayList<ListBoxDTO>> callback);
}
