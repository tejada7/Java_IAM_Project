/**
 * DAO.java
 */

package com.ftm.iamcore.services.dao;

import java.util.List;

/**
 * CRUD interface, takes a type T to create a generic implementation.
 * @author Favio
 *
 * @param <T> any object
 */
public interface DAO<T> {

	/**
	 * Persist an object in the database
	 * @param entity object to be persisted
	 */
	public void create(T entity);
	
	/**
	 * Read all the occurrences in the table
	 * @param clazz type of class to be read
	 * @return list of occurrences
	 */
	public List<T> readAll(Class<T> clazz);
	
	/**
	 * Modify one object in the database
	 * @param entity to be modified
	 */
	public void update(T entity);
	
	/**
	 * Delete one object in the database
	 * @param entity to be deleted
	 */
	public void delete(T entity);
	
	/**
	 * Search one or many occurrences given an object
	 * @param entity with filled fields to be searched in the database
	 * @return a list of ocurrences
	 */
	public List<T> search(T entity); 
	
	/**
	 * Search one occurrence of a given object
	 * @param entity to be searched
	 * @return the object if found
	 */
	public T searchUnique(T entity);
}
