package com.cqust;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTest {

    /**
     * 生成JWT令牌
     */
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "man");
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "Y3F1c3Q=") // 指定加密算法、密钥
                .addClaims(dataMap) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 设置过期时间
                .compact(); // 生成令牌
        System.out.println(jwt);
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJtYW4iLCJleHAiOjE3NDA0OTM3MTZ9.hEwvPhS-aCxDTTR6u-CdgjrwsHliYR__CFn-KkQz1lQ";
        Claims claims = Jwts.parser()
                .setSigningKey("Y3F1c3Q=") // 指定密钥
                .parseClaimsJws(token) // 解析令牌
                .getBody(); // 获取自定义信息
        System.out.println(claims);
    }
}
