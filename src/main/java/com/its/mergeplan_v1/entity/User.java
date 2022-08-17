package com.its.mergeplan_v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nickname;  // 아이디

    @Column(nullable = false)
    private String username;  // 유저 이름

    @Column(nullable = false)
    private String password;

    private String role;

    @Column(nullable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    private boolean active = true;  // 탈퇴시 false로

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Planner> plannerList;

    @CreationTimestamp
    private Timestamp createDate;
//    private String roles;  // USER, ADMIN

//    public List<String> getRoleList(){
//        if (this.roles.length() > 0){
//            return Arrays.asList(this.roles.split(","))
//        }
//        return new ArrayList<>();
//    }

}
