package com.its.mergeplan_v1.controller;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostUser;
import com.its.mergeplan_v1.entity.User;
import com.its.mergeplan_v1.repository.UserRepository;
import com.its.mergeplan_v1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestApiController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/home")
    public String home(){
        return "<h1>HOME</h1>";
    }

    @PostMapping("join")  // 회원가입 api
    public User join(@RequestBody PostUser postUser){
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRole("ROLE_USER");
        //userRepository.save(user);
        return userService.PostUser(postUser);
        //return "회원가입 완료";
    }

    // user권한만 접근가능
    @GetMapping("/api/v1/user")
    public String user(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return "user " + principalDetails.getUsername() + "!!";
    }

    // admin권한만 접근가능
    @GetMapping("/api/v1/admin")
    public String admin(){
        return "admin";
    }
}
