/**
 * AbstractFacade.java
 */
package com.ftm.iamweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ftm.iamcore.services.dao.DAO;

/**
 * Class that use a dao implementation, previously injected via Spring
 * @author Favio
 *
 * @param <T> - any object
 */
public abstract class AbstractFacade<T> {

	protected Class<T> entityClass;

	@Autowired 	
	private DAO<T> dao;	
	
	public DAO<T> getDao() {
		return dao;
	}

	public void setDao(DAO<T> dao) {
		this.dao = dao;
	}

	/**
	 * Invoke {@code DAO.create} to persist an object in the database
	 * @param entity - any object
	 */
	public void create(T entity) {
		dao.create(entity);
	}
	
	/**
	 * Invoke {@code DAO.update} to modify an object in the database
	 * @param entity - any object
	 */
	public void edit(T entity) {
		dao.update(entity);
	}
	
	/**
	 * Invoke {@code DAO.delete} to delete one record in the database
	 * @param entity - any object
	 */
	public void remove(T entity) {
		dao.delete(entity);
	}
	
	/**
	 * Invoke {@code DAO.searchUnique} to find one record in the database base on the criteria 
	 * passed with <i>entity</i> object
	 * @param entity - any object 
	 * @return T - any object
	 */
	public T find(T entity) {
		return dao.searchUnique(entity);
	}
	
	/**
	 * Invoke {@code DAO.readAll} to find all the occurrences in the database
	 * @return List&lt;T&gt; - where T is any object
	 */
	public List<T> findAll() {
		return dao.readAll(entityClass);
	}
}