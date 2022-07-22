package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.entity.Test;
import com.its.mergeplan_v1.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly = true)
public class TestService {
    private final TestRepository testRepository;

    public List<Test> findAll(){
        return testRepository.findAll();
    }
}
