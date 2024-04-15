package com.forest.onlinechat.controller;

import com.forest.onlinechat.pojo.FriendResultList;
import com.forest.onlinechat.pojo.GroupChat;
import com.forest.onlinechat.pojo.Result;
import com.forest.onlinechat.pojo.User;
import com.forest.onlinechat.service.Impl.GroupServiceImpl;
import com.forest.onlinechat.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 我的好友
 */
@RestController
@RequestMapping("/online/friend")
public class FriendController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private GroupServiceImpl groupService;


    /**
     * 搜索好友/群聊
     */
    @GetMapping("/search")
    public Result<FriendResultList> search(Integer friendId, Integer groupId) {
        FriendResultList friendResultList = new FriendResultList();
        if (friendId != null) {
            List<User> users = userService.selectUserById(friendId);
            friendResultList.setFriendList(users);
        }
        if (groupId != null) {
            List<GroupChat> groupChats = groupService.selectGroupById(groupId);
            friendResultList.setGroupChatList(groupChats);
        }

        return Result.success(friendResultList);
    }

    /**
     * 添加好友/群聊
     */
//    @PostMapping("/add")
//    public Result<String> add();
}
