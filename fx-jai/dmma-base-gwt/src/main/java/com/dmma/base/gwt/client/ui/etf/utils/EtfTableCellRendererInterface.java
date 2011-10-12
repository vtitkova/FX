package com.dmma.base.gwt.client.ui.etf.utils;

import com.google.gwt.user.client.rpc.IsSerializable;


public interface EtfTableCellRendererInterface<T extends IsSerializable>{
	public String getHTML(T entity);
}
