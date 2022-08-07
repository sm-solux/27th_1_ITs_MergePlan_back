package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.AccountsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AccountsItemRepository extends JpaRepository<AccountsItem, Long> {
    List<AccountsItem> findByUserId(long userId);

    @Query(value = "select * from accounts_item "
            + "where user_id = ?1 "
            + "and left(item_datetime, 7) = ?2", nativeQuery = true)
    public List<AccountsItem> findByUserIdWhereMonth(long userId, String day);

    @Query(value="select * from accounts_item "
            + "where user_id = ?1 "
            + "and date(item_datetime) = ?2", nativeQuery = true)
    public List<AccountsItem> findByUserIdWhereDate(long userId, String day);
}
