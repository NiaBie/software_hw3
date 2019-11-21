package com.example.springweb;

import com.example.springweb.dao.AppDetail;
import com.example.springweb.service.AppService;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceTest {
	@Autowired
	AppService appService;

	String[] appKinds = {"基础共性工业App", "行业通用工业App", "企业专用工业App"};
	String[] dangerProbabilities = {"经常", "很可能", "偶然", "很少", "极少"};
	String[] dangerSeriouses = {"灾难的", "严重的", "轻度的", "轻微的"};
	String[] controlClasses = {"1", "2", "3", "4"};

	@Test
	public void test1() {// 测试添加app审核
		String uid = "111111";// 当前用户
		String appName = "1212";
		String appKind = "基础共性工业App";
		String dangerProbability = "经常";
		String dangerSerious = "灾难的";
		String controlClass = "1";

		int appNum = appService.findAll().size();// 获取所有app数目
		appService.addApp(uid, appName, appKind, dangerProbability, dangerSerious, controlClass);

		AppDetail appGet = appService.getByApp(String.valueOf(appNum));// 默认aid为当前app数目
		assertEquals(appGet.uid, uid);
		assertEquals(appGet.appName, appName);
		assertEquals(appGet.appKind, search(appKinds, appKind));
		assertEquals(appGet.dangerProbability, search(dangerProbabilities, dangerProbability));
		assertEquals(appGet.dangerSerious, search(dangerSeriouses, dangerSerious));
		assertEquals(appGet.controlClass, search(controlClasses, controlClass));

		infoLog("test1 pass");
	}

	@Test
	public void test2() {// 测试当前用户app数目变化
		String uid = "111111";// 当前用户
		String appName = "1212";
		String appKind = "1";
		String dangerProbability = "1";
		String dangerSerious = "1";
		String controlClass = "1";

		int appNum = appService.getByUser(uid).size();// 获取所有app数目
		appService.addApp(uid, appName, appKind, dangerProbability, dangerSerious, controlClass);

		assertEquals(appNum + 1, appService.getByUser(uid).size());

		infoLog("test2 pass");
	}

	@Test
	public void mainTest() {
		test1();
		test2();
	}

	public int search(String a[], String key) {
		for (int i = 0; i < a.length; i ++) {
			if (a[i].equals(key)) {
				return i;
			}
		}
		return -1;
	}

	public void infoLog(String log) {
		System.out.println("\n" + log + "\n");
	}

}
