package com.dmma.fxjai.core.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.fxjai.core.entities.BarDTO;
import com.dmma.fxjai.core.types.AccountType;
import com.dmma.fxjai.core.types.SymbolType;
import com.dmma.fxjai.db.daos.PocDao;
import com.dmma.fxjai.db.entities.Account;
import com.dmma.fxjai.db.entities.Poc;
import com.dmma.fxjai.db.services.AccountDBService;
import com.dmma.fxjai.db.services.BarDBService;

public class MetaTraderService {
	private static final Logger log = LoggerFactory.getLogger(MetaTraderService.class);
	private PocDao pocDao;
	public void setPocDao(PocDao pocDao) {
		this.pocDao = pocDao;
	}
	private AccountDBService accountDBService;
	public void setAccountDBService(AccountDBService accountDBService) {
		this.accountDBService = accountDBService;
	}
	private BarDBService barDBService;
	public void setBarDBService(BarDBService barDBService) {
		this.barDBService = barDBService;
	}
	
	public void setActual(String account, Integer accountId, SymbolType symbol, Double bid, Date timeStamp){
		Poc entity = new Poc();
		entity.setCreated(timeStamp);
		String aaa = account+":"+symbol+":"+bid;
		log.info("Saving: " + aaa);
		entity.setText(aaa);
		pocDao.saveOrUpdate(entity);
	}


	public void updateBar(String account, BarDTO bar) {
		barDBService.saveOrUpdate(bar);
		// TODO Auto-generated method stub
		
	}

	public int processRegistration(String account, AccountType accountType, String userName, String serverName) {
		Account acc = accountDBService.findByAccount(account);
		if(acc == null){
			log.info("New client registration");
			acc = new Account();
			acc.setAccount(account);
			acc.setAccountType(accountType.getId());
			acc.setUserName(userName);
			acc.setServerName(serverName);
			accountDBService.saveOrUpdate(acc);
		}
		return acc.getId();
	}



}
