package com.learn.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.models.User;

/**
 * userDaoのテスト
 */
@SpringBootTest
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserDaoCustom userDaoCustom;

	/**
	 * 基幹設定に問題あったらここも落ちる
	 */
	@Test
	public void whenDBTest_thenNoExceptions() {
	}

	@Test
	public void whenSaveAndGetUserTest_thenCorrect() {

		List<User> resultDataFirst = StreamSupport.stream(userDao.findAll().spliterator(), false).toList();
		assertEquals(resultDataFirst.size(), 0, "登録前に取得");

		User saveData = new User("email.com", "john");
		userDao.save(saveData);
		List<User> resultDataSecond = StreamSupport.stream(userDao.findAll().spliterator(), false).toList();

		assertEquals(resultDataSecond.size(), 1, "登録後に取得");
	}

	@Test
	public void whenCustomQuery_thenCorrect() {
		List<User> saveDataList = Arrays.asList(new User("email.com", "john")
				, new User("email.com", "john")
				, new User("email.com", "john")
				, new User("not.com", "notjohn"));

		userDao.saveAll(saveDataList);
		
		List<User> resultData = userDaoCustom.findLikeName("not");
		assertEquals(resultData.size(), 1, "登録後に取得");
		
	}
}
