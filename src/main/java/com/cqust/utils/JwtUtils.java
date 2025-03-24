package com.cqust.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET_KEY = "Y3F1c3Q=";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    /**
     * 生成JWT令牌
     *
     * @param claims 自定义信息
     * @return 生成的JWT令牌
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 指定加密算法、密钥
                .addClaims(claims) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .compact(); // 生成令牌
    }

    /**
     * 解析JWT令牌
     *
     * @param token JWT令牌
     * @return 解析后的自定义信息
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // 指定密钥
                .parseClaimsJws(token) // 解析令牌
                .getBody(); // 获取自定义信息
    }

    public static void main(String[] args) {
        // 测试生成JWT令牌
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "man");
        String jwt = generateToken(dataMap);
        System.out.println("Generated JWT: " + jwt);

        // 测试解析JWT令牌
        Claims claims = parseToken(jwt);
        System.out.println("Parsed Claims: " + claims);
    }
}
