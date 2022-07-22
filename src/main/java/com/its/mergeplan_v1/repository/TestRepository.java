package com.its.mergeplan_v1.repository;

import com.its.mergeplan_v1.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
//    @Query(value="select * from test")
//    List<Test> getTest();
}
