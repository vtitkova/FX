package com.dmma.fxjai.core.configuration;

import org.constretto.annotation.Configuration;
import org.constretto.annotation.Configure;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;

import com.dmma.fxjai.db.entities.Account;
import com.dmma.fxjai.db.entities.BarD1;
import com.dmma.fxjai.db.entities.BarMN;
import com.dmma.fxjai.db.entities.BarW1;
import com.dmma.fxjai.db.entities.Poc;


public class ApplicationConfiguration extends AnnotationConfiguration{
	private static final long serialVersionUID = -3085599947632203102L;

	public ApplicationConfiguration() {
		AppProperties.constrettoConfiguration.on(this);
		configureEntities();
	}
	
	@Configure
	public void constrettoConfiguration(
			@Configuration(expression = "hibernate.connection.driver_class") String connectionDriverClass,
			@Configuration(expression = "hibernate.connection.url")          String connectionUrl,
			@Configuration(expression = "hibernate.connection.username")     String connectionUsername,
			@Configuration(expression = "hibernate.connection.password")     String connectionPassword,
			@Configuration(expression = "hibernate.dialect")                 String dialect,
			@Configuration(expression = "hibernate.hbm2ddl.auto")            String hbm2ddlAuto,
			@Configuration(expression = "hibernate.show_sql")                String showSql,
			@Configuration(expression = "hibernate.format_sql")              String formatSql,
			@Configuration(expression = "connection.provider_class")         String connectionProviderClass,
			@Configuration(expression = "connection.pool_size")              String connectionPoolSize,
			
			@Configuration(expression = "hibernate.c3p0.acquire_increment") String acquireIncrement,
			@Configuration(expression = "hibernate.c3p0.idle_test_period") String idleTestPeriod,
			@Configuration(expression = "hibernate.c3p0.max_size") String max_size,
			@Configuration(expression = "hibernate.c3p0.max_statements") String max_statements,
			@Configuration(expression = "hibernate.c3p0.min_size") String min_size,
			@Configuration(expression = "hibernate.c3p0.timeout") String timeout
			){
		
		// Set database connection
		this.setProperty(Environment.DRIVER,       connectionDriverClass);
		this.setProperty(Environment.URL,          connectionUrl);
		this.setProperty(Environment.USER,         connectionUsername);
		this.setProperty(Environment.PASS,         connectionPassword);
		this.setProperty(Environment.DIALECT,      dialect);
		// Set query logging
		this.setProperty(Environment.SHOW_SQL,     showSql);
		this.setProperty(Environment.FORMAT_SQL,   formatSql);
		// Set database creation mode 
		this.setProperty(Environment.HBM2DDL_AUTO, hbm2ddlAuto);
		
		// Setup c3p0 connection pooling
		this.setProperty(Environment.CONNECTION_PROVIDER,     connectionProviderClass);
		this.setProperty(Environment.POOL_SIZE,               connectionPoolSize);
        
		this.setProperty(Environment.C3P0_ACQUIRE_INCREMENT,   acquireIncrement);
        this.setProperty(Environment.C3P0_IDLE_TEST_PERIOD,    idleTestPeriod);
        this.setProperty(Environment.C3P0_MAX_SIZE,            max_size);
        this.setProperty(Environment.C3P0_MAX_STATEMENTS,      max_statements);
        this.setProperty(Environment.C3P0_MIN_SIZE,            min_size);
        this.setProperty(Environment.C3P0_TIMEOUT,             timeout);
	}
	
	private void configureEntities(){
		this.addAnnotatedClass(Poc.class);
		this.addAnnotatedClass(Account.class);
		this.addAnnotatedClass(BarD1.class);
		this.addAnnotatedClass(BarW1.class);
		this.addAnnotatedClass(BarMN.class);
		/*this.addAnnotatedClass(User.class);
		this.addAnnotatedClass(UserRole.class);
		this.addAnnotatedClass(BrokerOffice.class);
		this.addAnnotatedClass(Broker.class);
		this.addAnnotatedClass(BankOffice.class);
		this.addAnnotatedClass(Banker.class);
		this.addAnnotatedClass(Estate.class);
		this.addAnnotatedClass(EstateViewing.class);
		this.addAnnotatedClass(Client.class);
		this.addAnnotatedClass(Tip.class);
		this.addAnnotatedClass(HaveToSell.class);
		this.addAnnotatedClass(ClientVisit.class);
		this.addAnnotatedClass(MailTemplate.class);
		this.addAnnotatedClass(Mail.class);*/
	}
}
