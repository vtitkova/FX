package com.dmma.base.gwt.client.resources.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface BaseMessages extends Constants{
	public static final BaseMessages  MSG = (BaseMessages)     GWT.create(BaseMessages.class);


	public String id();
	public String nr();
	public String from();
	public String to();
	public String created();
	public String sent();
	public String processed();

	// Buttons or action links
	public String ok();     
	public String yes();  
	public String no();
	public String add();
	public String edit();
	public String find(); 
	public String save();
	public String update();
	public String cancel();
	public String close();
	public String refresh();
	public String pickUp();
	public String createNew();	
	public String saveAndPickUp();
	public String pdf();
	public String today(); 
	public String thisMonth(); 
	public String lastMonth(); 

	// Usual ListBox values
	public String na();
	public String notFound();
	public String select();
	public String all();
	public String none();

	// Dialog headers
	public String warning();
	public String error();
	public String info();
	public String confirmation();

	// Application Errors or permission restrictions
	public String appPingerError();
	public String methodPermissionGError();
	public String objectPermissionGError();
	public String objectNotExistGError(); 
	public String applicationError();

	// ChangePasswordWidget
	public String changePasswordTile();
	public String currentPassword();
	public String newPassword();    
	public String repeatPasswors(); 
	public String currentPasswordIsEmptyError();
	public String newPasswordIsNotEqualError();
	public String passwordUpdateSwccessfully();
	public String passwordUpdateFailed();

	// Applicatiom messages
	public String requiredFieldsError(); 
	public String saveOperationFailed(); // operation failed  
	public String saveOperationSuccessed();
	public String requestingData();
	public String dataNotFound();
	public String loading();
	public String clientContext();

	// Mail framework
	public String mailMenuMails();
	public String mailMenuNewMail();
	public String mailMailForm();
	public String mailMenuMailTemplates();
	public String mailMenuNewMailTemplate();
	public String mailMailTemplateForm();
	
	public String mailTemplate();
	public String mailStatusIsNew();
	public String mailStatusIsSent();
	public String mailStatusIsFailed();
	public String mailStatus();
	public String mailSubject();
	public String mailText();
	public String mailTemplateName();
	public String mailTemplateTitle();
	public String mailTemplateHeader();
	public String mailTemplateBody();

}
