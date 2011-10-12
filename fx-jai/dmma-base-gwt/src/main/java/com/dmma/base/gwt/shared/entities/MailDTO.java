package com.dmma.base.gwt.shared.entities;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;


public class MailDTO implements IsSerializable{
	private Integer id;
	private Integer status;
	private String templateName;
	private String mailFrom;
	private String mailTo;
	private String subject;
	private String message;
	private Date created;
	private Date sent;
	

	public MailDTO() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getSent() {
		return sent;
	}


	public void setSent(Date sent) {
		this.sent = sent;
	}


	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	public String getMailFrom() {
		return mailFrom;
	}


	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}


	public String getMailTo() {
		return mailTo;
	}


	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
}
