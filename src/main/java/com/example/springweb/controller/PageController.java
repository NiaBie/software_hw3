package com.example.springweb.controller;

import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(PageController.class);

    // 从主页跳转到任何页面
    @RequestMapping("/{page}")
    public String changePage(@PathVariable("page") String page) {
        infoLog("from index");
//        logger.info("hello logging" + helloService.getUserList());// TODO 数据库
        return page;
    }

    // 在登录页面
    @RequestMapping("/login/{page}")
//    public String login(GetLogin getLogin, @PathVariable("page") String page) {
    public String login(GetLogin getLogin) {
        String page = "";
        infoLog("page: " + page);
        infoLog("account: " + getLogin);
        return "login/" + page;
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }

    public class GetLogin {
//        String a;
//        String b;
        String account;
//        String password;
    }
}
