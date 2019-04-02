package com.app.sugarcrush.repo;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Generic DAO used in all Places where CRUDRepository or JPARepository interfaces cannot be used 
 * */
@Repository
public abstract class AbstractDao<IdWrapper extends java.io.Serializable , T> {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		/*
		 * SRC 1: http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
		 *   SUB: https://stackoverflow.com/questions/13008811/get-generic-superclass-of-generic-parameter
		 * */
		persistentClass = 	(Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	
	public Session getSession() {
		/*
		 * If we use sessionFactory.openSession then  we are responsible for closing the session
		 * */
		return sessionFactory.getCurrentSession();
	}
	
	
	public T getById(IdWrapper id) {
		return getSession().get(persistentClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
		return getSession().createCriteria(persistentClass).list();
	}
	
	public T saveOrUpdate(T t) {
			getSession().saveOrUpdate(t); 
			return t;
	}
	
	public void delete(T t) {
		getSession().delete(t);
	}
	
	@SuppressWarnings("unchecked")
	public void delete(long id)  {
		T element = getById((IdWrapper) new Long(id));
		if(element !=null) {
			delete(element); 
		}
	}
	
	/**
	 * Criteria : Alternate ways of manipulating objects & data available in RDBMS tables.
	 *    We create Criteria query programmatically to filter objects fetched from DB.
	 *     RESTRICTIONS:Restrictions are added to filter data
	 *       EG:
	 *          Criteria criteria = session.createCriteria(Employee.class);
	 *          criteria.add(Restrictions.eq("salary", 2000));
	 *          	Criterion salary = Restrictions.gt("salary", 2000);
	 *          	Criterion name = Restrictions.ilike("firstNname","zara%");
	 *          	LogicalExpression orExp = Restrictions.or(salary, name);
	 *          criteria.add(orExp)
	 *     PAGINATION:Criteria supports pagination from the core
	 *     		criteria.setFirstResult(1);
	 *     		criteria.setMaxResults(10);
	 *  	PROJECTIONS: Projections are like Aggregate functions in SQL 
	 *  		 cr.setProjection(Projections.rowCount());
	 * */
	public Criteria getCriteria() {
		return getSession().createCriteria(persistentClass);
	}
	
	
}
