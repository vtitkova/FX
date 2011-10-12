package com.dmma.base.gwt.server.mappers;

import java.util.ArrayList;
import java.util.List;

import com.dmma.base.app.mail.entities.MailTemplate;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;

public class MailTemplateMapper {

	public static MailTemplateDTO toDTO(MailTemplate source){
		if(source == null) return null;
		MailTemplateDTO target = new MailTemplateDTO();
		target.setId(    source.getId());
		target.setName(   source.getName());
		target.setTitle( source.getTitle());
		target.setHeader(source.getHeader());
		target.setBody(  source.getBody());
		return target;
	}
	
	public static ArrayList<MailTemplateDTO> toDTOs(List<MailTemplate> entities){
		if(entities == null) return null;
		ArrayList<MailTemplateDTO> retVal = new ArrayList<MailTemplateDTO>(entities.size());
		
		for(MailTemplate entity:entities){
			retVal.add(toDTO(entity));
		}
		return retVal;
	}
	
	public static MailTemplate mapToEntity(MailTemplateDTO source, MailTemplate target){
		if(target==null){
			target = new MailTemplate();
		}
		target.setId(    source.getId());
		target.setTitle( source.getTitle());
		target.setName(source.getName());
		target.setHeader(source.getHeader());
		target.setBody(  source.getBody());
		return target;
	}
}
