package com.tiv.card.audit.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * jwt工具类
 */
public class JwtUtil {

    /**
     * 签名密钥
     */
    private static final String SECRET = "qILK?VpAsd93df03U!IS2Fka5#ldfS%FnJKF!dfaPr$Jrj";

    /**
     * 签名算法
     */
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    /**
     * token前缀
     */
    private static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 加密
     *
     * @param map
     * @param maxAge
     * @return
     */
    public static String sign(Map<String, Object> map, long maxAge) {
        return sign(map, null, null, maxAge);
    }

    /**
     * 加密
     *
     * @param map
     * @param issuer
     * @param subject
     * @param maxAge
     * @return
     */
    public static String sign(Map<String, Object> map, String issuer, String subject, long maxAge) {
        Date now = new Date(System.currentTimeMillis());
        String jwt = Jwts.builder()
                // 设置自定义数据
                .setClaims(map)
                // 设置签发时间
                .setIssuedAt(now)
                // 设置过期时间
                .setExpiration(new Date(now.getTime() + maxAge))
                // 设置签发者
                .setIssuer(issuer)
                // 设置面向用户
                .setSubject(subject)
                .signWith(signatureAlgorithm, SECRET)
                .compact();
        return TOKEN_PREFIX + jwt;
    }

    /**
     * 解密
     *
     * @param token
     * @return
     */
    public static Map<String, Object> unSign(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
        } catch (Exception e) {
            throw new IllegalStateException("Token验证失败:" + e.getMessage());
        }
    }

}




