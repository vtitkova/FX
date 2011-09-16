package com.dmma.fxjai.db.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.base.app.services.base.BaseService;
import com.dmma.fxjai.db.daos.AccountDao;
import com.dmma.fxjai.db.entities.Account;

public class AccountDBService implements BaseService<Account, Integer>{
	private static final Logger log = LoggerFactory.getLogger(AccountDBService.class);
	private AccountDao dao;
	public void setAccountDao(AccountDao accountDao) {
		this.dao = accountDao;
	}
	
	@Override
	public Account findById(Integer id) {
		log.debug("findById:"+id);
		return dao.findById(id);
	}
	@Override
	public void saveOrUpdate(Account entity) {
		log.debug("saveOrUpdate");
		dao.saveOrUpdate(entity);
	}
	@Override
	public void delete(Account entity) {
		log.debug("delete");
		dao.delete(entity);
	}
	@Override
	public List<Account> findAll() {
		log.debug("findAll");
		return dao.findAll();
	}
	@Override
	public List<Integer> findAllIDs() {
		log.debug("findAllIDs");
		return dao.findAllIDs();
	}

	public Account findByAccount(String name){
		log.debug("findByAccount"+name);
		return dao.findByAccount(name);
	}

	
}
