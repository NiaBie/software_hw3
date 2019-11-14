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
                       String appKind,
                       String dangerProbability,
                       String dangerSerious,
                       String controlClass) {
        int total_app = appMapper.findAll().size();// 计算总长度


        AppDetail appDetail = new AppDetail();
        appDetail.aid = total_app;// TODO 唯一标识
        appDetail.uid = uid;// 当前用户
        appDetail.appName = appName;
        switch (appKind) {
            case "基础共性工业App":
                appDetail.appKind = 0;
                break;
            case "行业通用工业App":
                appDetail.appKind = 1;
                break;
            case "企业专用工业App":
                appDetail.appKind = 2;
                break;
            default:// TODO 报错
        }
        switch (dangerProbability) {
            case "灾难的":
                appDetail.dangerProbability = 0;
                break;
            case "严重的":
                appDetail.dangerProbability = 1;
                break;
            case "轻度的":
                appDetail.dangerProbability = 2;
                break;
            case "轻微的":
                appDetail.dangerProbability = 3;
                break;
        }
        switch (dangerSerious) {
            case "经常":
                appDetail.dangerSerious = 0;
                break;
            case "很可能":
                appDetail.dangerSerious = 1;
                break;
            case "偶然":
                appDetail.dangerSerious = 2;
                break;
            case "很少":
                appDetail.dangerSerious = 3;
                break;
            case "极少":
                appDetail.dangerSerious = 4;
                break;
        }
        appDetail.controlClass = Integer.parseInt(controlClass) - 1;// 必须要 - 1, 因为选项的最小值为1
        appDetail.start = null;// TODO 当前时间
        appDetail.duration = 10;
        appDetail.result = -1;

        appMapper.addApp(appDetail);// TODO 提交到数据库
    }

    public List<AppDetail> getByUser(String uid) {
        infoLog("getByUser: " + uid);
        return appMapper.getByUser(uid);// TODO 获取一个用户所有app的列表
    }

    public AppDetail getByApp(String aid) {
        return appMapper.getByApp(aid);// TODO 获取特定app的信息
    }

    public void infoLog(String log) {
        System.out.println("\n" + log + "\n");
    }
}
