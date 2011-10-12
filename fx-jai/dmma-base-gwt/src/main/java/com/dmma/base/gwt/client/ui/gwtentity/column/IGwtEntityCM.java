package com.dmma.base.gwt.client.ui.gwtentity.column;

import com.google.gwt.user.client.rpc.IsSerializable;


public interface IGwtEntityCM<T extends IsSerializable> {
	public GwtColumnMetadata[] getColumnMeta();
	public Object getId(T entity);
	public Object getValueAt(T entity, int index);
	public void setValueAt(T entity, int index, Object value);
	
}
