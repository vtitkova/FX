package com.dmma.fxjai.web.gwt.core.server.services;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dmma.fxjai.db.services.BarDBService;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.filters.BarSearchFilter;
import com.dmma.fxjai.web.gwt.core.client.services.BarGWTService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
public class BarGWTServiceImpl extends RemoteServiceServlet implements BarGWTService {
	private static final long serialVersionUID = -8849195396928815111L;
	private BarDBService barDBService;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		barDBService    = context.getBean("barDBService",   BarDBService.class);
	}
	
	@Override
	public List<BarDTO> find(BarSearchFilter filter) {
		return barDBService.findByFilter(filter);
	}

}
