package com.its.mergeplan_v1.config;

import com.its.mergeplan_v1.config.jwt.JwtAuthenticationFilter;
import com.its.mergeplan_v1.config.jwt.JwtAuthorizationFilter;
import com.its.mergeplan_v1.filter.MyFilter3;
import com.its.mergeplan_v1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsConfig corsConfig;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 시큐리티 필터가 내가 만든 필터보다 먼저 동작한다
        // 만들어준 필터를 시큐리티 필터보다 먼저 동작하게 만들고 싶으면 http.addFilterBefore(걸어주고 싶은 필터, 특정 필터전에)로 등록해준다!!
//        http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http.addFilter(corsConfig.corsFilter());
        // 모든 요청은 이 필터를 타게된다(cross-origin 요청이 와도 모두 허용이됨).
        // @CrossOrigin:인증이 없을때 걸어준다. 인증이 필요한 경우라면 시큐리티 필터를 걸어줘야함
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 세션 사용X
                .and()
               .formLogin().disable()  // jwt서버 사용. 폼X
               .httpBasic().disable()

               .addFilter(new JwtAuthenticationFilter(authenticationManager()))  // AuthenticationManager 던져줘야함
               .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
               .authorizeRequests()
               .antMatchers("/api/v1/user/**")
                   .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
               .antMatchers("/api/v1/admin/**")
                   .access("hasRole('ROLE_ADMIN')")
               .anyRequest().permitAll();  // 다른 요청은 권한 없이 접근 가능
    }
}
