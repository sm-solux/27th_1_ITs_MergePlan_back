package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.AccountsItemView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsItemViewRepository extends JpaRepository<AccountsItemView, Long> {
    @Query(value="select item_datetime, sum(case when item_kind = 0 then item_price else 0 end) as plus, "
            + "sum(case when item_kind = 1 then item_price else 0 end) as minus, planner_id "
            + "from accounts_item where user_id = ?1 and left(item_datetime, 7) = ?2 "
            + "group by date(item_datetime), planner_id order by date(item_datetime), planner_id", nativeQuery = true)
        //sum(case when item_kind = 0 then item_price else 0 end) - sum(case when item_kind = 1 then item_price else 0 end) as total,
    //public List<AccountsItemView> findViewMonthly(@Param(value = "userId") long userId, @Param(value = "month") String month);
    public List<AccountsItemView> findViewMonthly(long userId, String month);
}
