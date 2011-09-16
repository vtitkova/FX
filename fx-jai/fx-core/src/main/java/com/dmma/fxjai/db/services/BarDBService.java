package com.dmma.fxjai.db.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.fxjai.core.entities.BarDTO;
import com.dmma.fxjai.core.types.PeriodType;
import com.dmma.fxjai.core.types.SymbolType;
import com.dmma.fxjai.db.daos.BarD1Dao;
import com.dmma.fxjai.db.daos.BarMNDao;
import com.dmma.fxjai.db.daos.BarW1Dao;
import com.dmma.fxjai.db.entities.BarD1;
import com.dmma.fxjai.db.mappers.BarD1Mapper;

public class BarDBService {
	private static final Logger log = LoggerFactory.getLogger(BarDBService.class);
	private BarD1Dao barD1Dao;
	public void setBarD1Dao(BarD1Dao barD1Dao) {
		this.barD1Dao = barD1Dao;
	}
	private BarW1Dao barW1Dao;
	public void setBarW1Dao(BarW1Dao barW1Dao) {
		this.barW1Dao = barW1Dao;
	}
	private BarMNDao barMNDao;
	public void setBarMNDao(BarMNDao barMNDao) {
		this.barMNDao = barMNDao;
	}
	
	public BarDBService() {
	}

	
	
	public void saveOrUpdate(BarDTO bar) {
		PeriodType period = PeriodType.findById(bar.getPeriod());
		switch (period) {
		case isD1:
			BarD1 existing = BarD1Mapper.mapToEntity(bar, null);
			barD1Dao.saveOrUpdate(existing);
			break;
		case isW1:
			
			break;
		case isMN:
			
			break;
		}
	}

	public BarDTO barDBService(Integer accountId, SymbolType symbol,PeriodType period) {
		switch (period) {
		case isMN:
			
			break;
		case isW1:
			
			break;
		case isD1:
			BarD1Mapper.toDTO(barD1Dao.findLast(accountId, symbol));
			break;
		}
		return null;
	}

	
	

	
	
	
}
