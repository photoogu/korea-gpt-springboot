package com.korit.springboot_study.config;

import com.korit.springboot_study.security.exception.CustomAuthenticationEntryPoint;
import com.korit.springboot_study.security.filter.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 기본 세팅 : localhost:8080/ 에 대한 모든 요청에 대해 login 페이지로 넘어가서 인증을 요구함

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable(); // alert 창으로 인증하는 것 >> 이거 안쓸거에요!(disable)
        http.formLogin().disable(); // 기본 세팅으로 되어있는 로그인창 >> 이거 안쓸거에요!(disable)
        http.addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        // UsernamePasswordAuthenticationFilter.class 이 전에 customAuthenticationFilter 가 먼저 실행! 그 후 UsernamePasswordAuthenticationFilter 로 넘어감!
        http.authorizeRequests()
                .antMatchers(       // 특정 요청주소에는 모든 권한을 줌! 인증이 필요 X
                        "/api/post/**",
                        "/api/user/**"
                )
                .permitAll()
//                .antMatchers(             기능 별로 따로 줘도 됨!
//                        "/api/post/**",
//                        "/api/user/**"
//                )
//                .permitAll()
                .anyRequest()       // 모든 요청에 대해 (antMatchers 로 설정한 요청 URL 을 제외한)
                .authenticated()    // 인증이 필요합니다!
                .and()
                .exceptionHandling() // 인증에 대한 예외가 발생했을 때 내가 처리할게요!
                .authenticationEntryPoint(customAuthenticationEntryPoint); // 이걸로 처리할게요!
    }
}
