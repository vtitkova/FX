package com.dmma.base.gwt.client.ui.etf;


import java.util.ArrayList;
import java.util.List;

import com.dmma.base.gwt.client.ui.gwtentity.column.IGwtEntityCM;
import com.google.gwt.user.client.rpc.IsSerializable;


public class EtfTableModel<T extends IsSerializable, CMT extends IGwtEntityCM<T>> implements EtfTableModelInterface{
	private EtfTableModelListener listener;
	private CMT META;
	private List<T> entities;


	public EtfTableModel(CMT META) {
		this.META = META;
	}

	
	public void setObjects(List<T> objects) {
		this.entities = objects;
		if(listener != null){
			if(objects == null || objects.isEmpty()){
				listener.onModelChanged(new EtfTableModelEvent(EtfTableModelEvent.DATA_NOTFOUND));
			}else{
				listener.onModelChanged(new EtfTableModelEvent(EtfTableModelEvent.DATA_RECEIVED));
			}
		}
	}


	public List<T> getObjects() {
		return entities;
	}
		

	@Override
	public void setTableModelListener(EtfTableModelListener listenerr) {
		this.listener = listenerr;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return META.getColumnMeta()[columnIndex].clazz;
	}

	@Override
	public int getColumnCount() {
		return META.getColumnMeta().length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return META.getColumnMeta()[columnIndex].title;
	}

	@Override
	public int getColumnWidth(int columnIndex) {
		return META.getColumnMeta()[columnIndex].width;
	}

	//Rewrite this method if you need to have filter support!
	@Override
	public int[] getIndexesByFilter(ArrayList<String> filterStrings) {
		return null;
	}

	@Override
	public int getRowCount() {
		if(entities != null)
			return entities.size();
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(entities == null || entities.isEmpty() || entities.size() <= rowIndex){
			return null;
		}else{
			return META.getValueAt(entities.get(rowIndex), columnIndex);
		}
	}
	
	public T getEntity(int rowIndex) {
		if(entities == null || entities.isEmpty() || entities.size() <= rowIndex){
			return null;
		}else{
			return entities.get(rowIndex);
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return META.getColumnMeta()[columnIndex].editableInTable;
	}

	@Override
	public boolean isColumnFilterable(int columnIndex) {
		return META.getColumnMeta()[columnIndex].filterable;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(entities==null||entities.size()==0||entities.size()<=rowIndex)
			return;
		META.setValueAt(entities.get(rowIndex), columnIndex, aValue);
	}


	@Override
	public Object getObjectId(int rowIndex) {
		if(entities == null || entities.size() == 0 || entities.size() <= rowIndex){
			return null;
		}else{
			return META.getId(entities.get(rowIndex));
		}
	}
	
	public void setRequesting() {
		entities = null;
		if(listener != null){
			listener.onModelChanged(new EtfTableModelEvent(EtfTableModelEvent.DATA_REQUESTED));
		}
	}

}
