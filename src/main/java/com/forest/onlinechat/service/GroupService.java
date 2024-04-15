package com.forest.onlinechat.service;

import com.forest.onlinechat.pojo.GroupChat;

import java.util.List;

public interface GroupService {
List<GroupChat> selectGroupById(Integer groupId);


    void add(Integer id);
}
