package com.dmma.base.gwt.client.mail.mailtemplateform;

import java.util.ArrayList;
import java.util.HashMap;

import com.dmma.base.gwt.client.mail.MailTemplateSuggestion;
import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.rte.RichTextEditor;
import com.dmma.base.gwt.client.utils.BaseListBoxUtils;
import com.dmma.base.gwt.shared.entities.MailTemplateDTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MailTemplateView extends Composite implements  MailTemplateDisplay, ClickHandler{
	interface MyUiBinder extends UiBinder<Widget, MailTemplateView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	public static String VIEW_TITLE = BaseMessages.MSG.mailMailTemplateForm();
	
	private final HashMap<String, MailTemplateSuggestion> mailTemplateSuggestions;
	private ArrayList<String> registeredTemplateKeys;
	
	public MailTemplateDTO data;

	@UiField
	ListBox nameLB;
	
	@UiField
	Button nextButton;
	
	@UiField
	HTML templateKey;
	
	@UiField
	HTML templateTitle;
	
	@UiField
	HTML availableTags;
	
	@UiField
	TextBox headerTB;
	
	@UiField
	RichTextEditor bodyRT;
	
	@UiField
	Button saveButton;
	
	
	@UiField
	HTMLPanel suggestionsPanelContainer;
	@UiField
	HTMLPanel mailTemplatePanelContainer;
	@UiField
	HTMLPanel loadingPanelContainer;
	
	public MailTemplateView(HashMap<String, MailTemplateSuggestion> mailTemplateSuggestions){
		this.mailTemplateSuggestions = mailTemplateSuggestions;
		initWidget(uiBinder.createAndBindUi(this));
		
		nameLB.addItem(BaseMessages.MSG.select(), "select");
		
		mailTemplatePanelContainer.setVisible(false);
		suggestionsPanelContainer.setVisible(false);
		loadingPanelContainer.setVisible(false);
		nextButton.addClickHandler(this);
	}

	
	@Override
	public void setData(MailTemplateDTO data) {
		this.data = data;
		loadingPanelContainer.setVisible(false);
		
		if(data != null){
			suggestionsPanelContainer.setVisible(false);
			mailTemplatePanelContainer.setVisible(true);
			StringBuilder sb = new StringBuilder();
			MailTemplateSuggestion mailTemplateSuggestion = mailTemplateSuggestions.get(data.getName());
			if(mailTemplateSuggestion != null && mailTemplateSuggestion.getTags() != null){
				for(int i = 0; i < mailTemplateSuggestion.getTags().length; i++ ){
					sb.append("$").append(mailTemplateSuggestion.getTags()[i]).append("$");
					if(i < mailTemplateSuggestion.getTags().length - 1)
						sb.append(", ");
				}
			}
			this.availableTags.setText(sb.toString());
			templateKey.setText(data.getName());
			templateTitle.setText(data.getTitle());
			headerTB.setValue(data.getHeader());
			bodyRT.setHTML(data.getBody());
		}else{
			mailTemplatePanelContainer.setVisible(false);
			suggestionsPanelContainer.setVisible(true);
			nameLB.clear();
			
			for(MailTemplateSuggestion mts: mailTemplateSuggestions.values()){
				if(registeredTemplateKeys == null || registeredTemplateKeys.isEmpty() || isNotRegistered(mts,registeredTemplateKeys)){
					nameLB.addItem(mts.getTitle(), mts.getName());
				}
			}
			
			if(nameLB.getItemCount() > 0 ){
				nameLB.insertItem(BaseMessages.MSG.select(), BaseListBoxUtils.SELECT_IND+"",  0);
				nameLB.setSelectedIndex(0);
			}else{
				
				
			}
			
			// 1 . nado vijasnitj, kakie mailTemplateSuggestions
			
		}
		
	}


	private static boolean isNotRegistered(MailTemplateSuggestion mts,	ArrayList<String> registeredNmaes) {
		for(String key: registeredNmaes){
			if(key.equals(mts.getName()))
				return false;
		}
		return true;
	}


	@Override
	public void setDataRequested() {
		loadingPanelContainer.setVisible(true);
		mailTemplatePanelContainer.setVisible(false);
		suggestionsPanelContainer.setVisible(false);
	}


	@Override
	public MailTemplateDTO getData() {
		if(data == null) return null;
		
		data.setHeader(headerTB.getValue());
		data.setBody(bodyRT.getHTML());
		return data;
	}


	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public void setSaveRequested() {
		saveButton.setEnabled(false);
	}

	@Override
	public void setSaved(boolean result) {
		saveButton.setEnabled(true);
	}


	@Override
	public String getCaption(){
		return VIEW_TITLE;
	}

	@Override
	public void setRegisteredTemplateKeys(ArrayList<String> registeredTemplateKeys) {
		this.registeredTemplateKeys = registeredTemplateKeys;
	}


	@Override
	public void onClick(ClickEvent event) {
		if(nextButton.equals(event.getSource())){
			String key = BaseListBoxUtils.getSelectedValueAsString(nameLB);
			MailTemplateSuggestion mailTemplateSuggestion = mailTemplateSuggestions.get(key);
			if(mailTemplateSuggestion != null){
				MailTemplateDTO template = new MailTemplateDTO();
				template.setName(mailTemplateSuggestion.getName());
				template.setTitle(mailTemplateSuggestion.getTitle());
				setData(template);
			}
		}
	}

}