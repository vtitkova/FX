package com.dmma.base.gwt.client.mail.mails;

import java.util.ArrayList;
import java.util.Date;

import com.dmma.base.gwt.client.mail.mailform.MailFormPresenter;
import com.dmma.base.gwt.client.mail.meta.MailCM;
import com.dmma.base.gwt.client.mail.meta.MailStatusCellRenderer;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.resources.img.BaseImages;
import com.dmma.base.gwt.client.ui.etf.EtfTable;
import com.dmma.base.gwt.client.ui.etf.EtfTableModel;
import com.dmma.base.gwt.client.ui.pager.IPagerPanel;
import com.dmma.base.gwt.client.ui.pager.PagerListener;
import com.dmma.base.gwt.client.utils.BaseListBoxUtils;
import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.base.gwt.shared.dtos.PagedResponseDTO;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.gwt.shared.filters.MailSearchFilter;
import com.dmma.base.shared.shared.mail.types.MailStatusType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class MailsView extends Composite implements  MailsDisplay{
	interface MyUiBinder extends UiBinder<Widget, MailsView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	public static String VIEW_TITLE = BaseMessages.MSG.mailMenuMails();
	
	private boolean templateRecived = false; 
	private Integer templateToBeSelected = null;
	
	
	@UiField ListBox statusLB;
	@UiField ListBox templateLB;
	@UiField DateBox fromDP;
	@UiField DateBox toDP;
	@UiField Button searchButton;
	@UiField IPagerPanel pager;
	
	@UiField(provided = true)
	EtfTable<MailDTO, MailCM> contentTable;
	MailCM META;
	EtfTableModel<MailDTO, MailCM> tableModel;
	
	MailStatusCellRenderer mailStatusCellRenderer; 
	
	public MailsView(){
		META = new MailCM();
		tableModel = new EtfTableModel<MailDTO, MailCM>(META);
		contentTable = new EtfTable<MailDTO, MailCM>(tableModel, false, true, MailFormPresenter.PRESENTER_ID);
		contentTable.setEditImageResource(BaseImages.IMG.editSmall());
		
		mailStatusCellRenderer = new MailStatusCellRenderer();
		contentTable.addRenderer(1, mailStatusCellRenderer);
		
		initWidget(uiBinder.createAndBindUi(this));
		
		statusLB.addItem(BaseMessages.MSG.all(), "-1");
		
		statusLB.addItem(BaseMessages.MSG.mailStatusIsNew(),    MailStatusType.isNew.getId().toString());
		statusLB.addItem(BaseMessages.MSG.mailStatusIsSent(),   MailStatusType.isSent.getId().toString());
		statusLB.addItem(BaseMessages.MSG.mailStatusIsFailed(), MailStatusType.isFailed.getId().toString());
		
		templateLB.addItem(BaseMessages.MSG.all(), "-1");
		
	}
	

	@Override
	public void setData(PagedResponseDTO<MailDTO> data) {
		if(data == null){
			tableModel.setObjects(null);
		}else{
			tableModel.setObjects(data.getEntities());
			pager.setValue(data.getPage(), Long.valueOf(data.getTotal()), data.getItemsOnPage());
			pager.setVisible(true);
		}
	}

	@Override
	public void setDataRequested() {
		pager.setVisible(false);
		tableModel.setRequesting();
	}

	@Override
	public String getCaption() {
		return VIEW_TITLE;
	}

	
	@Override
	public void setSelectedStatusId(Integer statusId) {
		BaseListBoxUtils.setSelectedItemByValue(statusLB, ""+statusId);
	}

	/*@Override
	public void addAvailableTemplateNames(String templateTitle,	String templateName) {
		templateLB.addItem(templateTitle, templateName);
	}*/

	@Override
	public MailSearchFilter getMailSearchFilter() {
		MailSearchFilter retVal = new MailSearchFilter();
		retVal.setStatusId(BaseListBoxUtils.getSelectedValueAsInteger(statusLB));
		retVal.setMailTemplateId(BaseListBoxUtils.getSelectedValueAsInteger(templateLB));
		retVal.setDateFrom(fromDP.getValue());
		retVal.setDateTo(toDP.getValue());
		return retVal;
	}

	@Override
	public void setDateFrom(Date paramAsDate) {
		fromDP.setValue(paramAsDate);
	}

	@Override
	public void setDateTo(Date paramAsDate) {
		toDP.setValue(paramAsDate);		
	}


	@Override
	public HasClickHandlers getSearchButton() {
		return searchButton;
	}

	@Override
	public void setAvailableTemplatesRequested() {
		templateRecived = false;
		BaseListBoxUtils.setItemsNAToLB(templateLB);
	} 
	@Override
	public void setAvailableTemplates(ArrayList<ListBoxDTO> result) {
		BaseListBoxUtils.setItemsToLB(templateLB, result);// TODO Auto-generated method stub
		templateRecived = true;
		if(templateToBeSelected != null){
			setSelectedTemplateId(templateToBeSelected);
		}
	}
	@Override
	public void setSelectedTemplateId(Integer templateId) {
		if(templateRecived){
			BaseListBoxUtils.setSelectedItemByValue(templateLB, ""+templateId);
		}else{
			templateToBeSelected = templateId;
		}
	}
	
	@Override
	public void setPagerListener(PagerListener pagerListener) {
		pager.setIPagerListener(pagerListener);
	}

}