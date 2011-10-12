package com.dmma.base.gwt.client.mail.mailform;

import java.util.List;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.dialog.AppDialog;
import com.dmma.base.gwt.client.ui.rte.RichTextEditor;
import com.dmma.base.gwt.client.utils.BaseListBoxUtils;
import com.dmma.base.gwt.shared.entities.MailDTO;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MailFormView extends Composite implements  MailFormDisplay{
	interface MyUiBinder extends UiBinder<Widget, MailFormView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	public static String VIEW_TITLE = BaseMessages.MSG.mailMailForm();

	private List<MailTemplateDTO> availableTemplates;
	public MailDTO data;

	@UiField ListBox template;
	@UiField TextBox mailTo;
	@UiField TextBox subject;
	@UiField RichTextEditor bodyRT;
	@UiField CheckBox makeLog;

	@UiField Button sendButton;


	public MailFormView(){
		initWidget(uiBinder.createAndBindUi(this));
		template.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String selected = BaseListBoxUtils.getSelectedValueAsString(template);
				if(availableTemplates != null && !BaseListBoxUtils.SELECT_IND.equals(selected)){
					for(MailTemplateDTO mTemplate: availableTemplates){
						if(mTemplate.getName().equals(selected)){
							subject.setText(mTemplate.getHeader());
							bodyRT.setHTML(mTemplate.getBody());
							break;
						}
					}
				}else{
					subject.setText("");
					bodyRT.setHTML("");
				}
			}
		});
	}

	
	

	@Override
	public String getCaption() {
		return VIEW_TITLE;
	}


	@Override
	public void setAvailableTemplates(List<MailTemplateDTO> availableTemplates) {
		this.availableTemplates = availableTemplates;
		template.clear();
		template.addItem(BaseMessages.MSG.none(), BaseListBoxUtils.SELECT_IND);
		if(this.availableTemplates != null){
			for(MailTemplateDTO mTemplate: this.availableTemplates){
				template.addItem(mTemplate.getTitle(), mTemplate.getName());
			}
		}
	}


	@Override
	public void setData(MailDTO data) {
		this.data = data;
		subject.setText(data.getSubject());
		bodyRT.setHTML(data.getMessage());
		mailTo.setText(data.getMailTo());
		BaseListBoxUtils.setSelectedItemByValue(template, data.getTemplateName());
	}


	@Override
	public void setDataRequested() {
		// TODO Auto-generated method stub

	}


	@Override
	public HasClickHandlers getSendButton() {
		return sendButton;
	}


	@Override
	public MailDTO getData() {
		
		if(subject.getText().isEmpty() || subject.getText().isEmpty() || subject.getText().isEmpty()){
			AppDialog.show(BaseMessages.MSG.requiredFieldsError(), AppDialog.ERROR_MESSAGE);
			return null;
		}
		if(data == null)
			data = new MailDTO();
		data.setMessage(bodyRT.getHTML());
		data.setMailTo( mailTo.getText());
		data.setSubject(subject.getText());
		
		String templateName = BaseListBoxUtils.getSelectedValueAsString(template);
		if(templateName.equals("-1")){
			templateName = null;
		}
		data.setTemplateName(templateName);
		return data;
	}


	@Override
	public void setSaveRequested() {
		// TODO Auto-generated method stub

	}




	@Override
	public boolean getMakeLog() {
		return makeLog.getValue();
	}




}