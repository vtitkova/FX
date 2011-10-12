package com.dmma.base.gwt.client.services;


import java.util.ArrayList;

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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("MailGWTService")
public interface MailGWTService extends RemoteService {
	public MailDTO             findMailById(Integer id)                throws ObjectNotExistGError, MethodPermissionGError;
	public ArrayList<MailDTO>  findAllMails()                          throws MethodPermissionGError;
	public PagedResponseDTO<MailDTO>  findAllMails(PagedRequestDTO<MailSearchFilter> request) throws MethodPermissionGError;
	public Integer             saveOrUpdateMail(MailDTO entity)        throws MethodPermissionGError;
	public Integer             sendMail(MailDTO entity, MailSendingMethodType mailSendingMethodType, MailSendingLogType mailSendingLogType) throws MethodPermissionGError;
	
	
	public MailTemplateDTO findTemplateById(Integer id)         throws ObjectNotExistGError, MethodPermissionGError;
	public MailTemplateDTO findTemplateByKey(String name)       throws ObjectNotExistGError, MethodPermissionGError;
	public ArrayList<MailTemplateDTO> findAllTemplates()           throws MethodPermissionGError;
	public ArrayList<String>          findAllTemplateNames()       throws MethodPermissionGError;
	public ArrayList<ListBoxDTO>      findAllTemplateListBoxDTOs() throws MethodPermissionGError;
	public Integer saveOrUpdateTemplate(MailTemplateDTO entity)    throws MethodPermissionGError;
	
}
