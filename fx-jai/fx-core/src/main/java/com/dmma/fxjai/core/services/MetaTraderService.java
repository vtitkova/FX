package com.dmma.fxjai.core.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.fxjai.db.daos.PocDao;
import com.dmma.fxjai.db.entities.Account;
import com.dmma.fxjai.db.entities.Poc;
import com.dmma.fxjai.db.services.AccountDBService;
import com.dmma.fxjai.db.services.BarDBService;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.types.AccountType;
import com.dmma.fxjai.shared.shared.types.PeriodType;
import com.dmma.fxjai.shared.shared.types.SymbolType;

public class MetaTraderService {
	private static final Logger log = LoggerFactory.getLogger(MetaTraderService.class);
	private PocDao           pocDao;
	private AccountDBService accountDBService;
	private BarDBService     barDBService;

	public void setPocDao(PocDao pocDao) {
		this.pocDao = pocDao;
	}
	public void setAccountDBService(AccountDBService accountDBService) {
		this.accountDBService = accountDBService;
	}
	public void setBarDBService(BarDBService barDBService) {
		this.barDBService = barDBService;
	}
	
	
	public int processRegistration(String account, AccountType accountType, String userName, String serverName) {
		Account acc = accountDBService.findByAccount(account);
		if(acc == null){
			log.info("New client registration");
			acc = new Account();
			acc.setCreated(new Date());
			acc.setAccount(account);
		}
		acc.setAccountType(accountType.getId());
		acc.setUserName(userName);
		acc.setServerName(serverName);
		accountDBService.saveOrUpdate(acc);
		
		return acc.getId();
	}
	
	
	
	public void setActual(String account, Integer accountId, SymbolType symbol, Double bid, Date timeStamp){
		Poc entity = new Poc();
		entity.setCreated(timeStamp);
		String aaa = account+":"+symbol+":"+bid;
		log.info("Saving: " + aaa);
		entity.setText(aaa);
		pocDao.saveOrUpdate(entity);
	}


	public void updateBar(Integer accountId, SymbolType symbol, PeriodType period, BarDTO bar) {
		 barDBService.saveOrUpdate(accountId,symbol, period, bar);
	}
	
	public BarDTO getLastBar(String account, Integer accountId,	SymbolType symbol, PeriodType period) {
		return barDBService.getLastBar(accountId,symbol, period);
	}



}
