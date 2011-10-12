package com.dmma.base.gwt.client.ui.pager;

public class PagerUtils {

	static int pageCount(Long totalItems, int itemsOnScreen){
		Long l1 = totalItems / itemsOnScreen;
		Long l2 = totalItems % (itemsOnScreen);
		if(l2>0) 
			return l1.intValue()+1;   
		else 
			return l1.intValue();
	}
	
}
