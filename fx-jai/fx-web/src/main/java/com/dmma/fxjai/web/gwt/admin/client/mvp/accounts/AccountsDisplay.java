package com.dmma.fxjai.web.gwt.admin.client.mvp.accounts;

import java.util.ArrayList;

import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.fxjai.web.gwt.core.shared.entities.AccountDTO;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface AccountsDisplay extends IPresenterDisplay {
	public void setData(ArrayList<AccountDTO> data);
	public void setDataRequested();
	public HasClickHandlers getRefreshButton();
}
