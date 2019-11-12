package com.example.springweb.controller;

import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    // 从主页跳转到任何页面
    @RequestMapping("/{page}")
    public String changePage(@PathVariable("page") String page) {
        logger.info("fuck");
//        logger.info("hello logging" + helloService.getUserList());// TODO 数据库
        return page;
    }

    // 在登录页面
    @RequestMapping("/login/{page}")
    public String login(@PathVariable("page") String page) {
        return "login/" + page;
    }
}
