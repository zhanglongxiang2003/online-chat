package com.forest.onlinechat.controller;

import com.forest.onlinechat.pojo.*;
import com.forest.onlinechat.service.Impl.FriendServiceImpl;
import com.forest.onlinechat.service.Impl.GroupServiceImpl;
import com.forest.onlinechat.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private FriendServiceImpl friendService;

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
     * 添加好友
     */
    @PostMapping("/add")
    public Result<String> add(Integer id) {
        friendService.add(id);
        return Result.success("好友申请已发送！");
    }

    /**
     * 拒绝添加
     */
    @PatchMapping()
    public Result<String> TurnDown(Integer id) {
        friendService.TurnDown(id);
        return Result.success("成功拒绝对方好友申请！");
    }


    /**
     * 删除好友
     */
    @DeleteMapping()
    public Result<String> delete(Integer id) {
        friendService.delete(id);
        return Result.success("成功删除对方好友！");
    }


    /**
     * 获取好友申请列表
     */
    @GetMapping("/list")
    public Result<List<FriendApplyTdo>> list() {
        return Result.success(friendService.list());
    }



    /**
     * 同意好友申请
     */
    @PostMapping()
    public Result<String> put(Integer id) {
        friendService.put(id);
        return Result.success("成功添加为好友！");
    }
}
