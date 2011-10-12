package com.dmma.base.gwt.client.ui.pager;

import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.dmma.base.gwt.client.utils.BaseListBoxUtils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;





public class SimplePagerPanel3 extends Composite implements IPagerPanel, ChangeHandler, ClickHandler{
	interface MyUiBinder extends UiBinder<Widget, SimplePagerPanel3> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	private static final Integer defItemsOnScreen = 20;
	private static final int values[] = {10,15,20};
	private static final int beforeAfter = 2;

	
	
	private Integer itemsOnScreen;
	private Integer page;
	private Long    totalItems;
	
	private PageClickHandler pageClickHandler;
	private PagerListener listener;
	
	@UiField
	SimplePagerPanel3Css style;
	
	@UiField
	BaseImages img;
	
	
	@UiField
	ListBox itemsOnScreenLB;
	@UiField
	FlexTable pageTable ;
	
	@UiField
	Image leftFast;
	
	@UiField
	Image left;
	
	@UiField
	Image right;
	
	@UiField
	Image rightFast;
	
	
	
	
	public SimplePagerPanel3() {
		initWidget(uiBinder.createAndBindUi(this));
		
		leftFast.addClickHandler(this);
		left.addClickHandler(this);
		right.addClickHandler(this);
		rightFast.addClickHandler(this);
		
		for(int i = 0;i<values.length; i++){
			itemsOnScreenLB.addItem(""+values[i],""+values[i]);
		}
		itemsOnScreenLB.setWidth("45px");
		itemsOnScreenLB.addChangeHandler(this);
		
		pageClickHandler = new PageClickHandler();
		
//		cssRes.style().ensureInjected();
		
		
	}


	@Override
	public void onChange(ChangeEvent event) {
		if(listener==null) return;
		Integer i = BaseListBoxUtils.getSelectedValueAsInteger(itemsOnScreenLB);
		if(i != itemsOnScreen)
			listener.onItemsOnPageChanged(1, i);
	}


	@Override
	public Integer getPage() {
		return page;
	}


	@Override
	public Integer getTotalItems() {
		return totalItems.intValue();
	}


	@Override
	public Integer getItemsOnScreen() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setValue(Integer page, Long totalItems, Integer itemsOnScreen) {
		if(itemsOnScreen == null || itemsOnScreen<1)
			itemsOnScreen = defItemsOnScreen;
		this.itemsOnScreen = itemsOnScreen;
		
		if(totalItems == null || totalItems <1){
			this.page = 1;
			this.totalItems = 0L;
		}else{
			this.totalItems = totalItems;
			if(page == null || page < 1) 
				page = 1;
			this.page = page;
		}	
		repaintMe();
		
	}

	public void repaintMe() {
		
		// check IOS
		int selectedIOS =  BaseListBoxUtils.getSelectedValueAsInteger(itemsOnScreenLB);
		if( selectedIOS != itemsOnScreen){
			BaseListBoxUtils.setSelectedItemByValue(itemsOnScreenLB, itemsOnScreen+"");
			// default list is 10, 15, 20 , but user manually changed url to see 5 items :) 
			selectedIOS =  BaseListBoxUtils.getSelectedValueAsInteger(itemsOnScreenLB);
			if( selectedIOS != itemsOnScreen){
				itemsOnScreenLB.addItem("" + itemsOnScreen,"" + itemsOnScreen);
				BaseListBoxUtils.setSelectedItemByValue(itemsOnScreenLB, itemsOnScreen+"");
			}
		}
		
		// Enable / Disable buttons
		if(page > 1){
			left.setResource(img.left());
			left.setStyleName(style.pointer());
			leftFast.setResource(img.leftFast());
			leftFast.setStyleName(style.pointer());
		}else{
			left.setResource(img.leftDisabled());
			left.removeStyleName(style.pointer());
			leftFast.setResource(img.leftFastDisabled());
			leftFast.removeStyleName(style.pointer());
		}
		
		if(page < PagerUtils.pageCount(totalItems, itemsOnScreen)){
			right.setResource(img.right());
			right.setStyleName(style.pointer());
			rightFast.setResource(img.rightFast());
			rightFast.setStyleName(style.pointer());
		}else{
			right.setResource(img.rightDisabled());
			right.removeStyleName(style.pointer());
			rightFast.setResource(img.rightFastDisabled());
			rightFast.removeStyleName(style.pointer());
		}
			
	
		pageTable.removeAllRows();
		if(totalItems==0){
			pageTable.setHTML(0, 0, "1");
		}else{
			int pageCount = PagerUtils.pageCount(totalItems, itemsOnScreen);
			if((beforeAfter*2+1) > pageCount ){
				for(int i =1; i<=pageCount; i++){
					if(i==page){
						HTML h = new HTML("<b>"+i+"</b>");
						h.setStyleName(style.controlsText());
						pageTable.setWidget(0,i-1, h);
					
					}else{
						Label l = new Label(""+i);
						pageTable.setWidget(0,i-1, l);
						l.addClickHandler(pageClickHandler);
						l.setStyleName(style.controlsText());
						l.addStyleName(style.pointer());
					}
				}
			}else{
				paintComplexPages(pageCount, page);
			}
			
		}
		
	
	}
	
	private void paintComplexPages(int pageCount, Integer pg) {
		int start  = pg-beforeAfter;
		int finish = pg+beforeAfter;
		if(start<1) start = 1;
		if(start>pageCount-beforeAfter-beforeAfter) start = pageCount-beforeAfter-beforeAfter;
		if(finish>pageCount) finish = pageCount;
		if(finish<beforeAfter*2+1) finish = beforeAfter*2+1; 
		
		int ti = 0;
		if(start>1){
			pageTable.setHTML(0,ti, "..");
			ti++;
		}
		
		for(int i =start ; i<=finish; i++){
			if(i==pg){
				HTML h = new HTML("<b>"+i+"</b>");
				h.setStyleName(style.controlsText());
				pageTable.setWidget(0,ti, h);
			
			}else{
				Label l = new Label(""+i);
				pageTable.setWidget(0,ti, l);
				l.addClickHandler(pageClickHandler);
				l.setStyleName(style.controlsText());
				l.addStyleName(style.pointer());
			}
			ti++;
		}
		if(finish<pageCount){
			pageTable.setHTML(0,ti, "..");
			ti++;
		}
		
	}

	
	
	
	
	
	

	@Override
	public void setIPagerListener(PagerListener listener) {
		this.listener = listener;
	}


	@Override
	public void onClick(ClickEvent event) {
		if(listener == null) return ;
		
		if(leftFast.equals(event.getSource())){
			if( page > 1 ){
				listener.onPageChanged(1);
			}
		}else if(left.equals(event.getSource())){
			if( page > 1 ){
				listener.onPageChanged(page-1);
			}
		}else if(rightFast.equals(event.getSource())){
			int  pageCount = PagerUtils.pageCount(totalItems, itemsOnScreen);
			if(page < pageCount){
				listener.onPageChanged(pageCount);
			}
		}else if(right.equals(event.getSource())){
			if(page < PagerUtils.pageCount(totalItems, itemsOnScreen)){
				listener.onPageChanged(page+1);
			}
		}
	}

	
	
	private class PageClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent e) {
			if(listener != null){
				Label clickedLabel = (Label) e.getSource();
				String pageStr = clickedLabel.getText();
				listener.onPageChanged(Integer.valueOf(pageStr));
			} 
		}
	}

	
	
	
	
	
	
}
