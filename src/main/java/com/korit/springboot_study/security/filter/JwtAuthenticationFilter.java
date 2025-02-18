package com.korit.springboot_study.security.filter;

import com.korit.springboot_study.repository.UserRepository;
import com.korit.springboot_study.security.jwt.JwtProvider;
import com.korit.springboot_study.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // Bearer Token(JWT)
        String authorization = request.getHeader("Authorization");

        if (jwtProvider.validateToken(authorization)) {
            setJwtAuthentication(authorization);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setJwtAuthentication(String bearerToken) {
        String accessToken = jwtProvider.removeBearer(bearerToken);
        Claims claims = jwtProvider.parseToken(accessToken);
        if(claims == null) { // 유효성 검사에 실패함 >> ExceptionHandler 로 넘어감
//          throw new JwtException("Invalid JWT token"); // 유효성 검사에 대한 JWT 오류 -> Exception Handler 로 예외를 처리함 =>> accessToken 에 null, undefined, 빈 값이 들어있을 경우 응답이 안됨(500 Error)
            return; // 위의 오류로 인해 Exception Handler 로 예외를 처리하지 않고, JwtAuthenticationFilter 에서 바로 처리하게끔!
        }
        int userId = Integer.parseInt(claims.get("userId").toString()); // if 로 예외가 터지지 않았을 경우 >> DB 로 넘겨서 유효성 검사 진행
        userRepository.findById(userId).ifPresent(user -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            PrincipalUser principalUser = new PrincipalUser(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalUser, principalUser.getPassword(), principalUser.getAuthorities());
            securityContext.setAuthentication(authentication);
        });
    }
}
