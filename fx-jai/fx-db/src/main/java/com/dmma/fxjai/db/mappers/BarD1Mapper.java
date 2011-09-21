package com.dmma.fxjai.db.mappers;

import java.util.ArrayList;
import java.util.List;

import com.dmma.fxjai.db.entities.BarD1;
import com.dmma.fxjai.shared.dto.BarDTO;
import com.dmma.fxjai.shared.types.PeriodType;

public class BarD1Mapper {

	public static BarDTO toDTO(BarD1 source){
		if(source == null) return null;
		BarDTO target = new BarDTO();
		target.setId(       source.getId());
		target.setPeriod(   PeriodType.isD1.getId());
		target.setClientId( source.getClientId());
		target.setSymbolId( source.getSymbolId());
		target.setOpenDateTime(source.getOpenDateTime());
		target.setOpen(     source.getOpen());
		target.setHigh(     source.getHigh());
		target.setLow(      source.getLow());
		target.setClose(    source.getClose());
		target.setVolume(   source.getVolume());
		return target;
	}
	
	public static ArrayList<BarDTO> toDTOs(List<BarD1> entities){
		if(entities == null) return null;
		ArrayList<BarDTO> retVal = new ArrayList<BarDTO>(entities.size());
		
		for(BarD1 entity:entities){
			retVal.add(toDTO(entity));
		}
		return retVal;
	}
	
	public static BarD1 mapToEntity(BarDTO source, BarD1 target){
		if(target==null){
			target = new BarD1();
		}
		//target.setId(       source.getId());
		target.setClientId( source.getClientId());
		target.setSymbolId( source.getSymbolId());
		target.setOpenDateTime(source.getOpenDateTime());
		target.setOpen(     source.getOpen());
		target.setHigh(     source.getHigh());
		target.setLow(      source.getLow());
		target.setClose(    source.getClose());
		target.setVolume(   source.getVolume());
		return target;
	}
}
