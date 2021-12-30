package com.learn.daos.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.core.GenericTypeResolver;

/**
 * 共通のカスタムDAO用クラス。
 *
 * @param <T> entity名。
 */
public class commonCustomImpl<T> {


	@PersistenceContext
    protected EntityManager em;
    
    protected CriteriaBuilder builder ;
    protected CriteriaQuery<T> criteriaQuery ;
    protected Root<T> root ;
    
    Class<T> domainClass;
    
    /**
     * initialize。
     */
    @SuppressWarnings("unchecked")
	public void initializeCriteria () {
    	domainClass = (Class<T>) GenericTypeResolver
                .resolveTypeArgument(getClass(), commonCustomImpl.class);

    	builder = em.getCriteriaBuilder();
    	criteriaQuery = builder.createQuery(domainClass);
    	root = criteriaQuery.from(domainClass);
    }
}
