package com.learn.daos;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.learn.models.User;


@Transactional
public interface UserDaoCustom{
	/**
	 * User.nameΜͺκvυ
	 * @param name
	 * @return
	 */
	public List<User> findLikeName(String name);
}
