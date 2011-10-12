package com.dmma.base.app.mail.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dmma.base.app.daos.base.BaseDao;
import com.dmma.base.app.mail.entities.MailTemplate;

public class MailTemplateDao  extends BaseDao<MailTemplate, Integer>{
	
	public MailTemplateDao() {
		super(MailTemplate.class);
	}
	
	@SuppressWarnings("unchecked")
	public boolean nameExist(String key) {
		Session sesion = sessionFactory.openSession();
		try{
			Query query = sesion.createQuery("from MailTemplate where name = ?");
			List<MailTemplate> list = query.setString(0, key).list();
			if(list!=null&&list.size()>0)
				return true;
			return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return true;
		}finally{
			sesion.close();
		}
	}

	@SuppressWarnings("unchecked")
	public MailTemplate findByName(String key) {
		Session sesion = sessionFactory.openSession();
		try{
			Query query = sesion.createQuery("from MailTemplate where name = ?");
			List<MailTemplate> list = query.setString(0, key).list();
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

	public ArrayList<String> findAllNames() {
		ArrayList<String> retVal = new ArrayList<String>();
		for(MailTemplate t: findAll()){
			retVal.add(t.getName());
		}
		return retVal;
	}
	
}
