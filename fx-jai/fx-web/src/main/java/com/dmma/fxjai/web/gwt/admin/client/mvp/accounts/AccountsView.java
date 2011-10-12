package com.dmma.fxjai.web.gwt.admin.client.mvp.accounts;

import java.util.ArrayList;

import com.dmma.base.gwt.client.mail.mailtemplateform.MailTemplatePresenter;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.dmma.base.gwt.client.ui.etf.EtfTable;
import com.dmma.base.gwt.client.ui.etf.EtfTableModel;
import com.dmma.fxjai.web.gwt.core.client.meta.AccountCM;
import com.dmma.fxjai.web.gwt.core.shared.entities.AccountDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AccountsView extends Composite implements  AccountsDisplay{
	interface MyUiBinder extends UiBinder<Widget, AccountsView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	public static String VIEW_TITLE = BaseMessages.MSG.mailMenuMailTemplates();

	@UiField
	Button refreshButton;
	
	@UiField(provided = true)
	EtfTable<AccountDTO, AccountCM> mailTemplatesTable;
	AccountCM META;
	EtfTableModel<AccountDTO, AccountCM> tableModel;
	
	
	
	public AccountsView(){
		META = new AccountCM();
		tableModel = new EtfTableModel<AccountDTO, AccountCM>(META);
		mailTemplatesTable = new EtfTable<AccountDTO, AccountCM>(tableModel, false, true, MailTemplatePresenter.PRESENTER_ID);
		mailTemplatesTable.setEditImageResource(BaseImages.IMG.editSmall());
		mailTemplatesTable.setHeightDecreaseValue(108);
		
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setData(ArrayList<AccountDTO> data) {
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