package com.dmma.base.gwt.client.mail.mailform;

import java.util.List;

import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface MailFormDisplay extends IPresenterDisplay{
	public void setAvailableTemplates(List<MailTemplateDTO> availableTemplates);
	public void setData(MailDTO data);
	public void setDataRequested();
	public HasClickHandlers getSendButton();
	
	public MailDTO getData();
	public boolean getMakeLog();
	
	public void setSaveRequested();

	
}
