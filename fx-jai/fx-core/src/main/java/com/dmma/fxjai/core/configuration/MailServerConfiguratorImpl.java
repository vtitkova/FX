package com.dmma.fxjai.core.configuration;

import org.constretto.annotation.Configuration;
import org.constretto.annotation.Configure;

import com.dmma.base.app.mail.config.MailServerConfigurator;

public class MailServerConfiguratorImpl implements MailServerConfigurator {
	private String hostName; 
	private String hostPort; 
	private String hostUser;
	private String hostPwd; 
	private Boolean sendMails; 
	private String redirectAllTo; 
	
	public MailServerConfiguratorImpl() {
		AppProperties.constrettoConfiguration.on(this);
	}
	
	@Configure
	public void constrettoConfiguration(
			@Configuration(expression = "mail.server.host")     String hostName,
			@Configuration(expression = "mail.server.port")     String hostPort,
			@Configuration(expression = "mail.server.user")     String hostUser,
			@Configuration(expression = "mail.server.password") String hostPwd,
			@Configuration(expression = "mail.send.emails")     Boolean sendMails,
			@Configuration(expression = "mail.redirect.all.to") String redirectAllTo){
		this.hostName = hostName;
		this.hostPort = hostPort;
		this.hostUser = hostUser;
		this.hostPwd  = hostPwd;
		this.sendMails = sendMails;
		this.redirectAllTo  = redirectAllTo;
		
	}
	

	@Override
	public String getHostName() {
		return hostName;
	}

	@Override
	public String getHostUser() {
		return hostUser;
	}

	@Override
	public String getHostPwd() {
		return hostPwd;
	}

	@Override
	public String getHostPort() {
		return hostPort;
	}

	@Override
	public Boolean sendMails() {
		return sendMails;
	}

	@Override
	public String redirectAllTo() {
		return redirectAllTo;
	}

	@Override
	public boolean isValid(){
		if(hostName == null || hostName.isEmpty()) return false;
		if(hostUser == null || hostUser.isEmpty()) return false;
		if(hostPwd  == null || hostPwd.isEmpty()) return false;
		if(hostPort == null || hostPort.isEmpty()) return false;
		return true;
	}
}

