package com.dmma.base.gwt.client.ui.gwtentity;

import com.google.gwt.user.client.rpc.IsSerializable;

@Deprecated
public interface GwtEntityInterface extends IsSerializable {
	public Integer  getId();
	public Object   getValueAt(int index);
	public void setValueAt(int index, Object value);
	public String toString();
	
}
