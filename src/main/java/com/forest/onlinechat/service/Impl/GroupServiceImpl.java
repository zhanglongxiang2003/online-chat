package com.forest.onlinechat.service.Impl;

import com.forest.onlinechat.mapper.GroupMapper;
import com.forest.onlinechat.pojo.GroupChat;
import com.forest.onlinechat.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;


    @Override
    public List<GroupChat> selectGroupById(Integer groupId) {
        return groupMapper.selectGroupById(groupId);
    }



}
