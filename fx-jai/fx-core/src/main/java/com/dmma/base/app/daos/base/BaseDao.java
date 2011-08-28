package com.dmma.base.app.daos.base;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseDao <T,  ID extends Serializable>{
	protected static final Logger log = LoggerFactory.getLogger(BaseDao.class);
	protected SessionFactory sessionFactory;
	protected Class<T> persistentClass;

	public BaseDao(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		Session sesion = sessionFactory.openSession();
		try{
			return (T) sesion.get(persistentClass, id);
		}finally{
			sesion.close();
		}		
	}

	public void saveOrUpdate(T entity) {
		Session sesion = sessionFactory.openSession();
		Transaction tx = sesion.getTransaction();
		try{
			tx.begin();
			sesion.saveOrUpdate(entity);
			tx.commit();
		}catch (RuntimeException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}finally{
			sesion.close();
		}		
	}

	
	
	public void delete(T entity) {
		Session sesion = sessionFactory.openSession();
		Transaction tx = sesion.getTransaction();
		try{
			tx.begin();
			sesion.delete(entity);
			tx.commit();
		}catch (RuntimeException e) {
			log.error(e.getMessage());
			e.printStackTrace();
			tx.rollback();
		}finally{
			sesion.close();
		}	
	}

	
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Session sesion = sessionFactory.openSession();
		try{
			return sesion.createCriteria(persistentClass)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		}finally{
			sesion.close();
		}
    }
	
	@SuppressWarnings("unchecked")
    public List<ID> findAllIDs() {
		Session sesion = sessionFactory.openSession();
		try{
			 return sesion.createCriteria(persistentClass).setResultTransformer(Criteria.PROJECTION)
             .setProjection(Projections.id()).list();
		}finally{
			sesion.close();
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
