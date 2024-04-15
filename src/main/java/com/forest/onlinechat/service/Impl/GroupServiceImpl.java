package com.forest.onlinechat.service.Impl;

import com.forest.onlinechat.mapper.GroupMapper;
import com.forest.onlinechat.pojo.GroupChat;
import com.forest.onlinechat.service.GroupService;
import com.forest.onlinechat.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;


    @Override
    public List<GroupChat> selectGroupById(Integer groupId) {
        return groupMapper.selectGroupById(groupId);
    }

    @Override
    public void add(Integer id) {
        Map<String, Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");
//        groupMapper.add(userId,id);
    }
}
