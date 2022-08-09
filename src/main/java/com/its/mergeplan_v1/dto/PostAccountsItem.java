package com.its.mergeplan_v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date itemDatetime;
    private boolean itemKind;
    private int itemFirst;
    private int itemSecond;
    private String itemTitle;
    private int itemPrice;
    private int plannerId;

    public AccountsItem toEntity(long userId, Timestamp createDatetime, Date itemDatetime, boolean itemKind, int itemFirst, int itemSecond, String itemTitle, int itemPrice, int plannerId){
        return AccountsItem.builder()
                .userId(userId)
                .createDatetime(createDatetime)
                .itemDatetime(itemDatetime)
                .itemKind(itemKind)
                .itemFirst(itemFirst)
                .itemSecond(itemSecond)
                .itemTitle(itemTitle)
                .itemPrice(itemPrice)
                .plannerId(plannerId)
                .build();
    }
}
