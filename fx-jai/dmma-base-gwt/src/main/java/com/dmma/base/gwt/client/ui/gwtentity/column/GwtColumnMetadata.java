package com.dmma.base.gwt.client.ui.gwtentity.column;

import com.google.gwt.user.client.rpc.IsSerializable;

public class GwtColumnMetadata implements IsSerializable{
	public final  String  title;
	public int      width      = 100;
	public Class<?> clazz      = String.class;
	public boolean  filterable = false;
	public boolean  editable          = false;
	public boolean  editableInTable   = false;
	public boolean  required   = false;

	public GwtColumnMetadata(String  title) {
		this.title = title;
	}
	
	public GwtColumnMetadata(String  title, Class<?> clazz, int width) {
		this.title = title;		
		this.clazz = clazz;
		this.width =  width;
	}

}
