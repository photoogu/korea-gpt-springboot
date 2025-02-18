package com.korit.springboot_study.security.jwt;

import com.korit.springboot_study.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    private Key key;

    public JwtProvider(@Value("${jwt.secret}") String secret) {     // .yml 파일에 있는 데이터를 변수처럼 사용이 가능함(@Value)
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));       // 공식처럼 사용됨 // secret key 생성 : https://jwtsecret.com/generate
    }

    private Date getExpireDate() {
        return new Date(new Date().getTime() + (1000l * 60 * 60  * 24 * 365));
    }

    public String createAccessToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getUserId())
                .claim("roles", user.getUserRoles().stream().map(userRole -> userRole.getRole().getRoleName()).collect(Collectors.toList()))
                .setExpiration(getExpireDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("Bearer ");
    }

    public Claims parseToken(String token) {
        System.out.println(token);
        Claims claims = null;
        try {
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build();
            claims = parser.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    // Authorization -> AccessToken(Bearer ?????.?????.?????) 에서 앞의 "Bearer " 을 삭제하여 토큰만 가져오게 하기 위함
    public String removeBearer(String bearerToken) {
        String token = null;
        final String BEARER_KEYWORD = "Bearer ";
        if(bearerToken.startsWith(BEARER_KEYWORD)) {
            token = bearerToken.substring(BEARER_KEYWORD.length());
        }
        return token;
    }
}
