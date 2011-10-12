package com.dmma.base.gwt.client.mail.mailtemplateform;

import java.util.ArrayList;

import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface MailTemplateDisplay extends IPresenterDisplay{
	public void setRegisteredTemplateKeys(ArrayList<String> registeredTemplateKeys);
	public void setData(MailTemplateDTO data);
	public void setDataRequested();
	public HasClickHandlers getSaveButton();
	public MailTemplateDTO getData();
	public void setSaveRequested();
	public void setSaved(boolean result);

	/*public HasChangeHandlers getNamesListBox();
	public String getSelectedName();*/
}
