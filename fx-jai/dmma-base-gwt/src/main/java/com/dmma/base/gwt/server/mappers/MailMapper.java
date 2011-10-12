package com.dmma.base.gwt.server.mappers;

import java.util.ArrayList;
import java.util.List;

import com.dmma.base.app.mail.entities.Mail;
import com.dmma.base.gwt.shared.entities.MailDTO;

public class MailMapper {

	public static MailDTO toDTO(Mail source){
		if(source == null) return null;
		MailDTO target = new MailDTO();
		target.setId(          source.getId());
		target.setStatus(      source.getStatus());
		target.setTemplateName(source.getTemplateName());
		target.setMailFrom(    source.getMailFrom());
		target.setMailTo(      source.getMailTo());
		target.setSubject(     source.getSubject());
		target.setMessage(     source.getMessage());
		target.setCreated(     source.getCreated());
		target.setSent(        source.getSent());
		return target;
	}

	public static ArrayList<MailDTO> toDTOs(List<Mail> entities){
		if(entities == null) return null;
		ArrayList<MailDTO> retVal = new ArrayList<MailDTO>(entities.size());
		for(Mail entity:entities){
			retVal.add(toDTO(entity));
		}
		return retVal;
	}

	public static Mail mapToEntity(MailDTO source, Mail target){
		if(target==null){
			target = new Mail();
		}
		//target.setId(        source.getId());
		target.setStatus(      source.getStatus());
		target.setTemplateName(source.getTemplateName());
		target.setMailFrom(    source.getMailFrom());
		target.setMailTo(      source.getMailTo());
		target.setSubject(     source.getSubject());
		target.setMessage(     source.getMessage());
		target.setCreated(     source.getCreated());
		target.setSent(        source.getSent());
		return target;
	}
}
