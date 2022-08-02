package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostPlannerReq;
import com.its.mergeplan_v1.entity.Planner;
import com.its.mergeplan_v1.entity.User;
import com.its.mergeplan_v1.repository.PlannerRepository;
import com.its.mergeplan_v1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;

    @Transactional
    public String createPlan(PrincipalDetails principalDetails, PostPlannerReq postPlannerReq){
        User user = userRepository.findById(principalDetails.getUser().getId());
        Planner planner = postPlannerReq.toEntity(postPlannerReq.isAllday(), postPlannerReq.getStartTime(), postPlannerReq.getEndTime(), postPlannerReq.getTitle(), postPlannerReq.getCategory(), postPlannerReq.getDescription());
        plannerRepository.save(planner);
        return "완료";
    }
}
