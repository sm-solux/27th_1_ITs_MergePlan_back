package com.its.mergeplan_v1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "planner")
public class Planner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "user_id")
    private Long userId;

    private boolean allDay;  // 하루종일 하는 일정인지

    @CreationTimestamp
    @Column(name = "start")
    private Timestamp startDatetime;

    @CreationTimestamp
    @Column(name = "end")
    private Timestamp endDatetime;

    private String title;

    private String description;

    @CreationTimestamp
    @Column(name = "create_date")
    private Timestamp createDate;

    @Column(name = "item_first")
    private int itemFirst;

    @Transient
    private String itemFirstWord;

}
