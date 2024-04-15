package com.forest.onlinechat.controller;

import com.forest.onlinechat.pojo.Result;
import com.forest.onlinechat.service.Impl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/online/group")
public class GroupController {
    @Autowired
    private GroupServiceImpl groupService;


    /**
     *添加群聊
     */
    @PostMapping("/add")
    public Result<String> add(Integer id) {
        groupService.add(id);
        return Result.success("群聊申请已发送！");
    }

}
