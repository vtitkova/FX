package com.dmma.base.gwt.client.ui.pager;

public interface PagerListener {
	public void onItemsOnPageChanged(Integer newPageValue, Integer newItemsOnPageValue);
	public void onPageChanged(         Integer newValue);
}
