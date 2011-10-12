package com.dmma.fxjai.web.gwt.admin.client.mvp.barsvisual;

import java.util.ArrayList;
import java.util.List;

import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.filters.BarSearchFilter;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface BarsVisualDisplay extends IPresenterDisplay {
	public void setRegisteredAccountsRequested();
	public void setRegisteredAccounts(ArrayList<ListBoxDTO> result);

	public void setData(List<BarDTO> data);
	public void setDataRequested();
	
	public HasClickHandlers getSearchButton();
	
	public void setSelectedAccountId(Integer accountId);
	public void setSelectedSymbolId(Integer symbolId);
	public void setSelectedPeriodId(Integer periodId);
	public void setFrom(Integer from);
	public void setTo(  Integer to);

	public BarSearchFilter getBarSearchFilter();

	
	
}
