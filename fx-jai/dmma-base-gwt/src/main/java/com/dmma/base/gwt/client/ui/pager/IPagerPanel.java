package com.dmma.base.gwt.client.ui.pager;

import com.google.gwt.user.client.ui.Widget;

public interface IPagerPanel{
	
	public Integer  getPage();
	public Integer  getTotalItems();
	public Integer  getItemsOnScreen();
	public void setValue(Integer page, Long totalItems, Integer itemsOnScreen);
	
	public Widget asWidget();
	
	public void setIPagerListener(PagerListener listener);
	public void setVisible(boolean b);
	
}
