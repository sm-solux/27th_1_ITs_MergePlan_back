package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    User findById(long userId);
    User findByNickname(String nickname);
}
