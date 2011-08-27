package com.dmma.fxjai.web.gwt.poc.shared.entities;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PocDTO implements IsSerializable{
	private Integer id;
	private String  text;
	private Date    created;
	
	public PocDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
