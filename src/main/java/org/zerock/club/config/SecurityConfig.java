package org.zerock.club.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 시큐리티 관련 설정을 하기 위한 상속(추가 설정은 override를 통해서 조정하게 됨)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        // bcrypt라는 해시함수를 이용해서 패스워드를 암호화 함
        return new BCryptPasswordEncoder();
    }

    @Override
    // 설정을 통해서 권한을 부여함
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/sample/all").permitAll()
            .antMatchers("/sample/member").hasRole("USER");
        // 인가, 인증 문제시 로그인 화면으로 전환
        http.formLogin();
    }
}
