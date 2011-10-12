package com.dmma.base.gwt.client.ui.pager;

import com.dmma.base.gwt.client.ui.abstracts.BaseComposite;
import com.dmma.base.gwt.client.utils.BaseListBoxUtils;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;


/**
 * << .. 4 5 6 7 8 .. >> Items on screen |10v|
 * 
 **/
public class SimplePagerPanel2 extends BaseComposite implements IPagerPanel, ChangeHandler {
	int values[] = {10,15,20};
	int beforeAfter = 2;
	private Integer page;
	private Long totalItems;
	private Integer itemsOnScreen;

	private FlexTable layout;
	private FlexTable pageTable;

	private Label left;
	private Label right;
	private ListBox itemsOnScreenLB;

	private PagerListener listener;
	
	public SimplePagerPanel2() {
		super("PagerPanel");
		init();
	}

	private void init(){
		page = 0;
		totalItems = new Long(0);
		itemsOnScreen = 10;

		layout = new FlexTable();
		this.add(layout);

		left = new  Label("<<");
		left.addStyleName("actionWrap");
		layout.setWidget(0, 0,left);
		left.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if(listener!=null&& page>1){
					listener.onPageChanged(page-1);
				}
			}
		});
		
		pageTable = new FlexTable();
		layout.setWidget(0, 1,pageTable);


		right = new  Label(">>");
		right.addStyleName("actionWrap");
		layout.setWidget(0, 2,right);
		right.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if(listener!=null&& page< PagerUtils.pageCount(totalItems, itemsOnScreen)){
					listener.onPageChanged(page+1);
				}
			}
		});

		itemsOnScreenLB = new ListBox();
		for(int i = 0;i<values.length; i++){
			itemsOnScreenLB.addItem(""+values[i],""+values[i]);
		}
		itemsOnScreenLB.setWidth("45px");
		itemsOnScreenLB.addChangeHandler(this);

		layout.setHTML(0,3,"Items on screen");
		layout.setWidget(0, 4,itemsOnScreenLB);

	}

	public Integer getPage() {
		return page;
	}
	
	public Integer getTotalItems() {
		return totalItems.intValue();
	}

	
	public Integer getItemsOnScreen() {
		
		Integer i = BaseListBoxUtils.getSelectedValueAsInteger(itemsOnScreenLB);
		if(i<1){
			i = 10;
		}
		
		itemsOnScreen = i;
		return itemsOnScreen;
	}

	@Override
	public void setValue(Integer pg, Long total, Integer onScreen) {
		if(onScreen==null) onScreen = 10;
		itemsOnScreen = onScreen;

		if(total==null||total==0){
			page = 1;
			totalItems = new Long(0);
		}else{
			this.totalItems = total;
			if(pg==null||pg<1) pg = 1;
			page = pg;
		}	
		repaintMe();
	}

	public void repaintMe() {
		itemsOnScreenLB.setSelectedIndex(0);
		for(int i = 0;i<itemsOnScreenLB.getItemCount(); i++){
			if(itemsOnScreenLB.getValue(i).equals(""+itemsOnScreen)){
				itemsOnScreenLB.setSelectedIndex(i);
			}
		}
		
		pageTable.removeAllRows();
		if(totalItems==0){
			pageTable.setHTML(0, 0, "1");
		}else{
			int pageCount = PagerUtils.pageCount(totalItems, itemsOnScreen);
			if((beforeAfter*2+1) > pageCount ){
				for(int i =1; i<=pageCount; i++){
					if(i==page)
						pageTable.setHTML(0,i-1, "<b>"+i+"</b>");
					else{
						Label l = new Label(""+i);
						pageTable.setWidget(0,i-1, l);
						l.addStyleName("actionWrap");
						l.addClickHandler(new LabelClickHandler(i));
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
			if(i==pg)
				pageTable.setHTML(0,ti, "<b>"+i+"</b>");
			else{
				Label l = new Label(""+i);
				pageTable.setWidget(0,ti, l);
				l.addStyleName("actionWrap");
				l.addClickHandler(new LabelClickHandler(i));
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
	public void onChange(ChangeEvent e) {
		if(listener==null) return;
		
		Integer i = BaseListBoxUtils.getSelectedValueAsInteger(itemsOnScreenLB);
		if(i<1){
			i = 10;
		}
		if(i!=itemsOnScreen)
			listener.onItemsOnPageChanged(1, i);
	}

	private class LabelClickHandler implements ClickHandler{
		private Integer pageNR;
		public LabelClickHandler(Integer pageNR) {
			this.pageNR = pageNR;
		}
		@Override
		public void onClick(ClickEvent e) {
			if(listener!=null){
				listener.onPageChanged(pageNR);
			} 
		}
	}


}
