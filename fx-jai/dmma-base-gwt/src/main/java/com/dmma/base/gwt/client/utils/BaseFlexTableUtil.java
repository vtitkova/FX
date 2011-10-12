package com.dmma.base.gwt.client.utils;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;


public class BaseFlexTableUtil {

	public static void addDataNotFound(FlexTable targetTable) {
		int row = 	targetTable.getRowCount();
		int cc = maxCellCount(targetTable);
		targetTable.setHTML(row, 0, BaseMessages.MSG.dataNotFound());
		if(cc>0)
			targetTable.getFlexCellFormatter().setColSpan(row, 0, cc);
	}

	public static Integer maxCellCount(FlexTable targetTable){
		if(targetTable==null) return null;
		Integer retVal = 0;
		int row = 	targetTable.getRowCount();
		for(int i = 0; i<row; i++){
			if(targetTable.getCellCount(i)>retVal)
				retVal = targetTable.getCellCount(i);
		}
		return retVal;
	} 

	public static void addRequestingData(FlexTable targetTable) {
		int row = 	targetTable.getRowCount();
		int cc = maxCellCount(targetTable);
		targetTable.setHTML(row, 0, BaseMessages.MSG.requestingData());
		if(cc>0)
			targetTable.getFlexCellFormatter().setColSpan(row, 0, cc);
	}
	
	
	public static void addAnimatedRequestingData(FlexTable targetTable) {
		int row = 	targetTable.getRowCount();
		int cc = maxCellCount(targetTable);
		targetTable.setWidget(row, 0, new Image(BaseImages.IMG.loading()));
		if(cc>0)
			targetTable.getFlexCellFormatter().setColSpan(row, 0, cc);
		targetTable.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	public static void addAnimatedRequestingDataSmall(FlexTable targetTable) {
		int row = 	targetTable.getRowCount();
		int cc = maxCellCount(targetTable);
		targetTable.setWidget(row, 0, new Image(BaseImages.IMG.loadingSmall()));
		if(cc>0)
			targetTable.getFlexCellFormatter().setColSpan(row, 0, cc);
		targetTable.getFlexCellFormatter().setHorizontalAlignment(row, 0, HasHorizontalAlignment.ALIGN_CENTER);
	}

	
}
