package com.dmma.base.gwt.client.ui.gwtentity;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;


public interface GwtEntityListInterface<T extends IsSerializable>{
	public String getValue(ArrayList<T> entities);
}
