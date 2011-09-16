package com.dmma.fxjai.db.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dmma.base.app.daos.base.BaseDao;
import com.dmma.fxjai.core.types.SymbolType;
import com.dmma.fxjai.db.entities.BarD1;

public class BarD1Dao  extends BaseDao<BarD1, Long>{
	
	public BarD1Dao() {
		super(BarD1.class);
	}

	@SuppressWarnings("unchecked")
	public BarD1 findLast(Integer accountId, SymbolType symbol) {
		Session sesion = sessionFactory.openSession();
		try{
			Query query = sesion.createQuery("from BarD1 where clientId = ? and symbolId = ? order by date desc");
			query.setInteger(0, accountId);
			query.setInteger(1, symbol.getId());
			List<BarD1> list = query.list();
			if(list!=null&&list.size()>0)
				return list.get(0);
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			sesion.close();
		}
	}
	
}
