package com.dmma.base.gwt.client.services;

import java.util.ArrayList;

import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.base.gwt.shared.dtos.PagedRequestDTO;
import com.dmma.base.gwt.shared.dtos.PagedResponseDTO;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.dmma.base.gwt.shared.filters.MailSearchFilter;
import com.dmma.base.shared.shared.mail.types.MailSendingLogType;
import com.dmma.base.shared.shared.mail.types.MailSendingMethodType;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>MailTemplateGWTService</code>.
 */
public interface MailGWTServiceAsync {
	public void findMailById(Integer id, AsyncCallback<MailDTO> callback);
	public void findAllMails(AsyncCallback<ArrayList<MailDTO>> callback);
	public void findAllMails(PagedRequestDTO<MailSearchFilter> request,	AsyncCallback<PagedResponseDTO<MailDTO>> callback);
	public void saveOrUpdateMail(MailDTO entity, AsyncCallback<Integer> callback);
	public void sendMail(MailDTO entity, MailSendingMethodType mailSendingMethodType, MailSendingLogType mailSendingLogType, AsyncCallback<Integer> callback);
	
	public void findTemplateById(Integer id, AsyncCallback<MailTemplateDTO> callback);
	public void findTemplateByKey(String name, AsyncCallback<MailTemplateDTO> callback);
	public void findAllTemplates(AsyncCallback<ArrayList<MailTemplateDTO>> callback);
	public void findAllTemplateNames(AsyncCallback<ArrayList<String>> callback);
	public void findAllTemplateListBoxDTOs(AsyncCallback<ArrayList<ListBoxDTO>> callback);
	public void saveOrUpdateTemplate(MailTemplateDTO entity, AsyncCallback<Integer> callback);
}
