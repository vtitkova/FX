package com.dmma.fxjai.db.services;

import java.util.List;

import com.dmma.fxjai.db.daos.BarDao;
import com.dmma.fxjai.shared.shared.dto.BarDTO;
import com.dmma.fxjai.shared.shared.types.PeriodType;
import com.dmma.fxjai.shared.shared.types.SymbolType;

public class BarDBService {
	//private static final Logger log = LoggerFactory.getLogger(BarDBService.class);
	private BarDao barDao;
	public void setBarDao(BarDao barDao) {
		this.barDao = barDao;
	}
	
	public BarDBService() {
	}
	
	public void saveOrUpdate(Integer clientId, SymbolType symbol, PeriodType period, BarDTO bar) {
		boolean upd = true;
		BarDTO existing = barDao.findById(createTableName(clientId, symbol, period), bar.getOpenDateTime());
		
		if(existing == null){
			existing = new BarDTO();
			upd = false;
		}
		existing.setOpen(  bar.getOpen());
		existing.setHigh(  bar.getHigh());
		existing.setLow(   bar.getLow());
		existing.setClose( bar.getClose());
		existing.setVolume(bar.getVolume());
		
		if(upd){
			barDao.update(createTableName(clientId, symbol, period), existing);
		}else{
			existing.setOpenDateTime( bar.getOpenDateTime());
			barDao.create(createTableName(clientId, symbol, period), existing);
		}
	}

	
	public BarDTO getLastBar(Integer clientId, SymbolType symbol, PeriodType period) {
		return barDao.getLastBar(createTableName(clientId, symbol, period));
	}

	
	public List<BarDTO> findAll(Integer clientId, SymbolType symbol, PeriodType period){
		return barDao.findAll(createTableName(clientId, symbol, period));
	}

	
	public static String createTableName(Integer clientId, SymbolType symbol, PeriodType period){
		StringBuffer sb = new StringBuffer();
		sb.append("bar_").append(clientId).append("_").append(symbol.toString().toLowerCase()).append("_").append(period.toString().toLowerCase());
		return sb.toString();
	}

	
	
}
