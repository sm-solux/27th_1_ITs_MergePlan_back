package com.its.mergeplan_v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts_category")
public class AccountsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "user_id")
    private long userId;

    @Column(nullable = false)
    private int type;  // 대구분, 소구분

    @Column(nullable = true)
    private String first;  // 대구분

    @Column(nullable = true, name = "first_id")
    private int firstId;  // 대구분 id

    @Column(nullable = true)
    private String second;  // 소구분
}
