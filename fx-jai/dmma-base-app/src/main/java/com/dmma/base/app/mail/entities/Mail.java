package com.dmma.base.app.mail.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "mail")
public class Mail{

	@Id()
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	@Column private Integer status;
	@Column private String templateName;
	@Column private String mailFrom;
	@Column private String mailTo;
	@Column private String subject;
	@Column private String message;
	@Column private Date created;
	@Column private Date sent;
	

	public Mail() {
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
