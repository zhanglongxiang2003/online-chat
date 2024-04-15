package com.forest.onlinechat.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "FOREST_ENCRYPT";

    // 接收业务数据,生成token并返回
    public static String get(Map<String, Object> claims, long expiration) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC256(KEY));
    }

    public static String get(long expiration) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC256(KEY));
    }

    // 接收token,验证token,并返回业务数据
    public static Map<String, Object> parse(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}