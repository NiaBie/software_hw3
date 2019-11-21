package com.example.springweb;

import com.example.springweb.dao.UserDetail;
import com.example.springweb.service.UserService;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	UserService userService;

	@Test
	public void test1() {// 测试用户添加
		String uid = "nmnsdf";
		String userName = "hahaha";
		String password = "123456";
		UserDetail userSet = new UserDetail();

		userSet.setUid(uid);
		userSet.setUserName(userName);
		userSet.setPassword(password);

		userService.addUser(userSet);

		UserDetail userGet = userService.getByUid(uid);
		assertEquals(userGet.getUid(), uid);
		assertEquals(userGet.getUserName(), userName);
		assertEquals(userGet.getPassword(), password);

		infoLog("test1 pass");
	}

	@Test
	public void test2() {// 测试总用户数目变化
		int userNum = userService.getUserList().size();

		String uid = "nm3mnm";
		String userName = "hahaha";
		String password = "123456";
		UserDetail userSet = new UserDetail();

		userSet.setUid(uid);
		userSet.setUserName(userName);
		userSet.setPassword(password);

		userService.addUser(userSet);

		assertEquals(userNum + 1, userService.getUserList().size());

		infoLog("test2 pass");
	}

	@Test
	public void mainTest() {
		test1();
		test2();
	}

	public void infoLog(String log) {
		System.out.println("\n" + log + "\n");
	}

}
