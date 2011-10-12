package com.dmma.fxjai.web.gwt.core.client.services;

import java.util.ArrayList;

import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.fxjai.web.gwt.core.shared.entities.AccountDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("AccountGWTService")
public interface AccountGWTService extends RemoteService {
	public ArrayList<AccountDTO> findAll();
	public ArrayList<ListBoxDTO> findAllShort();
}
