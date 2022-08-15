package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
    List<Planner> findByUserId(Long userId);
    Planner findById(long planId);
}
