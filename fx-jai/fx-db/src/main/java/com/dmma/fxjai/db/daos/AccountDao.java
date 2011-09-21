package com.dmma.fxjai.db.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dmma.base.app.daos.base.BaseDao;
import com.dmma.fxjai.db.entities.Account;

public class AccountDao  extends BaseDao<Account, Integer>{
	
	public AccountDao() {
		super(Account.class);
	}

	@SuppressWarnings("unchecked")
	public Account findByAccount(String account) {
		Session sesion = sessionFactory.openSession();
		try{
			Query query = sesion.createQuery("from Account where account = ?");
			List<Account> list = query.setString(0, account).list();
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
