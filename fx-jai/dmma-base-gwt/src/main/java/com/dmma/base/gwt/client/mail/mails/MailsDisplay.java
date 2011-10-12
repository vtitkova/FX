package com.dmma.base.gwt.client.mail.mails;

import java.util.ArrayList;
import java.util.Date;

import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.client.ui.pager.PagerListener;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.base.gwt.shared.dtos.PagedResponseDTO;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.gwt.shared.filters.MailSearchFilter;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface MailsDisplay extends IPresenterDisplay {
	public void setAvailableTemplates(ArrayList<ListBoxDTO> result);

	public void setData(PagedResponseDTO<MailDTO> data);
	public void setDataRequested();
	
	public HasClickHandlers getSearchButton();
	
	public void setSelectedStatusId(Integer statusId);
	public void setSelectedTemplateId(Integer templateId);
	public void setDateFrom(Date paramAsDate);
	public void setDateTo(Date paramAsDate);

	public MailSearchFilter getMailSearchFilter();

	public void setAvailableTemplatesRequested();
	
	public void setPagerListener(PagerListener pagerListener);
	
}
