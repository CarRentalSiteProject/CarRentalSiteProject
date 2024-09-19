package com.example.CarRentTest.JWT;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.HashSet;
import java.util.Set;

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
    public String generateToken(UserDetails userDetails, String username, String emailOrphone) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("emailOrphone", emailOrphone);
        
        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    // 從JWT中提取用戶名
    public String extractUsername(String token) {
        return (String) extractAllClaims(token).get("username");
    }

    public String extractEmailOrPhone(String token) {
        return (String) extractAllClaims(token).get("emailOrphone");
    }

    // 從JWT中提取特定的聲明
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 驗證JWT的有效性
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && !isTokenBlacklisted(token);

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

    private Set<String> blacklistedTokens = new HashSet<>();

    public void invalidateToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}