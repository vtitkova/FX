package com.dmma.base.app.services.base;

import java.io.Serializable;
import java.util.List;


public interface BaseService<T,  ID extends Serializable>{
	public T        findById(ID id);
	public void     saveOrUpdate(T entity);
	public void     delete(T entity);
	public List<T>  findAll();
	public List<ID> findAllIDs();
}
