package com.its.mergeplan_v1.dto;

import com.its.mergeplan_v1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUser {
    private String username;
    private String password;
    private String role = "ROLE_USER";
    private Date birthday;
    private boolean active = true;
    private Timestamp createDate;

    public User toEntity(String username, String password, String role, Date birthday, boolean active, Timestamp createDate){
        return User.builder()
                .username(username)
                .password(password)
                .role(role)
                .birthday(birthday)
                .active(active)
                .createDate(createDate).build();
    }
}
