package com.example.springweb.controller;

import com.example.springweb.dao.AppDetail;
import com.example.springweb.dao.UserDetail;
import com.example.springweb.service.AppService;
import com.example.springweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class MainController {// 控制页面跳转,连接数据库
    @Autowired
    UserService userService;
    @Autowired
    AppService appService;

    List<UserDetail> allUsers;// 记录所有用户
    String curUser = null;// 当前用户
    String curUid = null;// 当前用户的id
    int userKind = 1;// 1: 企业, 2: 专家 TODO 不需要实现
    public final static Logger logger = LoggerFactory.getLogger(MainController.class);

    // 访问主页
    @RequestMapping("/")
    public String home() {
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
        } else {// TODO 没有登录
            model.addAttribute("user_name", "未登录");
        }

        return page;
    }

    @RequestMapping("/enterprise/{page}")
    public String login(Model model, @PathVariable("page") String page, String account, String password) {// TODO 这里变量名必须和html的相同
        infoLog("request2: " + page);// TODO 对于不同request,返回不同文件
        allUsers = userService.getUserList();// 更新用户列表
        model.addAttribute("user_name", "未登录");// TODO 显示用户名

        if (curUser != null) {// TODO 检验登录状态
             infoLog("user name: " + curUser);
             model.addAttribute("user_name", curUser);// TODO 保持登录状态
             model.addAttribute("all_apps", appService.getByUser(curUser));// TODO 返回用户所有app
             return "/enterprise/" + page;
        } else {

            // TODO 登录或注册页面
            if (page.equals("sign_in") || page.equals("sign_up")) {
                return "/enterprise/" + page;
            }

            return "redirect:/enterprise/sign_in";// TODO 全部重定向
        }
    }

    @RequestMapping("/src/{filename}.{suffix}")
    public String src(@PathVariable("filename") String filename,
                      @PathVariable("suffix") String suffix,
                      HttpServletResponse response) {// TODO 传输文件
        infoLog("request3: " + filename + "." + suffix);
        return "/src/" + filename + "." + suffix;
    }

    @RequestMapping("/enterprise/app/{page}")
    public String app(@PathVariable("page") String page,
                      @RequestParam(value = "aid", required = false) String aid,// TODO 查看特定app
                      String appName,
                      String appKind,
                      String dangerProbability,
                      String dangerSerious,
                      String controlClass,
                      Model model) {// App详情
        if (curUser == null) {// TODO 非法访问app详情
            infoLog("非法访问app");
            return "redirect:/enterprise/sign_in";
        }

        model.addAttribute("user_name", curUser);

        if (page.equals("added")) {// TODO 提交成功的页面
            infoLog("curUser: " + curUser);
            infoLog("curUid: " + curUid);
            infoLog("appName: " + appName);
            infoLog("appKind: " + appKind);
            infoLog("dangerProbability: " + dangerProbability);
            infoLog("dangerSerious: " + dangerSerious);
            infoLog("controlClass: " + controlClass);
            infoLog((userService == null) + ", " + (appService == null));

            appService.addApp(
                    curUid,
                    appName,
                    appKind,
                    dangerProbability,
                    dangerSerious,
                    controlClass
            );
        } else if (page.equals("doing")) {// TODO 特定app审核完成页面
            infoLog("查看审核: " + aid);
            model.addAttribute("", appService.getByApp(aid));
        } else if (page.equals("finish")) {// TODO 特定app正在审核页面
            infoLog("查看审核: " + aid);
            model.addAttribute("", appService.getByApp(aid));
        }

        return "/enterprise/app/" + page;
    }

    @RequestMapping("/error/{code}")
    public String error(@PathVariable("code") String code) {
        infoLog("error code: " + code);
        return "redirect:/error";// TODO 返回错误页面
    }

    @RequestMapping("/user/{kind}")// 查询当前用户所有app
    @ResponseBody// TODO
    public List<AppDetail> getList(@PathVariable("kind") String kind) {
        infoLog("user kind: " + kind);
        return appService.getByUser(curUid);
    }

    @RequestMapping("/user/app/{aid}")// 查询aid对应的app
    @ResponseBody// TODO
    public AppDetail getOne(@PathVariable("aid") String aid) {
        infoLog("search aid: " + aid);
        return appService.getByApp(aid);
    }

    @RequestMapping("/action/logout")// 登出
    public void logOut(Model model) {
        infoLog("登出");
        curUser = null;
        curUid = null;
        model.addAttribute("user_name", "未登录");
    }

    @RequestMapping("/action/login")// TODO 登陆成功
    public void logIn(Model model) {
        infoLog("登陆成功");
        curUser = null;
        curUid = null;
        model.addAttribute("user_name", curUser);
    }

    @RequestMapping("/action/sign_in")
    public UserDetail signIn(HttpServletRequest request) {// TODO 登录,返回账号信息
        String uid = request.getParameter("uid");
        infoLog("登录账号: " + uid);
        return userService.getOne(uid);
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }

}
