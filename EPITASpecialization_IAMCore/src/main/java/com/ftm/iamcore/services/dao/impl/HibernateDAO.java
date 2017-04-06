package com.ftm.iamcore.services.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftm.iamcore.services.dao.DAO;
import com.ftm.iamcore.utils.WhereClauseBuilder;

/**
 * DAO implementation using Hibernate
 * @author Favio
 *
 * @param <T> any class, eg. Identity
 */
@Service
public class HibernateDAO<T> implements DAO<T>, Serializable{

	private static final long serialVersionUID = 1L;
	
	//logger
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	//Hibernate session factory
	@Autowired
	private SessionFactory sessionFactory;	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(T entity) {		
		LOGGER.info("Persisting entity to database...");
		Session session = openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		closeSession(session);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> readAll(Class<T> clazz) {
		LOGGER.info("Retrieving entities from database...");
		Session session = openSession();	
		StringBuilder hql = new StringBuilder("from ")
				.append(clazz.getSimpleName());
		List<T> listEntities = session.createQuery(hql.toString()).list();			
		closeSession(session);
		LOGGER.info(listEntities.toString());
		return listEntities;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(T entity) {		
		LOGGER.info("Editing entity from database");
		Session session = openSession();		
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();		
		closeSession(session);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(T entity) {
		LOGGER.info("Deleting entity from database...");
		Session session = openSession();		
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();		
		closeSession(session);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> search(T entity) {	
		LOGGER.info("Searching entities from databse");
		Session session = openSession();
		StringBuilder hql = new StringBuilder("from ")
				.append(entity.getClass().getSimpleName())				
				.append(WhereClauseBuilder.getClause(entity));
		Query query = session.createQuery(hql.toString());
		List<T> listEntities = query.list();
		closeSession(session);
		return listEntities;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T searchUnique(T entity) {	
		LOGGER.info("Searching entity from databse");
		Session session = openSession();
		StringBuilder hql = new StringBuilder("from ")
				.append(entity.getClass().getSimpleName())
				.append(WhereClauseBuilder.getClause(entity));
		Query query = session.createQuery(hql.toString());
		T result = (T) query.uniqueResult();
		closeSession(session);
		return result;
	}

	/**
	 * open a hibernate session 
	 * @return the created session
	 */
	private Session openSession() {
		Session session = sessionFactory.openSession();
		return session;
	}

	/**
	 * close a hibernate session
	 * @param session
	 */
	private void closeSession(Session session) {
		session.close();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
}