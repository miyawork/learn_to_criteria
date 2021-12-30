package com.learn.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.learn.models.User;

/**
 * ���[�U��DAO�Bdaos�ɒu����ĂȂ���΂Ȃ�Ȃ��B
 * findById��save�ȂǊ�{�I�ȑ����CrudRepository�ɋl�܂��Ă���B
 */
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

}
