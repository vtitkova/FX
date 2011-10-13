package com.dmma.fxjai.web.gwt.core.client.meta;


import java.util.Date;

import com.dmma.base.gwt.client.ui.gwtentity.column.GwtColumnMetadata;
import com.dmma.base.gwt.client.ui.gwtentity.column.IGwtEntityCM;
import com.dmma.base.gwt.client.utils.BaseFormats;
import com.dmma.fxjai.shared.shared.dto.BarDTO;

public class BarLightCM implements IGwtEntityCM<BarDTO>{
	private final GwtColumnMetadata openDateTimeColumn;
	private final GwtColumnMetadata openColumn;
	private final GwtColumnMetadata highColumn;
	private final GwtColumnMetadata lowColumn;
	private final GwtColumnMetadata closeColumn;
	private final GwtColumnMetadata volumeColumn;
		
	
	
	public BarLightCM() {
		openDateTimeColumn = new GwtColumnMetadata("TIME",  String.class, 110);
		openColumn         = new GwtColumnMetadata("OPEN",  String.class, 100);
		highColumn         = new GwtColumnMetadata("HIGH",  String.class, 100);
		lowColumn          = new GwtColumnMetadata("LOW",   String.class, 100);
		closeColumn        = new GwtColumnMetadata("CLOSE", String.class, 100);
		volumeColumn       = new GwtColumnMetadata("VOL.",  String.class, -1);
 	}
	
	@Override
	public GwtColumnMetadata[] getColumnMeta(){
		return new GwtColumnMetadata[]{ openDateTimeColumn, openColumn, highColumn,
		  lowColumn, closeColumn, volumeColumn};
		
	}

	@Override
	public Object getValueAt(BarDTO entity, int index) {
		switch(index){
		case 0: 
			Date d = new Date(entity.getOpenDateTime()*1000l);
			return BaseFormats.getFormattedDateTimeGMT(d);
			//return entity.getOpenDateTime();
		case 1: return BaseFormats.getFormattedLongCurency(entity.getOpen());
		case 2: return BaseFormats.getFormattedLongCurency(entity.getHigh());
		case 3: return BaseFormats.getFormattedLongCurency(entity.getLow());
		case 4: return BaseFormats.getFormattedLongCurency(entity.getClose());
		case 5: return entity.getVolume();
		}
		return null;
	}

	@Override
	public Object getId(BarDTO entity) {
		return entity.getOpenDateTime();
	}

	@Override
	public void setValueAt(BarDTO entity, int index, Object value) {
		// TODO Auto-generated method stub
	}
}
