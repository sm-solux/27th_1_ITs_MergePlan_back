package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.AccountsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsCategoryRepository extends JpaRepository<AccountsCategory, Long> {
}
