package com.learn.daos.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import com.learn.daos.UserDao;
import com.learn.daos.UserDaoCustom;
import com.learn.models.User;

/**
 * UserDaoCustomの実装。UserDaoとは名称(UserDao + Impl)で結びついている。
 */
public class UserDaoImpl extends CommonCustomImpl<User> implements UserDaoCustom{


	/**
	 *  追加する先のDAO。@Lazyによって遅延初期化されているそう。
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
