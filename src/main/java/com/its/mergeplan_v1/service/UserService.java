package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.dto.PostUser;
import com.its.mergeplan_v1.entity.User;
import com.its.mergeplan_v1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User PostUser(PostUser postUser){
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setRole("ROLE_USER");
        User user = postUser.toEntity(postUser.getUsername(),
                bCryptPasswordEncoder.encode(postUser.getPassword()),
                postUser.getRole(),
                postUser.getBirthday(),
                postUser.isActive(),
                postUser.getCreateDate());
        return userRepository.save(user);
    }

}
