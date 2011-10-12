package com.dmma.base.gwt.client.mail.mailTemplates;

import java.util.ArrayList;

import com.dmma.base.gwt.client.mail.mailtemplateform.MailTemplatePresenter;
import com.dmma.base.gwt.client.mail.meta.MailTemplateCM;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.dmma.base.gwt.client.ui.etf.EtfTable;
import com.dmma.base.gwt.client.ui.etf.EtfTableModel;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MailTemplatesView extends Composite implements  MailTemplatesDisplay{
	interface MyUiBinder extends UiBinder<Widget, MailTemplatesView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	public static String VIEW_TITLE = BaseMessages.MSG.mailMenuMailTemplates();

	@UiField
	Button refreshButton;
	
	@UiField(provided = true)
	EtfTable<MailTemplateDTO, MailTemplateCM> mailTemplatesTable;
	MailTemplateCM META;
	EtfTableModel<MailTemplateDTO, MailTemplateCM> tableModel;
	
	
	
	public MailTemplatesView(){
		META = new MailTemplateCM();
		tableModel = new EtfTableModel<MailTemplateDTO, MailTemplateCM>(META);
		mailTemplatesTable = new EtfTable<MailTemplateDTO, MailTemplateCM>(tableModel, false, true, MailTemplatePresenter.PRESENTER_ID);
		mailTemplatesTable.setEditImageResource(BaseImages.IMG.editSmall());
		mailTemplatesTable.setHeightDecreaseValue(108);
		
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setData(ArrayList<MailTemplateDTO> data) {
		tableModel.setObjects(data);
	}

	@Override
	public void setDataRequested() {
		tableModel.setRequesting();
	}

	@Override
	public String getCaption() {
		return VIEW_TITLE;
	}

	@Override
	public HasClickHandlers getRefreshButton() {
		return refreshButton;
	}

}