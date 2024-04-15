package com.forest.onlinechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OnlineChatSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineChatSysApplication.class, args);
        System.out.println("\n\n\n" +
                "     (♥◠‿◠)ﾉﾞ  启动成功！   ლ(´ڡ`ლ)ﾞ          \n" +
                "     ___   ___   ___    ___  ___  _____      \n" +
                "    | __| / _ \\ | _ \\ | __| / __||_   _|   \n" +
                "    | _| | (_) ||   / | _|  \\__ \\  | |     \n" +
                "    |_|   \\___/ |_|_\\ |___| |___/  |_|     \n" +
                "                                             \n" +
                "    林子技术栈 始于2021-2024 http://47.115.230.102\n");
    }

}
