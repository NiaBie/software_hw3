package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Controller
public class HelloController {// 控制页面跳转,连接数据库
    @Autowired
    HelloService helloService;
    List<HelloUser> allUsers;// 记录所有用户
    String curUser = null;// 当前用户
    int userKind = 1;// 1: 企业, 2: 专家 TODO 不需要实现
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    // 访问主页
    @RequestMapping("/")
    public String home() {
        if (curUser == null) {// 首次访问
            ;// TODO
        } else {
            infoLog("登出");
            curUser = null;
        }
        return "index";
    }

    // 从主页跳转到任何页面
    @RequestMapping("/{page}")
    public String changePage(@PathVariable("page") String page, Model model) {
        infoLog("request1: " + page);
        if (curUser != null) {// 强制跳转
            model.addAttribute("user_name", curUser);// TODO 显示用户名

            if (userKind == 1) {// 是企业
                return "redirect:/enterprise/home";
            } else if (userKind == 0) {// 专家 TODO
                ;// 不实现
            }
        } else {// 没有登录
            model.addAttribute("user_name", "未登录");
        }

        return page;
    }

    @RequestMapping("/enterprise/{page}")
    public String login(GetLogin getLogin, @PathVariable("page") String page, Model model) {
        infoLog("request2: " + page);// TODO 对于不同request,返回不同文件
        allUsers = helloService.getUserList();// 更新用户列表
        model.addAttribute("user_name", "未登录");// TODO 显示用户名

        if (curUser != null) {// TODO 检验登录状态
             infoLog("user: " + curUser);
             model.addAttribute("user_name", curUser);// TODO 保持登录状态
             return "/enterprise/" + page;
        } else {
            String account = getLogin.account;
            String password = getLogin.password;
            infoLog(allUsers + "");
            infoLog("account: " + account);
            infoLog("password: " + password);

            if (account == null || password == null) {
                infoLog("非法访问");
//                curUser = null;// TODO 清空登录信息
                return "redirect:/enterprise";// 不能够直接访问个人主页
            }

            for (int i = 0; i < allUsers.size(); i ++) {
                HelloUser tmpUser = allUsers.get(i);
                infoLog(tmpUser.getId() + ": " + tmpUser.getPassword());
                if (allUsers.get(i).getId().equals(account)) {// 存在用户
                    if (tmpUser.getPassword().equals(password)) {// 密码正确
                        curUser = tmpUser.getName();
                        model.addAttribute("user_name", curUser);

                        infoLog("成功登陆");
                        return "/enterprise/home";// 登陆成功
                    } else {
                        curUser = null;// TODO 清空登录信息

                        infoLog("密码错误");
                        return "redirect:/enterprise?error=1";// TODO 密码错误
                    }
                }
            }
            curUser = null;// TODO 清空登录信息
            infoLog("账号不存在");
            return "redirect:/enterprise?error=2";// TODO 账号不存在
        }
    }

    @RequestMapping("/src/{page}")
    public String src(@PathVariable("page") String page) {// 非网页文件
        infoLog("request3: " + page);
        return "/src/" + page;
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }

    public class GetLogin {
        public String account;
        public String password;

        public void setAccount(String account) {
            this.account = account;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAccount() {
            return this.account;
        }

        public String getPassword() {
            return this.password;
        }
    }
}
