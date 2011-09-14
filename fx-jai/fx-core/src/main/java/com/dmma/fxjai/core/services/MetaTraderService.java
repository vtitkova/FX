package com.dmma.fxjai.core.services;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.fxjai.core.types.SymbolType;
import com.dmma.fxjai.db.daos.PocDao;
import com.dmma.fxjai.db.entities.Poc;

public class MetaTraderService {
	private static final Logger log = LoggerFactory.getLogger(MetaTraderService.class);
	private PocDao pocDao;
	
	public void setActual(String account, SymbolType symbol, Double bid, Date timeStamp){
		Poc entity = new Poc();
		entity.setCreated(timeStamp);
		String aaa = account+":"+symbol+":"+bid;
		log.info("Saving: " + aaa);
		entity.setText(aaa);
		pocDao.saveOrUpdate(entity);
	}


	public void setPocDao(PocDao pocDao) {
		this.pocDao = pocDao;
	}
}
