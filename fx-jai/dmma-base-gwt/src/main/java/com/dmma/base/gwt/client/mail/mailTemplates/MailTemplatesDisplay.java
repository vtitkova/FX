package com.dmma.base.gwt.client.mail.mailTemplates;

import java.util.ArrayList;

import com.dmma.base.gwt.client.presenter.IPresenterDisplay;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface MailTemplatesDisplay extends IPresenterDisplay {
	public void setData(ArrayList<MailTemplateDTO> data);
	public void setDataRequested();
	public HasClickHandlers getRefreshButton();
}
