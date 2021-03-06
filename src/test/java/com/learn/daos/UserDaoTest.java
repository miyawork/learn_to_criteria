package com.learn.daos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
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
	
	@BeforeEach
	public void initialize() {
		// レコード初期化
		userDao.deleteAll();
	}

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
		
		List<User> resultDataFirst = StreamSupport.stream(userDao.findAll().spliterator(), false).toList();
		assertEquals(resultDataFirst.size(), 4, "無条件に取得");
		
		List<User> resultDataSecond = userDaoCustom.findLikeName("not");
		assertEquals(resultDataSecond.size(), 1, "登録後に取得");
		
	}
	@Test
	public void whenSetNullValueToNotNullColumn_thenFail() {

		// 名前がnotnull
		User saveData = new User("email.com", null);
		try {
			userDao.save(saveData);
			fail("not nullなのに 通過してしまった");
		}catch (Exception e) {
			System.out.print(e);
		}
	}
}
