package com.its.mergeplan_v1.dto;

import com.its.mergeplan_v1.entity.Category;
import com.its.mergeplan_v1.entity.Planner;
import com.its.mergeplan_v1.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPlannerReq {
    private User user;
    private boolean allday;
    private Timestamp startTime;
    private Timestamp endTime;
    private String title;
    private Category category;
    private String description;

    public Planner toEntity(boolean allday, Timestamp startDatetime, Timestamp endDatetime, String title, Category category, String description){
        return Planner.builder()
//                .user(user)
                .allday(allday)
                .startDatetime(startDatetime)
                .endDatetime(endDatetime)
                .title(title)
                .category(category)
                .description(description).build();
    }

}
