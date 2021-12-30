package com.learn.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.learn.models.User;

/**
 * ユーザのDAO。daosに置かれてなければならない。
 * findByIdやsaveなど基本的な操作はCrudRepositoryに詰まっている。
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

}
