package com.example.health.mapper;

import com.example.health.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<User> findAll();

    // 更新今天打卡状态
    @Update("update user set today=#{today} where id = #{id}")
    int updateUserToday(int id, String today);

    // 更改打卡天数
    @Update("update user set days=#{days} where id = #{id}")
    int updateUserDays(int id, int days);

    // 添加用户
//    @Insert("insert into user(id, user, password, days, today, email, city_code, address, open, inviteNums, addDays) " +
//            "values (#{id}, #{user}, #{password}, #{days}, #{today}, #{email}, " +
//            "#{city_code}, #{address}, #{open}, #{inviteNums}, #{addDays})")
    @Insert("insert into user values (#{id}, #{user}, #{password}, #{days}, #{today}, #{email}, #{city_code}, #{address}, #{open}, #{inviteNums}, #{addDays})")
    int insertUser(User user);

    // 通过邮箱查询用户
    @Select("select id,user,email,days,today,city_code,address  from user where email=#{email}")
    List<User> findUserByEmail(String email);

    // 通过用户名查询用户
    @Select("select * from user where user=#{user}")
    List<User> findUserByUser(String user);

    // 通过用户名和密码查询用户
    @Select("select * from user where user=#{user} and password=#{password}")
    List<User> findUserByUserAndPassword(String user, String password);

    // 更改用户信息
    @Update("update user set user=#{user},password=#{password},email=#{email},city_code=#{city_code},address=#{address},open=#{open} where id=#{id}")
    int updateUserById(String user, String password, String email, String city_code, String address, int open, int id);

    // 增加用户邀请人数
    @Update("update user set inviteNums=#{inviteNums} where user=#{user} ")
    int addInviteNumsByUser(String user, int inviteNums);
}

