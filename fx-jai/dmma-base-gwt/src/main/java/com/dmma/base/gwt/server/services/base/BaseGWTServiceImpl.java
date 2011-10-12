package com.dmma.base.gwt.server.services.base;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public abstract class BaseGWTServiceImpl extends RemoteServiceServlet{
	protected static final Logger log = LoggerFactory.getLogger(BaseGWTServiceImpl.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		initMe(context);
	}
	
	protected abstract void initMe(WebApplicationContext context);
	// TODO give access to CLIENT CONTEXT
	
	
}
