package com.its.mergeplan_v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountsItem {

    private Long id;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date itemDateTime;
    private boolean itemKind;
    private int itemFirst;
    private int itemSecond;
    private String itemTitle;
    private int itemPrice;
    private Long plannerId;

}
