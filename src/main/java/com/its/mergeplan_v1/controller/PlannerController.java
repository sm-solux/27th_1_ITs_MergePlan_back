package com.its.mergeplan_v1.controller;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostPlanner;
import com.its.mergeplan_v1.dto.PostPlannerList;
import com.its.mergeplan_v1.entity.Planner;
import com.its.mergeplan_v1.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;

    @PostMapping("/auth/planner/item")  // 플랜 생성하기
    public Planner setPlan(Authentication authentication, @RequestBody PostPlanner postPlannerReq){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return plannerService.setPlan(principalDetails.getUser(), postPlannerReq);
    }

    @PatchMapping("/auth/planner/{plan_id}")  // 플래너 일정 수정하기
    public Planner updatePlan(Authentication authentication, @PathVariable int plan_id, @RequestBody PostPlanner postPlanner){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return plannerService.updatePlan(principalDetails.getUser(), postPlanner, plan_id);
    }


    @GetMapping("/auth/planner/item")  // 플래너 메인 페이지
    public List<PostPlanner> mainPlannerPage(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return plannerService.mainPlannerPage(principalDetails.getUser());
    }

    @DeleteMapping("/auth/planner/{plan_id}")  // 플랜 삭제하기
    public void deletePlan(Authentication authentication, @PathVariable int plan_id){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        plannerService.deletePlan(principalDetails.getUser(), plan_id);
    }

}