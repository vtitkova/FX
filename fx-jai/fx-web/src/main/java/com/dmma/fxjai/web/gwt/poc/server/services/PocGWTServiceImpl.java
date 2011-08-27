package com.dmma.fxjai.web.gwt.poc.server.services;

import java.util.ArrayList;
import java.util.Date;

import com.dmma.fxjai.web.gwt.poc.client.services.PocGWTService;
import com.dmma.fxjai.web.gwt.poc.shared.entities.PocDTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
public class PocGWTServiceImpl extends RemoteServiceServlet implements PocGWTService {
	private static final long serialVersionUID = -8849195396928815111L;

	@Override
	public ArrayList<PocDTO> findLastPocs() {
		ArrayList<PocDTO> pocs = new ArrayList<PocDTO>();
		for(int i = 0 ; i < 10 ; i++){
			PocDTO p = new PocDTO();
			p.setId(i);
			p.setText("someText"+i);
			p.setCreated(new Date());
			pocs.add(p);
		}
		return pocs;
	}

	@Override
	public Integer saveOrUpdate(PocDTO entity) {
		return 11;
	}
	
	/*protected PocService   bankOfficeService;
	private   UserRoleService     userRoleService;
	
	@Override
	protected void initMe(WebApplicationContext context) {
		bankOfficeService = context.getBean("bankOfficeService", BankOfficeService.class);
		userRoleService   = context.getBean("userRoleService",   UserRoleService.class);
	}*/



}
