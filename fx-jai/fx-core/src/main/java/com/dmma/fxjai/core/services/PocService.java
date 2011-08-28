package com.dmma.fxjai.core.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.base.app.services.base.BaseService;
import com.dmma.fxjai.core.daos.PocDao;
import com.dmma.fxjai.core.entities.Poc;

public class PocService implements BaseService<Poc, Integer>{
	private static final Logger log = LoggerFactory.getLogger(PocService.class);
	private PocDao pocDao;
	
	@Override
	public Poc findById(Integer id) {
		log.debug("findById");
		return pocDao.findById(id);
	}
	@Override
	public void saveOrUpdate(Poc entity) {
		log.debug("saveOrUpdate");
		pocDao.saveOrUpdate(entity);
	}
	@Override
	public void delete(Poc entity) {
		log.debug("delete");
		pocDao.delete(entity);
	}
	@Override
	public List<Poc> findAll() {
		log.debug("findAll");
		return pocDao.findAll();
	}
	@Override
	public List<Integer> findAllIDs() {
		log.debug("findAllIDs");
		return pocDao.findAllIDs();
	}
	
	public void setPocDao(PocDao pocDao) {
		this.pocDao = pocDao;
	}
}
