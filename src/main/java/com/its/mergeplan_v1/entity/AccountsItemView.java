package com.its.mergeplan_v1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts_item")
public class AccountsItemView {
    @Id
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "item_datetime")
    private Date itemDatetime;

    @Column(name = "plus")
    private int plus;

    @Column(name = "minus")
    private int minus;

    @Transient
    private int total = plus-minus;

    @Column(name="planner_id")
    private int plannerId;
}
