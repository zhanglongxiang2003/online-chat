package com.forest.onlinechat.mapper;

import com.forest.onlinechat.pojo.GroupChat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper {
    @Select("select a.id,a.name,a.avatar,a.introduction,a.announcement,a.created_time,a.update_time,a.created_user,b.nickname " +
            "as created_user_name from " +
            "group_chats a left join users b on b.id = a.created_user where a.id = #{groupId}")
    List<GroupChat> selectGroupById(Integer groupId);


}
