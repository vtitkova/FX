package com.dmma.base.gwt.client.utils;

import java.util.ArrayList;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.gwtentity.GwtEntityInterface;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.google.gwt.user.client.ui.ListBox;

public class BaseListBoxUtils {
	public static final String SELECT_IND = "-1";
	public static final String NA_IND     = "-2";
	public static final String NOT_FOUND  = "-3";
	



	/**
	 * return -1 if null 
	 * else return integer value of selected item
	 *   <-1, -select->
	 *   <123, AAA>
	 *   <143, BBB>
	 *   ....
	 *    * */
	public static Integer getSelectedValueAsInteger(ListBox box){
		if(box==null||box.getSelectedIndex()<0) return -1;
		String val = box.getValue(box.getSelectedIndex());
		if(val==null||val.length()==0) return -1;
		try{
			Integer retVal = Integer.parseInt(val);
			return retVal;
		}catch (Exception e) {
			System.err.println("Could not parse value from ListBox to Integer");
			return -1;
		}
	}

	/**
	 * return -1 if null 
	 * else return integer value of selected item
	 *   <"-1", -select->
	 *   <"123", AAA>
	 *   <"143", BBB>
	 *   ....
	 *    * */
	public static String getSelectedValueAsString(ListBox box){
		String val = box==null?null:box.getValue(box.getSelectedIndex());
		return val;
	}

	public static String getSelectedItemAsString(ListBox box){
		String val = box==null?null:box.getItemText(box.getSelectedIndex());
		return val;
	}

	
	public static void setSelectedItemByValue(ListBox box, String value){
		if(box==null || box.getItemCount() == 0) return;
		
		for(int i = 0; i<box.getItemCount(); i++){
			if(box.getValue(i).equals(value)){
				box.setSelectedIndex(i);
				return;
			}
		}
		box.setSelectedIndex(0);
	}
	public static void setSelectedItemByValue(ListBox box, Integer value){
		if(value ==null) return;
		setSelectedItemByValue(box, value.toString());
	}
	
	public static void setSelectedItemByText(ListBox box, String value){
		if(box==null || box.getItemCount() == 0) return;
		
		for(int i = 0; i<box.getItemCount(); i++){
			if(box.getItemText(i).equals(value)){
				box.setSelectedIndex(i);
				return;
			}
		}
		box.setSelectedIndex(0);
	}
	
	
	
	// work with list boxes
	public static void setItemsToLB(ListBox listBox, ArrayList<ListBoxDTO> data) {
		listBox.clear();
		if(data!=null&&!data.isEmpty()){
			listBox.addItem(BaseMessages.MSG.select(), SELECT_IND);
			addItemsToLB(listBox,data);
			listBox.setEnabled(true);
		}else{
			listBox.addItem(BaseMessages.MSG.notFound(), NOT_FOUND);
			listBox.setEnabled(false);
		}
	}
	
	// work with list boxes
	
	/**
	 *	Set only id into ListBox
	 *	Examlpe: first we receive  
	 * */
	public static void setOnlyOneIdToLB(ListBox listBox, GwtEntityInterface dto) {
		listBox.clear();
		if(dto!=null){
			listBox.addItem(""+dto.getId(), ""+dto.getId());
			listBox.setEnabled(false);
		}else{
			listBox.addItem(BaseMessages.MSG.notFound(), NOT_FOUND);
			listBox.setEnabled(false);
		}
	}
	
	public static void setItemsToLBKeepId(ListBox listBox, ArrayList<ListBoxDTO> data) {
		String value = getSelectedValueAsString(listBox);
		setItemsToLB(listBox, data);
		setSelectedItemByValue(listBox, value);
	}
	
	
	public static void setItemsNAToLB(ListBox listBox) {
		listBox.clear();
		listBox.addItem(BaseMessages.MSG.na(), NA_IND);
		listBox.setEnabled(false);
	}
	
	public static void addItemsToLB(ListBox listBox, ArrayList<ListBoxDTO> data) {
		if(listBox==null||data==null||data.isEmpty()) return;
		for(ListBoxDTO dto:data){
			listBox.addItem(dto.getName(), ""+dto.getId());
		}
	}

	
	
	
}


