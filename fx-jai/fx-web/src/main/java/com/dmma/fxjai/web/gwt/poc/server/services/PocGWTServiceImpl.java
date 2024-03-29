package com.dmma.fxjai.web.gwt.poc.server.services;

import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dmma.fxjai.db.daos.PocDao;
import com.dmma.fxjai.web.gwt.poc.client.services.PocGWTService;
import com.dmma.fxjai.web.gwt.poc.server.mappers.PocMapper;
import com.dmma.fxjai.web.gwt.poc.shared.entities.PocDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
public class PocGWTServiceImpl extends RemoteServiceServlet implements PocGWTService {
	private static final long serialVersionUID = -8849195396928815111L;
	private PocDao pocDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		pocDao    = context.getBean("pocDao",   PocDao.class);
	}
	
	@Override
	public ArrayList<PocDTO> findLastPocs() {
		return PocMapper.toDTOs(pocDao.findAll());
	}

	@Override
	public Integer saveOrUpdate(PocDTO entity) {
		return 11;
	}
	
}
