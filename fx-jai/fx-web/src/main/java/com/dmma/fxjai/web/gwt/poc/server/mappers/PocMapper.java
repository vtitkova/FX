package com.dmma.fxjai.web.gwt.poc.server.mappers;

import java.util.ArrayList;
import java.util.List;

import com.dmma.fxjai.core.entities.Poc;
import com.dmma.fxjai.web.gwt.poc.shared.entities.PocDTO;

public class PocMapper {

	public static PocDTO toDTO(Poc source){
		if(source == null) return null;
		PocDTO target = new PocDTO();
		target.setId(     source.getId());
		target.setText(   source.getText());
		target.setCreated(source.getCreated());
		return target;
	}
	
	public static ArrayList<PocDTO> toDTOs(List<Poc> entities){
		if(entities == null) return null;
		ArrayList<PocDTO> retVal = new ArrayList<PocDTO>(entities.size());
		
		for(Poc entity:entities){
			retVal.add(toDTO(entity));
		}
		return retVal;
	}
	
	public static Poc mapToEntity(PocDTO source, Poc target){
		if(target==null){
			target = new Poc();
		}
		//target.setId(         source.getId());
		target.setText( source.getText());
		target.setCreated( source.getCreated());
		return target;
	}
}
