package com.forest.onlinechat.service.Impl;

import com.forest.onlinechat.mapper.UserMapper;
import com.forest.onlinechat.pojo.User;
import com.forest.onlinechat.pojo.UserVo;
import com.forest.onlinechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User form) {
        String email = form.getEmail();
        String password = form.getPassword();
        return userMapper.login(email, password);
    }

    @Override
    public boolean register(UserVo userVo) {
        String email = userVo.getUser().getEmail();
        String password = userVo.getUser().getPassword();
        String nickName = userVo.getUser().getNickname();

        if (userMapper.selectByEmail(email) == null && userMapper.register(nickName, email, password) != 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUserInfoById(Integer id) {
        User userInfo = userMapper.getUserInfoById(id);
        userInfo.setPassword("********");
        return userInfo;
    }

    @Override
    public List<User> selectUserById(Integer friendId) {
        return userMapper.selectUserById(friendId);
    }


}
