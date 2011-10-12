package com.dmma.base.gwt.server.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.context.WebApplicationContext;

import com.dmma.base.app.mail.entities.Mail;
import com.dmma.base.app.mail.entities.MailTemplate;
import com.dmma.base.app.mail.services.MailProcessor;
import com.dmma.base.app.mail.services.MailService;
import com.dmma.base.app.mail.services.MailTemplateService;
import com.dmma.base.gwt.client.services.MailGWTService;
import com.dmma.base.gwt.server.mappers.MailMapper;
import com.dmma.base.gwt.server.mappers.MailTemplateMapper;
import com.dmma.base.gwt.server.services.base.BaseGWTServiceImpl;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.base.gwt.shared.dtos.PagedRequestDTO;
import com.dmma.base.gwt.shared.dtos.PagedResponseDTO;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.dmma.base.gwt.shared.errors.MethodPermissionGError;
import com.dmma.base.gwt.shared.errors.ObjectNotExistGError;
import com.dmma.base.gwt.shared.filters.MailSearchFilter;
import com.dmma.base.shared.shared.mail.types.MailSendingLogType;
import com.dmma.base.shared.shared.mail.types.MailSendingMethodType;

/**
 * Mail  
 * 
 * 	<!-- Base Servlets -->
    <servlet>
		<servlet-name>mailGWTServlet</servlet-name>
		<servlet-class>com.dmma.base.gwt.server.services.MailGWTServiceImpl</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>mailGWTServlet</servlet-name>
		<url-pattern>/Banker/MailGWTService</url-pattern>
	</servlet-mapping>
	....
 **/
public class MailGWTServiceImpl extends BaseGWTServiceImpl implements MailGWTService {
	private static final long serialVersionUID = 7516288737599084327L;

	private MailService mailService;
	private MailTemplateService mailTemplateService;
	private MailProcessor mailProcessor;
	
	@Override
	protected void initMe(WebApplicationContext context) {
		mailService         = context.getBean("mailService",         MailService.class);
		mailTemplateService = context.getBean("mailTemplateService", MailTemplateService.class);
		mailProcessor       = context.getBean("mailProcessor",       MailProcessor.class);
	}

	@Override
	public MailDTO findMailById(Integer id) throws ObjectNotExistGError, MethodPermissionGError {
		Mail entity = mailService.findById(id);
		if(entity==null) throw new ObjectNotExistGError();
		return MailMapper.toDTO(entity);
	}

	@Override
	public ArrayList<MailDTO> findAllMails() throws MethodPermissionGError {
		return MailMapper.toDTOs(mailService.findAll());
	}

	@Override
	public PagedResponseDTO<MailDTO> findAllMails(PagedRequestDTO<MailSearchFilter> request) throws MethodPermissionGError {
		Integer statusId       = request.getFilter().getStatusIdId();
		Integer mailTemplateId = request.getFilter().getMailTemplateId();
		Date dateFrom          = request.getFilter().getDateFrom();
		Date dateTo            = request.getFilter().getDateTo();
		
		Long total = mailService.countMails(statusId, mailTemplateId, dateFrom,dateTo);
		if(total == null || total < 1){
			return null;
		}
		
		int start = (request.getPage() -1 ) * request.getItemsOnPage();
		int maxResults =  request.getItemsOnPage();
		ArrayList<MailDTO> entities = MailMapper.toDTOs(mailService.findMails(start, maxResults, statusId, mailTemplateId, dateFrom, dateTo));
		
		PagedResponseDTO<MailDTO> retVal = new PagedResponseDTO<MailDTO>();
		retVal.setTotal(total);
		retVal.setPage(request.getPage());
		retVal.setItemsOnPage(request.getItemsOnPage());
		retVal.setEntities(entities);
		
		return retVal;
	}


	@Override
	public Integer saveOrUpdateMail(MailDTO entity)	throws MethodPermissionGError {
		Mail existingMail = null;
		if(entity.getId() != null)
			existingMail = mailService.findById(entity.getId());
		
		existingMail = MailMapper.mapToEntity(entity, existingMail);
		mailService.saveOrUpdate(existingMail);
		return existingMail.getId();
	}

	@Override
	public MailTemplateDTO findTemplateById(Integer id) throws ObjectNotExistGError, MethodPermissionGError {
		MailTemplate entity = mailTemplateService.findById(id);
		if(entity==null) throw new ObjectNotExistGError();
		return MailTemplateMapper.toDTO(entity);
	}

	@Override
	public MailTemplateDTO findTemplateByKey(String name) throws ObjectNotExistGError, MethodPermissionGError {
		MailTemplate entity = mailTemplateService.findByKey(name);
		if(entity==null) throw new ObjectNotExistGError();
		return MailTemplateMapper.toDTO(entity);
	}

	@Override
	public ArrayList<MailTemplateDTO> findAllTemplates() throws MethodPermissionGError {
		return MailTemplateMapper.toDTOs(mailTemplateService.findAll());
	}

	@Override
	public Integer saveOrUpdateTemplate(MailTemplateDTO entity)	throws MethodPermissionGError {
		MailTemplate existingTemplate = null;
		if(entity.getId() != null)
			existingTemplate = mailTemplateService.findById(entity.getId());
		
		existingTemplate = MailTemplateMapper.mapToEntity(entity, existingTemplate);
		mailTemplateService.saveOrUpdate(existingTemplate);
		return existingTemplate.getId();
	}

	@Override
	public Integer sendMail(MailDTO entity, MailSendingMethodType methodType, MailSendingLogType logType) throws MethodPermissionGError {
		Mail existingMail = null;
		if(entity.getId() != null)
			existingMail = mailService.findById(entity.getId());
		
		existingMail = MailMapper.mapToEntity(entity, existingMail);
		mailProcessor.sendMail(existingMail, methodType, logType);
		return existingMail.getId();
	}

	@Override
	public ArrayList<String> findAllTemplateNames()	throws MethodPermissionGError {
		return mailTemplateService.findAllKeys();
	}

	@Override
	public ArrayList<ListBoxDTO> findAllTemplateListBoxDTOs() throws MethodPermissionGError {
		ArrayList<MailTemplateDTO> found = findAllTemplates();
		if(found == null)
			return null;
		
		ArrayList<ListBoxDTO> retVal = new ArrayList<ListBoxDTO>(found.size());
		for(MailTemplateDTO template: found){
			ListBoxDTO dto = new ListBoxDTO(template.getId(), template.getTitle());
			retVal.add(dto);
		}
		return retVal;
	}

	
}