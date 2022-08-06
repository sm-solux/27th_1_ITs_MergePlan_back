package com.its.mergeplan_v1.dto;

import com.its.mergeplan_v1.entity.AccountsItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostAccountsItem {
    private long userId;
    private Timestamp createDatetime;
    private Date itemDatetime;
    private int itemFirst;
    private int itemSecond;
    private String itemTitle;
    private int plannerId;

    public AccountsItem toEntity(long userId, Timestamp createDatetime, Date itemDatetime, int itemFirst, int itemSecond, String itemTitle, int plannerId){
        return AccountsItem.builder()
                .userId(userId)
                .createDatetime(createDatetime)
                .itemDatetime(itemDatetime)
                .itemFirst(itemFirst)
                .itemSecond(itemSecond)
                .itemTitle(itemTitle)
                .plannerId(plannerId)
                .build();
    }
}
