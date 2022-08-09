package com.its.mergeplan_v1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts_item")
public class AccountsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "user_id")
    private long userId;

    @CreationTimestamp
    @Column(name = "create_datetime")
    private Timestamp createDatetime;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "item_datetime")
    private Date itemDatetime;

    @Column(name = "item_kind")
    private boolean itemKind;

    @Column(name = "item_first")
    private int itemFirst;

    @Column(name = "item_second")
    private int itemSecond;

    @Column(name = "item_title")
    private String itemTitle;

    @Column(name="item_price")
    private int itemPrice;

    @Column(name = "planner_id")
    private int plannerId;
}
