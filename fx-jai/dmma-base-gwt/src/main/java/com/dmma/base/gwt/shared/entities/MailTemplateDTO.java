package com.dmma.base.gwt.shared.entities;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MailTemplateDTO implements IsSerializable{
	private Integer id;
	private String  name;
	private String  title;
	private String  header;
	private String  body;

	public MailTemplateDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
