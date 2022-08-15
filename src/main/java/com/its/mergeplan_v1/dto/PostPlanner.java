package com.its.mergeplan_v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.mergeplan_v1.entity.Planner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostPlanner {
    private Long userId;
    private boolean allDay;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end;
    private String title;
    private int category;
    private String description;
    private String itemFirstWord;
    private int itemFirst;

    public Planner toEntity(Long userId, boolean allDay, Date start, Date end, String title, int category, String description){
        String[] str = {"Work", "Party", "Shopping", "Dining", "Trip"};
        return Planner.builder()
                .userId(userId)
                .allday(allDay)
                .startDatetime(start)
                .endDatetime(end)
                .title(title)
                .itemFirst(category)
                .itemFirstWord(str[category - 1])
                .description(description).build();
    }

}
