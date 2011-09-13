package com.dmma.fxjai.connector.errors;

public class ConnectionError extends Throwable{
	private static final long serialVersionUID = 838828279336634227L;
	private String login; 
	private String reason; 
	
	public ConnectionError(String login, String reason) {
		this.login = login;
		this.reason = reason;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
	
}
