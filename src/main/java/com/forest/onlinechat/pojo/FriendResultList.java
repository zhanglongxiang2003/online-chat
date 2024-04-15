package com.forest.onlinechat.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FriendResultList {
    private List<User> friendList = new ArrayList<>();
    private List<GroupChat> GroupChatList = new ArrayList<>();
}
