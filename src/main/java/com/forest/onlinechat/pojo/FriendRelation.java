package com.forest.onlinechat.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendRelation {
    private Integer id;
    private Integer userId;
    private Integer friendId;
    private String remark;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
    private int createdUser;
}
