package com.example.login.JWT;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

// JWT服務類,用於處理JWT相關操作
@Service
public class JwtService {
    // Token有效期限 (設定1小時過期)
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 單位ms

    // BASE64編碼的密鑰，用於簽名和驗證JWT
    private static final String SECRET_KEY = "546A55A71347A254462D4A614E645267556B58703273357638792F423F452848";

    // 生成JWT令牌
    public String generateToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails
    ) {
        return Jwts
            .builder()
            .setClaims(extraClaims)  // 設置額外的聲明
            .setSubject(userDetails.getUsername()) // 以Username做為Subject
            .setIssuedAt(new Date(System.currentTimeMillis()))  // 設置令牌簽發時間
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 設置令牌過期時間
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // 使用HS256算法和密鑰進行簽名
            .compact();  // 生成最終的JWT字符串
    }

    // 從JWT中提取用戶名
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 從JWT中提取特定的聲明
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 驗證JWT的有效性
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // 檢查JWT是否過期
    private boolean isTokenExpired(String token) {
        final Date expirationDate = extractExpiration(token);
        return expirationDate != null && expirationDate.before(new Date());
    }

    // 從JWT中提取過期時間
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 從JWT中提取所有聲明
    private Claims extractAllClaims(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())  // 設置用於驗證簽名的密鑰
            .build()
            .parseClaimsJws(token)  // 解析JWT
            .getBody();  // 獲取JWT的主體部分(即所有聲明)
    }

    // 獲取用於簽名的密鑰
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);  // 解碼BASE64編碼的密鑰
        return Keys.hmacShaKeyFor(keyBytes);  // 創建HMAC-SHA密鑰
    }
}