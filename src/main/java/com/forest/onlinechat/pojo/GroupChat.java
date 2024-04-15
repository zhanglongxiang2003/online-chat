package com.forest.onlinechat.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class GroupChat {
    private Integer id;
    private String name;
    private String avatar;
    private String introduction;
    private String announcement;
    private Date createdTime;
    private Date updateTime;
    private Integer createdUser;
    private String createdUserName;
}
