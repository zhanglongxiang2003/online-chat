package com.forest.onlinechat.inteceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forest.onlinechat.pojo.Result;
import com.forest.onlinechat.utils.JwtUtil;
import com.forest.onlinechat.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 预检放行
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        // Token验证
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            // Token为空或格式不正确，返回401 Unauthorized错误
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "对不起 我们只接待拥有token的用户");
            return false;
        }

        // token验证通过！
        token = token.replace("Bearer ", "");
        try {
            // 解析Token
            Map<String, Object> userInfo = JwtUtil.parse(token);
            // 将信息存储到ThreadLocal里面
            ThreadLocalUtil.set(userInfo);
            return true;
        } catch (Exception e) {
            // Token过期或无效，返回403 SC_FORBIDDEN错误
            Result<String> stringResult = new Result<>(403, "不好意思 您的token已经过期啦", e.getMessage());
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(stringResult));
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求执行完成之后将请求前，存入的ThreadLocal的数据清除掉
        ThreadLocalUtil.remove();
    }
}
