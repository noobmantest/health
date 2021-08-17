package com.example.health.mapper;

import com.example.health.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> findAll();

    // 更新今天打卡状态
    @Update("update user set today=#{today} where id = #{id}")
    int updateUserToday(int id, String today);

    // 更改打卡天数
    @Update("update user set days=#{days} where id = #{id}")
    int updateUserDays(int id, int days);

//    @Insert("insert into sys_user values (#{id}, #{username}, #{email}, #{password}, #{phoneNum})")

    @Insert("insert into user values (#{id}, #{user}, #{password}, #{days}, #{today}, #{email}, #{city_code}, #{address} )")
    int insertUser(User user);

    @Select("select id,user,email,days,today,city_code,address  from user where email=#{email}")
    List<User> findUserByEmail(String email);
}
