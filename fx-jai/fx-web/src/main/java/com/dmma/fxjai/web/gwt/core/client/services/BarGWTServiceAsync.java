package com.dmma.fxjai.web.gwt.core.client.services;

import java.util.List;

import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.filters.BarSearchFilter;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>BarGWTService</code>.
 */
public interface BarGWTServiceAsync {
	public void find(BarSearchFilter filter, AsyncCallback<List<BarDTO>> callback);
}
