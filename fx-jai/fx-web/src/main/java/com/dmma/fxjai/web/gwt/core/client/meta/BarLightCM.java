package com.dmma.fxjai.web.gwt.core.client.meta;


import com.dmma.base.gwt.client.ui.gwtentity.column.GwtColumnMetadata;
import com.dmma.base.gwt.client.ui.gwtentity.column.IGwtEntityCM;
import com.dmma.fxjai.shared.shared.dto.BarDTO;

public class BarLightCM implements IGwtEntityCM<BarDTO>{
	private final GwtColumnMetadata openDateTimeColumn;
	private final GwtColumnMetadata openColumn;
	private final GwtColumnMetadata highColumn;
	private final GwtColumnMetadata lowColumn;
	private final GwtColumnMetadata closeColumn;
	private final GwtColumnMetadata volumeColumn;
		
	
	
	public BarLightCM() {
		openDateTimeColumn = new GwtColumnMetadata("TIME",  Integer.class, 100);
		openColumn         = new GwtColumnMetadata("OPEN",  Double.class,  100);
		highColumn         = new GwtColumnMetadata("HIGH",  String.class,  100);
		lowColumn          = new GwtColumnMetadata("LOW",   String.class,  100);
		closeColumn        = new GwtColumnMetadata("CLOSE", String.class,  100);
		volumeColumn       = new GwtColumnMetadata("VOL.",  String.class,  -1);
 	}
	
	@Override
	public GwtColumnMetadata[] getColumnMeta(){
		return new GwtColumnMetadata[]{ openDateTimeColumn, openColumn, highColumn,
		  lowColumn, closeColumn, volumeColumn};
		
	}

	@Override
	public Object getValueAt(BarDTO entity, int index) {
		switch(index){
		case 0: return entity.getOpenDateTime();
		case 1: return entity.getOpen();
		case 2: return entity.getHigh();
		case 3: return entity.getLow();
		case 4: return entity.getClose();
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
