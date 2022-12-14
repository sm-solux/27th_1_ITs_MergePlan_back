package com.its.mergeplan_v1.controller;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.GetPlanner;
import com.its.mergeplan_v1.dto.PostPlanner;
import com.its.mergeplan_v1.dto.PostPlannerList;
import com.its.mergeplan_v1.entity.Planner;
import com.its.mergeplan_v1.service.AccountsService;
import com.its.mergeplan_v1.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlannerController {

    private final PlannerService plannerService;
    private final AccountsService accountsService;

    @PostMapping("/auth/planner/item")  // 플랜 생성하기
    public Planner setPlan(Authentication authentication, @RequestBody PostPlanner postPlannerReq){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return plannerService.setPlan(principalDetails.getUser(), postPlannerReq);
    }

    @PatchMapping("/auth/planner/item/{plan_id}")  // 플래너 일정 수정하기
    public Planner updatePlan(Authentication authentication, @PathVariable Long plan_id, @RequestBody PostPlanner postPlanner){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        return plannerService.updatePlan(principalDetails.getUser(), postPlanner, plan_id);
    }


    @GetMapping("/auth/planner/item")  // 플래너 메인 페이지
    public List<GetPlanner> mainPlannerPage(Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        List<GetPlanner> getPlanners = plannerService.mainPlannerPage(principalDetails.getUser());
        for(GetPlanner getPlanner : getPlanners){
            getPlanner.setAccountsItemPs(accountsService.getItemByPlannerId(getPlanner.getId()));
        }
        return getPlanners;
    }

    @DeleteMapping("/auth/planner/item/{plan_id}")  // 플랜 삭제하기
    public void deletePlan(Authentication authentication, @PathVariable Long plan_id){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        plannerService.deletePlan(principalDetails.getUser(), plan_id);
    }

    @GetMapping("/auth/planner/item/{plan_id}")  // 플랜 하나 조회하기
    public GetPlanner onePlan(Authentication authentication, @PathVariable Long plan_id){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        GetPlanner getPlanner = plannerService.onePlan(principalDetails.getUser(), plan_id);
        getPlanner.setAccountsItemPs(accountsService.getItemByPlannerId(getPlanner.getId()));
        return getPlanner;
    }

}