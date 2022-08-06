package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.AccountsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsItemRepository extends JpaRepository<AccountsItem, Long> {

}
