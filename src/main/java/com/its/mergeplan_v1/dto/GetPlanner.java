package com.its.mergeplan_v1.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.its.mergeplan_v1.entity.AccountsItemP;
import com.its.mergeplan_v1.entity.Planner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPlanner {
    private Long id;
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
    private Timestamp createDate;
    @Transient
    private List<AccountsItemP> accountsItemPs = new ArrayList<>();


    public Planner toEntity(Long id, Long userId, boolean allDay, Date start, Date end, String title, int category, String description, Timestamp createDate){
        String[] str = {"Work", "Party", "Shopping", "Dining", "Trip"};
        return Planner.builder()
                .id(id)
                .userId(userId)
                .allday(allDay)
                .startDatetime(start)
                .endDatetime(end)
                .title(title)
                .createDate(createDate)
                .itemFirst(category)
                .itemFirstWord(str[category - 1])
                .description(description).build();
    }


}
