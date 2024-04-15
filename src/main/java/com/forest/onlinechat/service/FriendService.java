package com.forest.onlinechat.service;

import com.forest.onlinechat.pojo.FriendApplyTdo;

import java.util.List;

public interface FriendService {
    void add(Integer id);

    void TurnDown(Integer id);

    void delete(Integer id);

    List<FriendApplyTdo> list();

    void put(Integer id);
}
