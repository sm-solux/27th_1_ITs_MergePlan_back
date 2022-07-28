package com.its.mergeplan_v1.config.auth.controller;

import com.its.mergeplan_v1.entity.Test;
import com.its.mergeplan_v1.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public String test(@RequestParam("name") String name){
        return "hi " + name;
    }

    @GetMapping("/getTest")
    public List<Test> getTest(){
        return testService.findAll();
    }

}
