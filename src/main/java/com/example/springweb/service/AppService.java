package com.example.springweb.service;

import com.example.springweb.dao.AppDetail;
import com.example.springweb.mapper.AppMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AppService {
    @Resource
    private AppMapper appMapper;

    public List<AppDetail> findAll() {
        List<AppDetail> list = appMapper.findAll();
        return list;
    }

    public void addApp(String uid,// 当前用户
                       String appName,
                       int appKind,
                       int dangerProbability,
                       int dangerSerious,
                       int controlClass) {
        int total_app = appMapper.findAll().size();// 计算总长度


        AppDetail appDetail = new AppDetail();
        appDetail.aid = total_app;// TODO 唯一标识
        appDetail.uid = uid;// 当前用户
        appDetail.appName = appName;
        appDetail.appKind = appKind;
        appDetail.dangerProbability = dangerProbability;
        appDetail.dangerSerious = dangerSerious;
        appDetail.controlClass = controlClass;
        appDetail.start = "current_timestamp()";// TODO 当前时间
        appDetail.duration = 10;
        appDetail.result = -1;

        appMapper.addApp(appDetail);// TODO 提交到数据库
    }

    public List<AppDetail> getByUser(String uid) {
        return null;// TODO 获取一个用户所有app的列表
    }

    public AppDetail getByApp() {
        return null;// TODO 获取特定app的信息
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }
}
