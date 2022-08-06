package com.its.mergeplan_v1.dto;

import com.its.mergeplan_v1.entity.AccountsCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostAccountsCategory {
    private long userId;
    private int type;
    private String first;
    private int firstId;
    private String second;

    public AccountsCategory toEntity(long userId, int type, String first, int firstId, String second){
        return AccountsCategory.builder()
                .userId(userId)
                .type(type)
                .first(first)
                .firstId(firstId)
                .second(second)
                .build();
    }
}
