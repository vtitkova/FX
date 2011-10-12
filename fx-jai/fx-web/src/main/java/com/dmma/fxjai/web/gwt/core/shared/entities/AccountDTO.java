package com.dmma.fxjai.web.gwt.core.shared.entities;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AccountDTO implements IsSerializable{
	private Integer id;
	private String  account;
	private Integer accountType;
	private String  userName;
	private String  serverName;
	private Date    created;
	
	public AccountDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
}
