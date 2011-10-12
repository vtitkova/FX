package com.dmma.fxjai.web.gwt.admin.client.mvp.barsvisual;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.dmma.base.gwt.client.utils.BaseListBoxUtils;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.filters.BarSearchFilter;
import com.dmma.fxjai.shared.shared.types.PeriodType;
import com.dmma.fxjai.shared.shared.types.SymbolType;
import com.dmma.fxjai.web.gwt.core.client.css.FxCssResource;
import com.dmma.fxjai.web.gwt.core.client.css.FxCssResource.FxCss;
import com.dmma.fxjai.web.gwt.core.client.i18n.FXMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class BarsVisualView extends Composite implements  BarsVisualDisplay{
	interface MyUiBinder extends UiBinder<Widget, BarsVisualView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	public static String VIEW_TITLE = FXMessages.MSG.menuBarsVisual();
	
	private boolean accountsRecived = false; 
	private Integer accountToBeSelected = null;
	
	private FxCss fxCss =  FxCssResource.RES.style();
	
	@UiField ListBox accountLB;
	@UiField ListBox symbolLB;
	@UiField ListBox periodLB;
	@UiField DateBox fromDP;
	@UiField DateBox toDP;
	@UiField Button searchButton;
	
	@UiField
	ScrollPanel scrollPanel ;
	
	private FlowPanel canvas;
		
	public BarsVisualView(){
		
		initWidget(uiBinder.createAndBindUi(this));
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
	
		canvas = new FlowPanel();
		canvas.setStyleName(fxCss.canvas());
		fxCss.ensureInjected();
		scrollPanel.setWidget(canvas);
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resizeMe();
			}
		});
		resizeMe();
		
	}
	private static final int SCROLLER_MIN_HEIGHT = 200;
	private int heightDecreaseValue = 90;
	
	private void resizeMe(){
		int height = Window.getClientHeight();
		height -= heightDecreaseValue;
		if(height < SCROLLER_MIN_HEIGHT)
			height = SCROLLER_MIN_HEIGHT;
		scrollPanel.setHeight(height + "px");
	}
	

	@Override
	public void setData(List<BarDTO> data) {
		canvas.clear();
		
		
		renderOneBar(0,data.get(0));
		
	}

	
	
	private void renderOneBar(int i, BarDTO barDTO) {
		FlowPanel barBody = new FlowPanel();
		barBody.setStyleName(fxCss.bar());
		barBody.addStyleName(fxCss.barDown());

		barBody.getElement().getStyle().setTop(10, Unit.PX);
		barBody.getElement().getStyle().setHeight(100, Unit.PX);
		canvas.add(barBody);
		
	}


	@Override
	public void setDataRequested() {
		canvas.clear();
		canvas.add(new Image(BaseImages.IMG.loadingSmall()));
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
		retVal.setAccountId(BaseListBoxUtils.getSelectedValueAsInteger(accountLB));
		retVal.setSymbol(SymbolType.findById(BaseListBoxUtils.getSelectedValueAsInteger(symbolLB)));
		retVal.setPeriod(PeriodType.findById(BaseListBoxUtils.getSelectedValueAsInteger(periodLB)));
		
		 
		retVal.setFrom(trimValue(fromDP.getValue()));
		retVal.setTo(trimValue(toDP.getValue()));
		return retVal;
	}

	
	
	private Integer trimValue(Date value) {
		if(value == null)
			return null;
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