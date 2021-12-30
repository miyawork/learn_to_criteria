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
		// 初期化
	    initializeCriteria();
	    
	    // queryを編集していくことで
	    criteriaQuery
	    // select。この場合は全件選択
	      .select(root)
	      // nameの部分一致
	      .where(builder.like(
	    		  root.get("name"), "%" + name + "%"))
	      // 順序なども実装可能。
	      .orderBy(builder.asc(root.get("name")));
	    
	    // 結果を返す
	    TypedQuery<User> query = em.createQuery(criteriaQuery);
	    return query.getResultList();
	}

}
