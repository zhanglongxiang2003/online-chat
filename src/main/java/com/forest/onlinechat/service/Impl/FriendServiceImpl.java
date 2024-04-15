package com.forest.onlinechat.service.Impl;

import com.forest.onlinechat.mapper.FriendMapper;
import com.forest.onlinechat.pojo.FriendApplyTdo;
import com.forest.onlinechat.service.FriendService;
import com.forest.onlinechat.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public void add(Integer id) {
        Map<String, Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");
        if (friendMapper.getFriendApply(userId, id) == null) {
            friendMapper.add(userId, id);
        }
    }

    @Override
    public void TurnDown(Integer id) {
        Map<String, Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");
        friendMapper.TurnDown(userId, id);
    }

    @Override
    public void delete(Integer id) {
        Map<String, Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");
        friendMapper.delete(userId, id);
    }

    @Override
    public List<FriendApplyTdo> list() {
        Map<String, Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");
        return friendMapper.list(userId);
    }

    @Override
    public void put(Integer id) {
        Map<String, Object> userInfo = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfo.get("id");
        friendMapper.TurnDown(userId,id);
        friendMapper.put(userId,id);
    }
}
