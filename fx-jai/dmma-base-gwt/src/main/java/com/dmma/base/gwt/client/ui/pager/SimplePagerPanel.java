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
 * << 1	2 3 4 5 6 7 8 >> Items on screen |10v|
 * 
 **/
public class SimplePagerPanel extends BaseComposite implements IPagerPanel, ChangeHandler {
	int values[] = {10,15,20};
	private Integer page;
	private Long totalItems;
	private Integer itemsOnScreen;

	private FlexTable layout;
	private FlexTable pageTable;

	private Label left;
	private Label right;
	private ListBox itemsOnScreenLB;

	private PagerListener listener;
	
	public SimplePagerPanel() {
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
				if(listener!=null&& page<pageCount(totalItems, itemsOnScreen)){
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

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalItems() {
		return totalItems.intValue();
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public Integer getItemsOnScreen() {
		//Integer i = null;
		//try{
		//i = Integer.parseInt(itemsOnScreenLB.getText());
		//}catch (Exception e) {
		//	i = 10;
		//}
		Integer i = BaseListBoxUtils.getSelectedValueAsInteger(itemsOnScreenLB);
		itemsOnScreen = i;
		return itemsOnScreen;
	}

	public void setItemsOnScreen(Integer itemsOnScreen) {
		this.itemsOnScreen = itemsOnScreen;
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
			int pageCount = pageCount(totalItems, itemsOnScreen);
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
		}
	}


	static int pageCount(Long total, int onSc){
		Long l1 = total/(new Integer(onSc));
		Long l2 = total%(new Integer(onSc));
		if(l2>0) return l1.intValue()+1;   
		else return l1.intValue();
		//return total/onSc+(total%onSc>0?1:0);
	}

	
	@Override
	public void setIPagerListener(PagerListener listener) {
		this.listener = listener;
	}

	@Override
	public void onChange(ChangeEvent e) {
		if(listener==null) return;
		Integer i = BaseListBoxUtils.getSelectedValueAsInteger(itemsOnScreenLB);
		if(i != itemsOnScreen)
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
