package com.forest.onlinechat.pojo;


import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class UserVo {
    @Valid
    private User user;
    @NotBlank(message = "验证码不能为空！")
    private String code;
    private Map<String,String> token;
}
