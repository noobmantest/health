package com.example.health.mapper;

import com.example.health.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("select * from log")
    List<Log> findAll();

    @Insert("insert into log values (#{id},#{user},#{password},#{reason},#{time})")
    int insertLog(Log log);

    @Select("select * from log where user = #{user}")
    List<Log> findByUser(String user);

//    @Insert("insert into sys_user values (#{id}, #{username}, #{email}, #{password}, #{phoneNum})")
//    void addUser(User user);
}
