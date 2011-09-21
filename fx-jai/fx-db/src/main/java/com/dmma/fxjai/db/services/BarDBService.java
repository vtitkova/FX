package com.dmma.fxjai.db.services;

import com.dmma.fxjai.db.daos.BarD1Dao;
import com.dmma.fxjai.db.daos.BarMNDao;
import com.dmma.fxjai.db.daos.BarW1Dao;
import com.dmma.fxjai.db.entities.BarD1;
import com.dmma.fxjai.db.entities.BarMN;
import com.dmma.fxjai.db.entities.BarW1;
import com.dmma.fxjai.db.mappers.BarD1Mapper;
import com.dmma.fxjai.db.mappers.BarMNMapper;
import com.dmma.fxjai.db.mappers.BarW1Mapper;
import com.dmma.fxjai.shared.dto.BarDTO;
import com.dmma.fxjai.shared.types.PeriodType;
import com.dmma.fxjai.shared.types.SymbolType;

public class BarDBService {
	//private static final Logger log = LoggerFactory.getLogger(BarDBService.class);
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
		case isMN1:
			BarMN existingMN = barMNDao.findByOpenDateTime(bar.getClientId(), bar.getSymbolId(), bar.getOpenDateTime());
			barMNDao.findById(41l); 
			existingMN = BarMNMapper.mapToEntity(bar, existingMN);
			barMNDao.saveOrUpdate(existingMN);
			break;
		case isW1:
			BarW1 existingW1 = barW1Dao.findByOpenDateTime(bar.getClientId(), bar.getSymbolId(), bar.getOpenDateTime());
			existingW1 = BarW1Mapper.mapToEntity(bar, existingW1);
			barW1Dao.saveOrUpdate(existingW1);
			break;
		case isD1:
			BarD1 existingD1 = barD1Dao.findByOpenDateTime(bar.getClientId(), bar.getSymbolId(), bar.getOpenDateTime());
			existingD1 = BarD1Mapper.mapToEntity(bar, existingD1);
			barD1Dao.saveOrUpdate(existingD1);
			break;
		}
	}

	public BarDTO getLastBar(Integer accountId, SymbolType symbol, PeriodType period) {
		switch (period) {
		case isMN1:
			return BarMNMapper.toDTO(barMNDao.findLast(accountId, symbol));
		case isW1:
			return BarW1Mapper.toDTO(barW1Dao.findLast(accountId, symbol));
		case isD1:
			return BarD1Mapper.toDTO(barD1Dao.findLast(accountId, symbol));
		}
		return null;
	}

	
	

	
	
	
}
