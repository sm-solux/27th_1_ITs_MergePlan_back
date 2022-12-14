package com.its.mergeplan_v1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "planner")
public class Planner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    private boolean allday;  // 하루종일 하는 일정인지

    @Column(name = "start_datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDatetime;

    @Column(name = "end_datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDatetime;

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
