package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostPlanner;
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
    public Planner createPlan(PrincipalDetails principalDetails, PostPlanner postPlannerReq){
        User user = userRepository.findById(principalDetails.getUser().getId());
        Planner planner = postPlannerReq.toEntity(postPlannerReq.isAllday(), postPlannerReq.getStartTime(), postPlannerReq.getEndTime(), postPlannerReq.getTitle(), postPlannerReq.getCategory(), postPlannerReq.getDescription());
        return plannerRepository.save(planner);
    }
}
