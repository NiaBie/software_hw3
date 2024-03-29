
package com.example.springweb.mapper;

        import com.example.springweb.dao.AppDetail;
        import org.apache.ibatis.annotations.*;

        import java.util.List;

@Mapper
public interface AppMapper {
    @Select("select *, TIMESTAMPDIFF(MINUTE, start, current_timestamp) - duration as remain from app;")
    @Results({
            @Result(property = "aid", column = "aid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "appName", column = "appName"),
            @Result(property = "appKind", column = "appKind"),
            @Result(property = "dangerProbability", column = "dangerProbability"),
            @Result(property = "dangerSerious", column = "dangerSerious"),
            @Result(property = "controlClass", column = "controlClass"),
            @Result(property = "start", column = "start"),
            @Result(property = "duration", column = "duration"),
            @Result(property = "remain", column = "remain"),
            @Result(property = "result", column = "result")
    })
    List<AppDetail> findAll();// 查看所有

    @Insert("insert into app (aid, uid, appName, appKind, dangerProbability, dangerSerious, controlClass, start, duration, result)\n" +
            "  values\n" +
            "  (#{aid},\n" +
            "   #{uid},\n" +
            "   #{appName},\n" +
            "   #{appKind},\n" +
            "   #{dangerProbability},\n" +
            "   #{dangerSerious},\n" +
            "   #{controlClass},\n" +
            "   current_timestamp(),\n" +
            "   #{duration},\n" +
            "   #{result});")// TODO
    void addApp(AppDetail appDetail);// 提交一个新的申请


    @Select("select *, TIMESTAMPDIFF(MINUTE, start, current_timestamp) - duration as remain from app where uid = #{uid}")
    @Results({
            @Result(property = "aid", column = "aid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "appName", column = "appName"),
            @Result(property = "appKind", column = "appKind"),
            @Result(property = "dangerProbability", column = "dangerProbability"),
            @Result(property = "dangerSerious", column = "dangerSerious"),
            @Result(property = "controlClass", column = "controlClass"),
            @Result(property = "start", column = "start"),
            @Result(property = "duration", column = "duration"),
            @Result(property = "remain", column = "remain"),
            @Result(property = "result", column = "result")
    })
    List<AppDetail> getByUser(String uid);// 获取一个用户的所有App列表

    @Select("select *, TIMESTAMPDIFF(MINUTE, start, current_timestamp) - duration as remain from app where aid = #{aid}")
    @Results({
            @Result(property = "aid", column = "aid"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "appName", column = "appName"),
            @Result(property = "appKind", column = "appKind"),
            @Result(property = "dangerProbability", column = "dangerProbability"),
            @Result(property = "dangerSerious", column = "dangerSerious"),
            @Result(property = "controlClass", column = "controlClass"),
            @Result(property = "start", column = "start"),
            @Result(property = "duration", column = "duration"),
            @Result(property = "remain", column = "remain"),
            @Result(property = "result", column = "result")
    })
    AppDetail getByApp(String aid);// 获取特定的App
}
