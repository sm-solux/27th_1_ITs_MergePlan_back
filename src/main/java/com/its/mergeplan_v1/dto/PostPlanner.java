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
    private boolean allDay;
    private Timestamp start;
    private Timestamp end;
    private String title;
    private int category;
    private String description;
    private String itemFirstWord;

    public Planner toEntity(Long userId, boolean allDay, Timestamp start, Timestamp end, String title, int category, String description){
        String[] str = {"Work", "Party", "Shopping", "Dining", "Trip"};
        return Planner.builder()
                .userId(userId)
                .allday(allDay)
                .startDatetime(start)
                .endDatetime(end)
                .title(title)
                .itemFirstWord(str[getCategory()-1])
                .description(description).build();
    }

}
