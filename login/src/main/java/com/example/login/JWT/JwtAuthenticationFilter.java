package com.example.login.JWT;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // JWT 服務
    private final JwtService jwtService;
    // 用戶詳情服務
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // 從請求頭中獲取 Authorization 字段
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 如果 Authorization 頭不存在或不是 Bearer token，直接放行
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 提取 JWT token
        jwt = authHeader.substring(7);
        // 從 JWT 中提取用戶郵箱
        userEmail = jwtService.extractUsername(jwt);

        // 如果用戶郵箱存在且當前沒有認證信息
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 加載用戶詳情
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // 驗證 token 是否有效
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // 創建認證 token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                // 設置認證詳情
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // 設置認證信息
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            if (jwtService.isTokenBlacklisted(jwt)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        // 繼續過濾鏈
        filterChain.doFilter(request, response);
    }
}
