package com.dmma.base.gwt.shared.dtos;

import com.google.gwt.user.client.rpc.IsSerializable;


public class ListBoxDTO implements IsSerializable{
	private Integer id;
	private String  name;
	
	public ListBoxDTO() {
	}
	
	public ListBoxDTO(Integer id, String  name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
