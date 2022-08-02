package com.its.mergeplan_v1.controller;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostPlanner;
import com.its.mergeplan_v1.entity.Planner;
import com.its.mergeplan_v1.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping("/auth/planner")
    public Planner setPlan(Authentication authentication, @RequestBody PostPlanner postPlannerReq){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return plannerService.createPlan(principalDetails, postPlannerReq);
    }
}