package com.its.mergeplan_v1.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
public class Planner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean allday;  // 하루종일 하는 일정인지

    @CreationTimestamp
    private LocalDateTime startTime;

    @CreationTimestamp
    private LocalDateTime endTime;

    private String title;

    private Category category;

    private String description;

    @CreationTimestamp
    private Timestamp createDate;


}
