package com.learn.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.learn.models.User;

/**
 * ユーザDAO。Spring上ではdaosフォルダ内にあることで結びついている。
 * findByIdやsaveなど基本処理はCrudRepository側のメソッドで行ってくれる。
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

}
