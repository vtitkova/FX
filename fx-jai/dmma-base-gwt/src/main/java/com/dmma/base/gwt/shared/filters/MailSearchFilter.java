package com.dmma.base.gwt.shared.filters;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MailSearchFilter implements IsSerializable{
	private Integer statusId;
	private Integer mailTemplateId;
	private Date    dateFrom;
	private Date    dateTo;
	
	public MailSearchFilter() {
	}

	public Integer getStatusIdId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		if(statusId == null || statusId < 0)
			this.statusId = null;
		else
			this.statusId = statusId;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Integer getMailTemplateId() {
		return mailTemplateId;
	}

	public void setMailTemplateId(Integer mailTemplateId) {
		if(mailTemplateId == null || mailTemplateId < 0)
			this.mailTemplateId = null;
		else
			this.mailTemplateId = mailTemplateId;
	}
}
