package com.learn.daos.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.learn.daos.UserDao;
import com.learn.daos.UserDaoCustom;
import com.learn.models.User;

public class UserDaoImpl extends commonCustomImpl<User> implements UserDaoCustom{


	/**
	 * Œ³‚ÌDAOBLazy‚É‚æ‚é’x‰„‰Šú‰»‚ğs‚Á‚Ä‚¢‚é
	 * https://mkyong.com/spring-data/spring-data-add-custom-method-to-repository/
	 */
    @Autowired
    @Lazy
    UserDao userDao;
    

    /**
     * 
     */
	@Override
	public List<User> findLikeName(String name) {
	    initializeCriteria();
	    criteriaQuery
	      .select(root)
	      .where(builder.like(root.get("name"), "%" + name + "%"));
	    
	    TypedQuery<User> query = em.createQuery(criteriaQuery);
	    return query.getResultList();
	}

}
