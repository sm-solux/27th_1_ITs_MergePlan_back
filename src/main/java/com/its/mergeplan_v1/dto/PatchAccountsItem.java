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
public class PatchAccountsItem {
    private Long id;
    private Long userId;
    private Timestamp createDatetime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date itemDatetime;
    private boolean itemKind;
    private int itemFirst;
    private int itemSecond;
    private String itemTitle;
    private int itemPrice;
    private Long plannerId;

    public AccountsItem toEntity(Long id, Long userId, Timestamp createDatetime, Date itemDatetime, boolean itemKind, int itemFirst, int itemSecond, String itemTitle, int itemPrice, Long plannerId){
        return AccountsItem.builder()
                .id(id)
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
