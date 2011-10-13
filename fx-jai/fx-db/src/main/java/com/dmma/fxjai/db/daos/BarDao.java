package com.dmma.fxjai.db.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmma.base.app.daos.base.BaseDao;
import com.dmma.fxjai.shared.shared.dto.BarDTO;

public class BarDao{
	protected static final Logger log = LoggerFactory.getLogger(BaseDao.class);
	protected SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<BarDTO> find(final String tableName, Integer openDateTimeFrom, Integer openDateTimeTo, Boolean orderDesc) {
		Session sesion = sessionFactory.openSession();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("select openDateTime, open, high, low, close, volume from ");
			sb.append(tableName);
			sb.append(" where 1=1 " );
			
			if(openDateTimeFrom != null){
				sb.append("AND openDateTime >= ? " );
			}
			if(openDateTimeTo != null){
				sb.append("AND openDateTime <= ? " );
			}
			if(orderDesc != null && orderDesc)
				sb.append(" order by openDateTime desc ");
			else
				sb.append(" order by openDateTime asc ");
			
			
			Query query = sesion.createSQLQuery(sb.toString());
			int i = 0;
			if(openDateTimeFrom != null){
				query.setInteger(i, openDateTimeFrom);
				i++;
			}
			if(openDateTimeTo != null){
				query.setInteger(i, openDateTimeTo);
				i++;
			}
			
			query.setResultTransformer(Transformers.aliasToBean(BarDTO.class));
			List<BarDTO> list = query.list();
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			sesion.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<BarDTO> findAll(final String tableName) {
		Session sesion = sessionFactory.openSession();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("select openDateTime, open, high, low, close, volume from ");
			sb.append(tableName);
			sb.append(" order by openDateTime asc ");
			
			Query query = sesion.createSQLQuery(sb.toString());
			query.setResultTransformer(Transformers.aliasToBean(BarDTO.class));
			List<BarDTO> list = query.list();
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			sesion.close();
		}
	}

	public BarDTO getLastBar(final String tableName) {
		Session sesion = sessionFactory.openSession();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("select openDateTime, open, high, low, close, volume from ");
			sb.append(tableName);
			sb.append(" order by openDateTime desc ");
			
			Query query = sesion.createSQLQuery(sb.toString());
			query.setMaxResults(1);
			query.setResultTransformer(Transformers.aliasToBean(BarDTO.class));
			BarDTO result = (BarDTO) query.uniqueResult();
			return result;
		}
		catch (Exception e) {
			createTable(tableName);
			return null;
		}finally{
			sesion.close();
		}
	}

	public BarDTO findById(final String tableName, Integer openDateTime) {
		Session sesion = sessionFactory.openSession();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("select openDateTime, open, high, low, close, volume from ");
			sb.append(tableName);
			sb.append(" where openDateTime = ? ");
			
			Query query = sesion.createSQLQuery(sb.toString());
			query.setInteger(0, openDateTime);
			query.setResultTransformer(Transformers.aliasToBean(BarDTO.class));
			
			BarDTO result = (BarDTO) query.uniqueResult();
			return result;
		}
		catch (Exception e) {
			createTable(tableName);
			return null;
		}finally{
			sesion.close();
		}
	}
	
	public Boolean createTable(final String tableName){
		Session sesion = sessionFactory.openSession();
		try{
			StringBuffer sb = new StringBuffer();
			sb.append("CREATE TABLE `").append(tableName).append("` (");
			sb.append("`openDateTime` int(11) NOT NULL, ");
			sb.append("`open`   double NOT NULL, ");
			sb.append("`high`   double NOT NULL, ");
			sb.append("`low`    double NOT NULL, ");
			sb.append("`close`  double NOT NULL, ");
			sb.append("`volume` int(11) NOT NULL, ");
			sb.append("PRIMARY KEY (`openDateTime`)");
			sb.append(")");
			
			Query query = sesion.createSQLQuery(sb.toString());
			query.executeUpdate();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			sesion.close();
		}
	}
	
	public void update(final String tableName, BarDTO existing) {
		Session sesion = sessionFactory.openSession();
		Transaction tx = sesion.getTransaction();
		try{
			tx.begin();
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE `").append(tableName).append("` ");
			sb.append("SET  `open` = ? , `high` = ? , `low` = ? ,  `close` = ? , `volume` = ? ");
			sb.append("WHERE `openDateTime` = ? ");
			
			Query query = sesion.createSQLQuery(sb.toString());
			query.setDouble(0, existing.getOpen());
			query.setDouble(1, existing.getHigh());
			query.setDouble(2, existing.getLow());
			query.setDouble(3, existing.getClose());
			query.setInteger(4, existing.getVolume());
			query.setInteger(5, existing.getOpenDateTime());
			query.executeUpdate();
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			sesion.close();
		}
	}


	public void create(String tableName, BarDTO existing) {
		Session sesion = sessionFactory.openSession();
		Transaction tx = sesion.getTransaction();
		try{
			tx.begin();
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO `").append(tableName).append("` (`openDateTime`, `open`, `high`, `low`, `close`, `volume`) ");
			sb.append("VALUES (? , ? , ? , ? , ? , ? )");
						
			Query query = sesion.createSQLQuery(sb.toString());
			query.setInteger(0, existing.getOpenDateTime());
			query.setDouble(1, existing.getOpen());
			query.setDouble(2, existing.getHigh());
			query.setDouble(3, existing.getLow());
			query.setDouble(4, existing.getClose());
			query.setInteger(5, existing.getVolume());
			query.executeUpdate();
			tx.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			sesion.close();
		}
	}
	
	
}
