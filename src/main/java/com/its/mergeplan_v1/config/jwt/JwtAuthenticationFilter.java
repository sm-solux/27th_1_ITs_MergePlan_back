package com.its.mergeplan_v1.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// 스프링 시큐리에서 UsernamePasswordAuthenticationFilter
// /login으로 요청해서 username과 password를 post로 전송하면 해당 필터가 동작한다(SecurityConfig에서 formlogin을 disable해둬서 현재 작동하지는X)
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login요청을 하면, 로그인 시도를 위해 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도중");
        // username과 password를 받아서 정상인지 로그인 시도를 해본다
        try {
//            BufferedReader br = request.getReader();
//
//            String input = null;
//            while((input = br.readLine()) != null){
//                System.out.println(input);

            // json 데이터 파싱하기
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);

            // 토큰 만들기
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getNickname(), user.getPassword());

            // PrincipalDetailsService의 loadUserByUsername()함수가 실행됨. 정상이면 authentication이 리턴됨 (username의 정보만 가져가고, password정보는 스프링이 알아서 처리해줌)
            // db에 있는 username과 password가 일치한다는 뜻!!
            // authenticationManager에 토큰을 넣어 던지고, 인증을 받는다. authentication에 내가 로그인한 정보가 담긴다!
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);


            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료, 유저이름: " + principalDetails.getUser().getNickname());

            // authentication객체를 session영역에 저장한다(권한 관리를 시큐리티가 해주기 위해서 -> 편하게 하려고)
            return authentication;  // 세션에 저장된다

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


     // attemptAuthentication이 먼저 실행되고, 인증이 정상적으로 실행되었으면 successfulAuthentication함수가 실행된다
    // JWT토큰을 만들어서 request요청한 사용자에게 response해준다
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        System.out.println("successfulAuthentication이 실행됨 : 인증 완료");

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // JWT 라이브러리 이용!
        String jwtToken = JWT.create()
                .withSubject("토큰 발급")
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME ))  // 만료시간(1000=1초)
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("nickname", principalDetails.getUser().getNickname())
                .sign(Algorithm.HMAC512("mergeplan"));  // 내 서버만 아는 고유 시크릿키

//        System.out.println(jwtToken);
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);  // 헤더에 담겨 사용자에게 응답된다
    }
}