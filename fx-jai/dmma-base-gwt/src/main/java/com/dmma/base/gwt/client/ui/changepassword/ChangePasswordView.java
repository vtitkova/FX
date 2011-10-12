package com.dmma.base.gwt.client.ui.changepassword;

import com.dmma.base.gwt.client.resources.i18n.BaseMessages;
import com.dmma.base.gwt.client.ui.dialog.AppDialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

public class ChangePasswordView extends Composite {
	interface MyUiBinder extends UiBinder<Widget, ChangePasswordView> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private ChangePasswordListener listener;

	@UiField
	protected PasswordTextBox currentPassword;
	@UiField
	protected PasswordTextBox newPassword;
	@UiField
	protected PasswordTextBox repPassword;
	@UiField
	protected Button updateButton;
	
	public ChangePasswordView() {
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}

	private void init() {
		updateButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				tryToChangePassword();
			}
		});
	}

	private void tryToChangePassword(){
		String current = currentPassword.getValue();
		if(current==null||current.length()==0){
			AppDialog.show(BaseMessages.MSG.currentPasswordIsEmptyError(), AppDialog.WARNING_MESSAGE);
			return;
		}
		String newPass = newPassword.getValue();
		String repPass = repPassword.getValue();
		if(newPass==null||newPass.length()==0||!newPass.equals(repPass)){
			
			AppDialog.show(BaseMessages.MSG.newPasswordIsNotEqualError(), AppDialog.WARNING_MESSAGE);
			return;
			//TODO add error style 
		}
		sendPasswordChangeRequest(current, newPass);
	}
	
	private void sendPasswordChangeRequest(String oldPassword, String newPassword){
		if( listener!=null )
			listener.changePassword(oldPassword, newPassword);
	}

	public void setPasswordChangeStatus(Boolean status) {
		if(status==null||!status){
			AppDialog.show(BaseMessages.MSG.passwordUpdateFailed(), AppDialog.ERROR_MESSAGE);
		}else{
			AppDialog.show(BaseMessages.MSG.passwordUpdateSwccessfully(), AppDialog.INFORMATION_MESSAGE);
			currentPassword.setValue("");
			newPassword.setValue("");
			repPassword.setValue("");
		}
	}

	public void setListener(ChangePasswordListener listener) {
		this.listener = listener;
	}
}
