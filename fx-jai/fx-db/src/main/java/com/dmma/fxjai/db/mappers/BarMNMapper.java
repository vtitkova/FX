/*package com.dmma.fxjai.db.mappers;

import java.util.ArrayList;
import java.util.List;

import com.dmma.fxjai.db.entities.BarMN;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.types.PeriodType;

public class BarMNMapper {

	public static BarDTO toDTO(BarMN source){
		if(source == null) return null;
		BarDTO target = new BarDTO();
		target.setId(       source.getId());
		target.setPeriod(   PeriodType.MN1.getId());
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
	
	public static ArrayList<BarDTO> toDTOs(List<BarMN> entities){
		if(entities == null) return null;
		ArrayList<BarDTO> retVal = new ArrayList<BarDTO>(entities.size());
		
		for(BarMN entity:entities){
			retVal.add(toDTO(entity));
		}
		return retVal;
	}
	
	public static BarMN mapToEntity(BarDTO source, BarMN target){
		if(target==null){
			target = new BarMN();
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
*/