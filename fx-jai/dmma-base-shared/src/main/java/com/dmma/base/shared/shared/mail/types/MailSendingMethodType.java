package com.dmma.base.shared.shared.mail.types;



public enum MailSendingMethodType {
	isStandart, // Make entry in the DB if needed and try to send in separated thread
	isInstant,  // Make entry in the DB if needed and try to send synchronously (now).  
	isDelayed;  // Make entry in the DB. Sending will be processed in separated task according schedule
}
