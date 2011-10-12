package com.dmma.fxjai.web.gwt.core.server.services;

import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dmma.base.gwt.shared.dtos.ListBoxDTO;
import com.dmma.fxjai.db.daos.AccountDao;
import com.dmma.fxjai.web.gwt.core.client.services.AccountGWTService;
import com.dmma.fxjai.web.gwt.core.server.mappers.AccountMapper;
import com.dmma.fxjai.web.gwt.core.shared.entities.AccountDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
public class AccountGWTServiceImpl extends RemoteServiceServlet implements AccountGWTService {
	private static final long serialVersionUID = -8849195396928815111L;
	private AccountDao accountDao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		accountDao    = context.getBean("accountDao",   AccountDao.class);
	}
	
	@Override
	public ArrayList<AccountDTO> findAll() {
		return AccountMapper.toDTOs(accountDao.findAll());
	}

	@Override
	public ArrayList<ListBoxDTO> findAllShort() {
		ArrayList<AccountDTO> found = findAll();
		if(found == null)
			return null;
		
		ArrayList<ListBoxDTO> retVal = new ArrayList<ListBoxDTO>(found.size());
		for(AccountDTO one: found){
			ListBoxDTO dto = new ListBoxDTO(one.getId(), one.getAccount() +" "+one.getServerName());
			retVal.add(dto);
		}
		return retVal;
	}

}
