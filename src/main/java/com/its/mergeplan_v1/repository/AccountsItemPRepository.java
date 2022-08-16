package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.AccountsItemP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsItemPRepository extends JpaRepository<AccountsItemP, Long> {

    List<AccountsItemP> findByPlannerId(long plannerId);

}
