package com.dmma.fxjai.web.gwt.core.client.services;

import java.util.List;

import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.filters.BarSearchFilter;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("BarGWTService")
public interface BarGWTService extends RemoteService {
	public List<BarDTO> find(BarSearchFilter filter);
}
