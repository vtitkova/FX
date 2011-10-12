package com.dmma.base.gwt.client.ui.etf.utils;

import java.util.Date;

import com.dmma.base.gwt.client.ui.etf.EtfTableModel;
import com.dmma.base.gwt.client.utils.BaseFormats;
import com.dmma.base.shared.shared.clazz.DateTime;
import com.dmma.base.shared.shared.clazz.Time;



public class EtfTableUtils {
	
	public static boolean modelHasFilterabeColumns(EtfTableModel<?,?> model){
		if(model == null) 
			return false;
		int col = model.getColumnCount();
		for(int i = 0; i<col;i++){
			if(model.isColumnFilterable(i))
				return true;
		}
		return false;
	}

	public static int[] getFilterableColumnIndexes(EtfTableModel<?,?> model) {
		if(model == null) 
			return null;
		
		int colCnt = model.getColumnCount();
		int filtColCnt = 0;
		
		for(int i = 0; i<colCnt;i++){
			if(model.isColumnFilterable(i))
				filtColCnt++;
		}
		
		int[] indexes = new int[filtColCnt];
		
		filtColCnt = 0;
		for(int i = 0; i<colCnt;i++){
			if(model.isColumnFilterable(i)){
				indexes[filtColCnt] = i;
				filtColCnt++;
			}
		}
		
		return indexes;
	}
	
	public static String getHTML(Object val, Class<?> clazz) {
		if(Date.class.equals(clazz))
			return BaseFormats.getFormattedDate((Date) val); 
		else if(DateTime.class.equals(clazz))
			return BaseFormats.getFormattedDateTime((Date) val); 
		else if(Time.class.equals(clazz))
			return BaseFormats.getFormattedTime((Date) val); 
		
		
		else if(Double.class.equals(clazz))
			return BaseFormats.getFormattedCurency((Double) val); 
		else 
			return val.toString();

	}

}
