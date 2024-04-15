package com.forest.onlinechat.mapper;

import com.forest.onlinechat.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from users where email = #{email} and password = #{password}")
    User login(@Param("email") String email,@Param("password") String password);

    @Insert("insert into online_chat.users(nickname,email,password,created_user,created_time,upde_time) values(#{nickName},#{email},#{password},0,now(),now())")
    Integer register(@Param("nickName") String nickName, @Param("email") String email, @Param("password") String password);

    @Select("select users.id from users where email = #{email}")
    User selectByEmail(String email);

    @Select("select * from users where id = #{id}")
    User getUserInfoById(Integer id);

    @Select("select id,email,nickname,avatar,signature,region,gender,birthday from users where id = #{friendId}")
    List<User> selectUserById(Integer friendId);


}
