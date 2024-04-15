package com.forest.onlinechat.pojo;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
public class User {
    private Integer id;
    @NotBlank(message = "邮箱不能为空！")
    private String email;
    @NotBlank(message = "密码不能为空！")
    private String password;
    private String nickname;
    private String avatar;
    private String signature;
    private String region;
    private String gender;
    private Date birthday;
    private Integer friendGroupId;
    private Boolean noSignIn;
}