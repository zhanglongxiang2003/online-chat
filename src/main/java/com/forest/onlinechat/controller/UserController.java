package com.forest.onlinechat.controller;

import com.forest.onlinechat.pojo.Result;
import com.forest.onlinechat.pojo.User;
import com.forest.onlinechat.pojo.UserVo;
import com.forest.onlinechat.service.Impl.UserServiceImpl;
import com.forest.onlinechat.utils.CommonUtil;
import com.forest.onlinechat.utils.JwtUtil;
import com.forest.onlinechat.utils.Md5Util;
import com.forest.onlinechat.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 个人用户
 */
@RestController
@RequestMapping("/online/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Resource
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    String fromMail;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private final String regKey = "web_chart:periodOfValidityRegKey";


    /**
     * 登录账号
     */
    @PostMapping("/login")
    public Result login(@RequestBody User form) {
        form.setPassword(Md5Util.getMD5String(form.getPassword()));
        User user = userService.login(form);
        if (user != null) {
            //accessToken
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("email", user.getEmail());
            claims.put("password", user.getPassword());
            claims.put("isRefreshToken", false);
            long assessExpire = 88888 * 10L * 60 * 1000;
            String assessToken = JwtUtil.get(claims, assessExpire);

            //refreshToken
            claims.put("isRefreshToken", true);
            long refreshExpire = form.getNoSignIn() ? 30L * 24 * 60 * 60 * 1000 : 5L * 60 * 60 * 1000;
            String refreshToken = JwtUtil.get(claims, refreshExpire);

            Map<String, String> token = new HashMap<>();
            token.put("assessToken", assessToken);
            token.put("refreshToken", refreshToken);
            UserVo userVo = new UserVo();
            userVo.setToken(token);

            return Result.success(userVo);
        } else {
            return Result.error("登录失败");
        }
    }


    /**
     * 注册-验证码
     */
    @GetMapping("/code")
    public Result<String> code(String email) throws MessagingException, UnsupportedEncodingException {
        String SendCodeLimit = "web_chart:registerCodeFrequencyLimitation" + email;
        String periodOfValidityRegKey = this.regKey + email;
        if (redisTemplate.opsForValue().get(SendCodeLimit) != null) {
            return Result.error("频率太快！请一分钟后再次尝试");
        }
        String ranCode = CommonUtil.generateRandomCode(6);
        //验证码频率限制

        redisTemplate.opsForValue().set(SendCodeLimit, ranCode, 60, TimeUnit.SECONDS);
        //验证码有效期 10分钟
        redisTemplate.opsForValue().set(periodOfValidityRegKey, ranCode, 10 * 60, TimeUnit.SECONDS);
        // 创建一个邮件消息
        MimeMessage message = javaMailSender.createMimeMessage();
        // 创建 MimeMessageHelper，指定 boolean multipart 参数为 true
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        // 发件人邮箱和名称
        helper.setFrom(fromMail, "林子技术栈");
        // 收件人邮箱
        helper.setTo(email);
        // 邮件标题
        helper.setSubject("WebChat 验证码");
        // 邮件正文，第二个参数表示是否是HTML正文
        String contentText = "您好，您正在注册WebChat账号下方是您的验证码。温馨提示：有效期10分钟。";
        helper.setText("    <div class=\"container\"\n" + "        style=\"width: 100%; background-color: #F3F3F3; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); height: 800px;\">\n" + "        <style>\n" + "            * {\n" + "                background-color: #F3F3F3 !important;\n" + "            }\n" + "        </style>\n" + "        <div style=\"padding: 0 15px;\">\n" + "            <h1 style=\"font-size: 40px; padding: 6px 0; font-weight: 700; text-align: center; color: #333;\">WebChat 验证码\n" + "            </h1>\n" + "            <p style=\"font-size: 20px;height: 20px; color: #333;\">发件人: 林子技术栈</p>\n" + "            <p style=\"    font-size: 20px;height: 20px;color: #333;\">内容: " + contentText + "</p>\n" + "            <div class=\"code\"\n" + "                style=\"text-align: center; font-size: 24px; font-weight: bold; margin-top: 20px; margin-bottom: 40px;\">\n" + "                <span class=\"text-code-number\" style=\"font-size: 15vw; padding: 10vw 0; display: block;\">\n" + "                    " + ranCode + "\n" + "                </span>\n" + "            </div>\n" + "            <!-- <a class=\"button forest-copy-code-button\" style=\"\n" + "             display: block;\n" + "                width: 35vw;\n" + "                margin: 0 auto;\n" + "                padding: 3vw 19vw;\n" + "                text-align: center;\n" + "                background-color: #3D6FFF !important;\n" + "                font-size: 20px;\n" + "                letter-spacing: .8vw;\n" + "                color: #fff;\n" + "                text-decoration: none;\n" + "                margin-bottom: 20vw;\n" + "                border-radius: 2vw;\n" + "                \">复制验证码</a> -->\n" + "                                <!-- onclick=\"() => {\n" + "                                    const textarea = document.createElement('textarea');\n" + "                                    textarea.value = \"897542\";\n" + "                                    document.body.appendChild(textarea);\n" + "                                    textarea.select();\n" + "                                    document.execCommand('copy');\n" + "                                    document.body.removeChild(textarea);\n" + "                                }\" -->\n" + "            <a style=\"font-size: 20px;\n" + "            color: #3D6FFF;\" href=\"http://172.26.1.213:5173/login\">立即体验 WebChat！</a>\n" + "\n" + "        </div>\n" + "        <img src=\"https://ncstatic.clewm.net/rsrc/2024/0323/19/1cb0d4e872261f8075099cf9f6ef47f3.png?x-oss-process=image/format,gif/auto-orient,1\"\n" + "            alt=\"林子技术栈-LOGO\" style=\"width: 100%;\">\n" + "    </div>", true);

//        // 添加一个附件，指定附件名称、文件的 Inputstream 流 以及 Content-Type
//        helper.addAttachment("spring-framework 中文文档.pdf",
//                () -> Files.newInputStream(Paths.get("C:\\Users\\KevinBlandy\\Desktop\\spring-framework 中文文档.pdf")),
//                "application/octet-stream");
        // 发送
        javaMailSender.send(message);

        return Result.success("验证码发送成功");
    }

    /**
     * 注册账号
     */
    @PostMapping("/register")
    public Result<String> code(@RequestBody Map<String, Object> form) {

        @Valid UserVo userVo = new UserVo();
        userVo.setCode((String) form.get("code"));
        User user = new User();
        user.setEmail((String) form.get("email"));
        user.setPassword((String) form.get("password"));
        userVo.setUser(user);

        String RegCodeKey = regKey + userVo.getUser().getEmail();
        if (!Objects.equals(redisTemplate.opsForValue().get(RegCodeKey), userVo.getCode())) {
            return Result.error("请输入正确的验证码");
        }
        redisTemplate.delete(RegCodeKey);
        userVo.getUser().setPassword(Md5Util.getMD5String(userVo.getUser().getPassword()));
        if (!userService.register(userVo)) {
            return Result.error("注册失败！该账号已被注册");
        }
        return Result.success("注册成功");
    }

    /**
     * 获取个人用户信息
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> userInfo = ThreadLocalUtil.get();
        Integer id = (Integer) userInfo.get("id");

        User user = userService.getUserInfoById(id);
        return Result.success(user);
    }

    /**
     * 获取新token
     */
    @PatchMapping("/refreshToken")
    public Result<String> refreshToken() {
        Map<String, Object> userInfo = ThreadLocalUtil.get();
        Boolean isRefreshToken = (Boolean) userInfo.get("isRefreshToken");
        if (isRefreshToken) {
            Integer id = (Integer) userInfo.get("id");
            User user = userService.getUserInfoById(id);
            if (user != null) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", user.getId());
                claims.put("email", user.getEmail());
                claims.put("password", user.getPassword());
                claims.put("isRefreshToken", false);
                long assessExpire = 10 * 60 * 1000;
                String assessToken = JwtUtil.get(claims, assessExpire);
                return Result.success(assessToken);
            }
            return Result.error("token过期了");
        }
        return Result.error("token过期了");
    }
}