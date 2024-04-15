package com.forest.onlinechat.service;

import com.forest.onlinechat.pojo.User;
import com.forest.onlinechat.pojo.UserVo;

import java.util.List;

public interface UserService {
    User login(User user);

    boolean register(UserVo userVo);

    User getUserInfoById(Integer id);

    List<User> selectUserById(Integer friendId);

}
