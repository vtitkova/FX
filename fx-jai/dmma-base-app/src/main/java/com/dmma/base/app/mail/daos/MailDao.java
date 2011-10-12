package com.dmma.base.app.mail.daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dmma.base.app.daos.base.BaseDao;
import com.dmma.base.app.mail.entities.Mail;

public class MailDao  extends BaseDao<Mail, Integer>{

	public MailDao() {
		super(Mail.class);
	}

	@SuppressWarnings("unchecked")
	public List<Mail> findAll(Integer statusId, Integer mailTemplateId, Date dateFrom, Date dateTo) {
		Session sesion = sessionFactory.openSession();
		try{
			StringBuffer SQL = new StringBuffer();
			SQL.append("from Mail where 1=1 ");
			if(statusId != null)
				SQL.append(" and status = ? ");
			if(dateFrom != null)
				SQL.append(" and created >= ? ");
			if(dateTo != null)
				SQL.append(" and created <= ? ");
			if(mailTemplateId != null){
				SQL.append(" AND templateName in (select name from MailTemplate where id = ? ) ");
			}
			SQL.append(" order by id desc ");

			Query query = sesion.createQuery(SQL.toString());
			int i = 0;
			if(statusId != null){
				query.setInteger(i, statusId);
				i++;
			}
			if(dateFrom != null){
				query.setDate(i, dateFrom);
				i++;
			}
			if(dateTo != null){
				query.setDate(i, dateTo);
				i++;
			}
			if(mailTemplateId != null){
				query.setInteger(i, mailTemplateId);
				i++;
			}
			List<Mail> list = query.list();
			return list;
		}finally{
			sesion.close();
		}
	}

	public Long countMails(Integer statusId, Integer mailTemplateId,	Date dateFrom, Date dateTo) {
		Session sesion = sessionFactory.openSession();
		try{
		StringBuffer SQL = new StringBuffer();
		SQL.append("select count(*) from Mail where 1=1 ");
		if(statusId != null)
			SQL.append(" and status = ? ");
		if(dateFrom != null)
			SQL.append(" and created >= ? ");
		if(dateTo != null)
			SQL.append(" and created <= ? ");
		if(mailTemplateId != null){
			SQL.append(" AND templateName in (select name from MailTemplate where id = ? ) ");
		}
		SQL.append(" order by id desc ");

		Query query = sesion.createQuery(SQL.toString());
		int i = 0;
		if(statusId != null){
			query.setInteger(i, statusId);
			i++;
		}
		if(dateFrom != null){
			query.setDate(i, dateFrom);
			i++;
		}
		if(dateTo != null){
			query.setDate(i, dateTo);
			i++;
		}
		if(mailTemplateId != null){
			query.setInteger(i, mailTemplateId);
			i++;
		}
		query.setCacheable(false);
		return (Long) query.uniqueResult();
		}finally{
			sesion.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mail> findMails(int start, int maxResults, Integer statusId, Integer mailTemplateId, Date dateFrom, Date dateTo) {
		Session sesion = sessionFactory.openSession();
		try{
			StringBuffer SQL = new StringBuffer();
			SQL.append("from Mail where 1=1 ");
			if(statusId != null)
				SQL.append(" and status = ? ");
			if(dateFrom != null)
				SQL.append(" and created >= ? ");
			if(dateTo != null)
				SQL.append(" and created <= ? ");
			if(mailTemplateId != null){
				SQL.append(" AND templateName in (select name from MailTemplate where id = ? ) ");
			}
			SQL.append(" order by id desc ");

			Query query = sesion.createQuery(SQL.toString());
			query.setFirstResult(start);
			query.setMaxResults(maxResults);
			int i = 0;
			if(statusId != null){
				query.setInteger(i, statusId);
				i++;
			}
			if(dateFrom != null){
				query.setDate(i, dateFrom);
				i++;
			}
			if(dateTo != null){
				query.setDate(i, dateTo);
				i++;
			}
			if(mailTemplateId != null){
				query.setInteger(i, mailTemplateId);
				i++;
			}
			List<Mail> list = query.list();
			return list;
		}finally{
			sesion.close();
		}
	}


}
