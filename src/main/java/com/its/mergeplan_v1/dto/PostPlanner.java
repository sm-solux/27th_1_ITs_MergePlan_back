package com.its.mergeplan_v1.dto;

import com.its.mergeplan_v1.entity.Planner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPlanner {
    private Long userId;
    private boolean allday;
    private Timestamp startTime;
    private Timestamp endTime;
    private String title;
    private int category;
    private String description;

    public Planner toEntity(Long userId, boolean allday, Timestamp startDatetime, Timestamp endDatetime, String title, int category, String description){
        String[] str = {"Work", "Party", "Shopping", "Dining", "Trip"};
        return Planner.builder()
                .userId(userId)
                .allday(allday)
                .startDatetime(startDatetime)
                .endDatetime(endDatetime)
                .title(title)
                .itemFirstWord(str[getCategory()-1])
                .description(description).build();
    }

}
