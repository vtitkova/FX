package com.dmma.fxjai.core.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.constretto.annotation.Configuration;
import org.constretto.annotation.Configure;

public class ApplicationDataSource extends BasicDataSource{
	
	public ApplicationDataSource() {
		AppProperties.constrettoConfiguration.on(this);
	}
	
	@Configure
	public void constrettoConfiguration(@Configuration(expression = "hibernate.connection.driver_class") String connectionDriverClass,
			@Configuration(expression = "hibernate.connection.url")          String connectionUrl,
			@Configuration(expression = "hibernate.connection.username")     String connectionUsername,
			@Configuration(expression = "hibernate.connection.password")     String connectionPassword){
		this.username = connectionUsername;
		this.password = connectionPassword;
		this.url             = connectionUrl;
		this.driverClassName = connectionDriverClass;
		
	}
}
