package com.forest.onlinechat.mapper;

import com.forest.onlinechat.pojo.FriendApplyTdo;
import com.forest.onlinechat.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("select * from friends_apply where user_id = #{userId} and friend_id = #{id}")
    User getFriendApply(@Param("userId") Integer userId, @Param("id") Integer id);

    @Insert("insert into online_chat.friends_apply(user_id,friend_id,created_user,created_time,update_time,remark) values(#{userId},#{id},#{userId},now(),now(),'friend')")
    void add(@Param("userId") Integer userId, @Param("id") Integer id);

    @Delete("delete from online_chat.friends_apply where user_id = #{id} and friend_id = #{userId}")
    void TurnDown(@Param("userId") Integer userId, @Param("id") Integer id);

    @Select("delete from online_chat.friends_list where user_id = #{userId} and friend_id = #{id} or  user_id = #{id} and friend_id = #{userId}")
    void delete(@Param("userId") Integer userId, @Param("id") Integer id);

    @Select("select a.id,a.user_id,a.friend_id,a.remark,a.created_time,a.update_time,a.created_user,b.email,b.nickname,b.avatar," + "b.signature,b.region,b.gender,b.birthday,b.friend_group_id,b.created_time,b.upde_time,b.created_user  " + "from friends_apply as a left join users as b on b.id = a.user_id where friend_id = #{userId}")
    List<FriendApplyTdo> list(@Param("userId") Integer userId);

    @Insert("insert into friends_list(user_id,friend_id,remark,created_time,update_time,created_user) values(#{id},#{userId},'friend',now(),now(),#{userId})")
    void put(@Param("userId") Integer userId, @Param("id") Integer id);
}
