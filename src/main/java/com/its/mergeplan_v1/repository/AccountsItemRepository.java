package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.AccountsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AccountsItemRepository extends JpaRepository<AccountsItem, Long> {
    List<AccountsItem> findByUserId(long userId);
    Optional<AccountsItem> findById(Long accountId);

    @Query(value = "select * from accounts_item "
            + "where user_id = ?1 "
            + "and left(item_datetime, 7) = ?2", nativeQuery = true)
    public List<AccountsItem> findByUserIdWhereMonth(long userId, String day);

    @Query(value = "select * from accounts_item "
            + "where user_id = ?1 and item_kind = 0 "
            + "and left(item_datetime, 7) = ?2", nativeQuery = true)
    public List<AccountsItem> findIncomeByUserIdWhereMonth(long userId, String day);

    @Query(value = "select * from accounts_item "
            + "where user_id = ?1 and item_kind = 1 "
            + "and left(item_datetime, 7) = ?2", nativeQuery = true)
    public List<AccountsItem> findExpensesByUserIdWhereMonth(long userId, String day);

    @Query(value="select sum(case when item_kind = 0 then item_price else 0 end) as income, " +
            "sum(case when item_kind = 1 then item_price else 0 end) as expenses, " +
            "sum(case when item_kind = 0 then item_price else 0 end) - sum(case when item_kind = 1 then item_price else 0 end) as total " +
            "from accounts_item where user_id = ?1 " +
            "and left(item_datetime, 7) = ?2", nativeQuery = true)
    public Map<String, Integer> findTotal(long userId, String day);

    @Query(value="select * from accounts_item "
            + "where user_id = ?1 "
            + "and date(item_datetime) = ?2", nativeQuery = true)
    public List<AccountsItem> findByUserIdWhereDate(long userId, String day);
}
