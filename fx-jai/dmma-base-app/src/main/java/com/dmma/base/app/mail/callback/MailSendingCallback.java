package com.dmma.base.app.mail.callback;

import com.dmma.base.app.mail.entities.Mail;

public interface MailSendingCallback {
	public void onMailSent(Mail mail, boolean isSuccess, String errorDescription);
}
