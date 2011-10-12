package com.dmma.base.app.mail.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.base.app.mail.callback.MailSendingCallback;
import com.dmma.base.app.mail.config.MailServerConfigurator;
import com.dmma.base.app.mail.entities.Mail;
import com.dmma.base.app.mail.runnable.SendMailRunnable;
import com.dmma.base.shared.shared.mail.types.MailSendingLogType;
import com.dmma.base.shared.shared.mail.types.MailSendingMethodType;
import com.dmma.base.shared.shared.mail.types.MailStatusType;

public class MailProcessor {
	private static final Logger log = LoggerFactory.getLogger(MailProcessor.class);
	private MailService mailService;
	private MailServerConfigurator mailServerConfigurator;
	private MyMailSendingCallback  mailSendingCallback;
	
	public MailProcessor() {
		mailSendingCallback = new MyMailSendingCallback();
	}
	
	
	public Boolean sendMail(Mail mail, MailSendingMethodType sendingType, MailSendingLogType logType) {
		if(mail.getMailFrom() == null || mail.getMailFrom().length() == 0)
			mail.setMailFrom(mailServerConfigurator.getHostUser());
		
		if(mailServerConfigurator.redirectAllTo() != null && mailServerConfigurator.redirectAllTo().length() != 0){
			String originalDestination = "<br>This mail was redirected, original email address was : "+ mail.getMailTo();
			mail.setMailTo(mailServerConfigurator.redirectAllTo());
			mail.setMessage(mail.getMessage() + originalDestination);
		}
		
		
		mail.setCreated(new Date());
		mail.setStatus(MailStatusType.isNew.getId());
			
		if(MailSendingMethodType.isDelayed.equals(sendingType) && MailSendingLogType.isNoLog.equals(logType)){
			sendingType = MailSendingMethodType.isStandart;
		}
		
		
		if(MailSendingMethodType.isStandart.equals(sendingType)){
			log.info("performing -standart- mail sending: log = "+logType);
			SendMailRunnable runable = null;
			if(MailSendingLogType.isLog.equals(logType)){
				mailService.saveOrUpdate(mail);
				runable = new SendMailRunnable(mail, mailServerConfigurator, mailSendingCallback);
			}else{
				runable = new SendMailRunnable(mail, mailServerConfigurator, null);
			}
			Thread t = new Thread(runable);
			t.start();
			return true;
		}
		if(MailSendingMethodType.isInstant.equals(sendingType)){
			log.info("performing -instant- mail sending: log = "+logType);
			SendMailRunnable runable = null;
			if(MailSendingLogType.isLog.equals(logType)){
				mailService.saveOrUpdate(mail);
				runable = new SendMailRunnable(mail, mailServerConfigurator, mailSendingCallback);
			}else{
				runable = new SendMailRunnable(mail, mailServerConfigurator, null);
			}
			//Thread t = new Thread(runable);
			//t.start();
			// we will do it synchronously
			runable.run();
			if(MailStatusType.isSent.getId().equals(mail.getStatus()))
				return true;
			else
				return false;
		}
		
		if(MailSendingMethodType.isDelayed.equals(sendingType)){
			log.info("performing -delayed- mail sending: log = "+logType);
			mailService.saveOrUpdate(mail);
			return true;
		}
		return null;
	}


	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}


	public void setMailServerConfigurator(MailServerConfigurator mailServerConfigurator) {
		this.mailServerConfigurator = mailServerConfigurator;
	}
	

	private class MyMailSendingCallback implements MailSendingCallback{
		@Override
		public void onMailSent(Mail mail, boolean isSuccess, String errorDescription) {
			mailService.saveOrUpdate(mail);
		}
	}
	
	
	
	
	
	
	
}
