package com.dmma.base.gwt.client.ui.gwtentity;

import com.google.gwt.user.client.rpc.IsSerializable;


public interface GwtEnumRendererInterface extends IsSerializable {
	public String lookUpById(Integer id);
}
