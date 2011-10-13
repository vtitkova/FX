package com.dmma.fxjai.web.gwt.admin.client.mvp.bars;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dmma.base.gwt.client.mail.mailform.MailFormPresenter;
import com.dmma.base.gwt.client.mail.meta.MailStatusCellRenderer;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.dmma.base.gwt.client.ui.etf.EtfTable;
import com.dmma.base.gwt.client.ui.etf.EtfTableModel;
import com.dmma.base.gwt.client.utils.BaseDateBoxUtils;
import com.dmma.base.gwt.client.utils.BaseListBoxUtils;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.filters.BarSearchFilter;
import com.dmma.fxjai.shared.shared.types.PeriodType;
import com.dmma.fxjai.shared.shared.types.SymbolType;
import com.dmma.fxjai.web.gwt.core.client.i18n.FXMessages;
import com.dmma.fxjai.web.gwt.core.client.meta.BarLightCM;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class BarsView extends Composite implements  BarsDisplay{
	interface MyUiBinder extends UiBinder<Widget, BarsView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	public static String VIEW_TITLE = FXMessages.MSG.menuBars();
	
	private boolean accountsRecived = false; 
	private Integer accountToBeSelected = null;
	
	
	@UiField ListBox accountLB;
	@UiField ListBox symbolLB;
	@UiField ListBox periodLB;
	@UiField DateBox fromDP;
	@UiField DateBox toDP;
	@UiField Button searchButton;
	
	@UiField(provided = true)
	EtfTable<BarDTO, BarLightCM> contentTable;
	BarLightCM META;
	EtfTableModel<BarDTO, BarLightCM> tableModel;
	
	MailStatusCellRenderer mailStatusCellRenderer; 
	
	public BarsView(){
		META = new BarLightCM();
		tableModel = new EtfTableModel<BarDTO, BarLightCM>(META);
		contentTable = new EtfTable<BarDTO, BarLightCM>(tableModel, false, true, MailFormPresenter.PRESENTER_ID);
		contentTable.setEditImageResource(BaseImages.IMG.editSmall());
		contentTable.setHeightDecreaseValue(122);
		initWidget(uiBinder.createAndBindUi(this));
		
		BaseDateBoxUtils.setFormat(fromDP);
		BaseDateBoxUtils.setFormat(toDP);
		
		accountLB.addItem(BaseMessages.MSG.all(), BaseListBoxUtils.SELECT_IND);
		
		periodLB.addItem(BaseMessages.MSG.select(),  BaseListBoxUtils.SELECT_IND);
		periodLB.addItem(PeriodType.MN1.getName(), PeriodType.MN1.getId().toString());
		periodLB.addItem(PeriodType.W1.getName(),  PeriodType.W1.getId().toString());
		periodLB.addItem(PeriodType.D1.getName(),  PeriodType.D1.getId().toString());
		periodLB.addItem(PeriodType.H4.getName(),  PeriodType.H4.getId().toString());
		periodLB.addItem(PeriodType.H1.getName(),  PeriodType.H1.getId().toString());
		
		symbolLB.addItem(BaseMessages.MSG.select(),    BaseListBoxUtils.SELECT_IND);
		symbolLB.addItem(SymbolType.EURUSD.toString(), SymbolType.EURUSD.getId().toString());
		symbolLB.addItem(SymbolType.GBPUSD.toString(), SymbolType.GBPUSD.getId().toString());
		
	}
	

	@Override
	public void setData(List<BarDTO> data) {
			tableModel.setObjects(data);
	}

	@Override
	public void setDataRequested() {
		tableModel.setRequesting();
	}

	@Override
	public String getCaption() {
		return VIEW_TITLE;
	}

	
	@Override
	public void setSelectedPeriodId(Integer periodId) {
		BaseListBoxUtils.setSelectedItemByValue(periodLB, periodId);
	}
	
	@Override
	public void setSelectedSymbolId(Integer symbolId) {
		BaseListBoxUtils.setSelectedItemByValue(symbolLB, symbolId);
	}
	
	@Override
	public BarSearchFilter getBarSearchFilter() {
		BarSearchFilter retVal = new BarSearchFilter();
		retVal.setOrderDesc(true);
		retVal.setAccountId(BaseListBoxUtils.getSelectedValueAsInteger(accountLB));
		retVal.setSymbol(SymbolType.findById(BaseListBoxUtils.getSelectedValueAsInteger(symbolLB)));
		retVal.setPeriod(PeriodType.findById(BaseListBoxUtils.getSelectedValueAsInteger(periodLB)));
		
		 
		retVal.setFrom(trimValue(fromDP.getValue()));
		retVal.setTo(  trimValue(toDP.getValue()));
		return retVal;
	}

	
	
	private Integer trimValue(Date value) {
		if(value == null)
			return null;
		
		System.out.println(value.toGMTString());
		System.out.println(value);
		
		Long time = value.getTime()/1000;
		return time.intValue();
	}


	@Override
	public void setFrom(Integer paramAsDate) {
		if(paramAsDate == null){
			fromDP.setValue(null);
			return;
		}
		Long time = new Long(paramAsDate*1000);
		fromDP.setValue(new Date(time));
	}

	@Override
	public void setTo(Integer paramAsDate) {
		if(paramAsDate == null){
			toDP.setValue(null);
			return;
		}
		Long time = new Long(paramAsDate*1000);
		toDP.setValue(new Date(time));		
	}


	@Override
	public HasClickHandlers getSearchButton() {
		return searchButton;
	}

	
	@Override
	public void setRegisteredAccountsRequested() {
		accountsRecived = false;
		BaseListBoxUtils.setItemsNAToLB(accountLB);
	} 
	@Override
	public void setRegisteredAccounts(ArrayList<ListBoxDTO> result) {
		BaseListBoxUtils.setItemsToLB(accountLB, result);
		accountsRecived = true;
		if(accountToBeSelected != null){
			setSelectedAccountId(accountToBeSelected);
		}
	}
	@Override
	public void setSelectedAccountId(Integer accountId) {
		if(accountsRecived){
			BaseListBoxUtils.setSelectedItemByValue(accountLB, ""+accountId);
		}else{
			accountToBeSelected = accountId;
		}
	}

}