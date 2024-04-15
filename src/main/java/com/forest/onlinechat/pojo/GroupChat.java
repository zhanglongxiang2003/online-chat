package com.forest.onlinechat.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupChat {
    private Integer id;
    private String name;
    private String avatar;
    private String introduction;
    private String announcement;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
    private Integer createdUser;
    private String createdUserName;
}
