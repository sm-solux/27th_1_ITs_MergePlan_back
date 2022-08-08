package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostPlanner;
import com.its.mergeplan_v1.entity.Planner;
import com.its.mergeplan_v1.entity.User;
import com.its.mergeplan_v1.repository.PlannerRepository;
import com.its.mergeplan_v1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlannerService {

    private final PlannerRepository plannerRepository;
    private final UserRepository userRepository;

    @Transactional
    public Planner setPlan(User loginUser, PostPlanner postPlannerReq){
        User user = userRepository.findById(loginUser.getId());
        Planner planner = postPlannerReq.toEntity(postPlannerReq.isAllday(), postPlannerReq.getStartTime(), postPlannerReq.getEndTime(), postPlannerReq.getTitle(), postPlannerReq.getCategory(), postPlannerReq.getDescription());
        return plannerRepository.save(planner);
    }

    @Transactional(readOnly = true)
    public List<PostPlanner> mainPlannerPage(User loginUser, Long user_id){
        User user = userRepository.findById(loginUser.getId());
        List<Planner> plannerList = plannerRepository.findByUserId(user_id);
        List<PostPlanner> postPlannerLists = new ArrayList<>();

        for (Planner pn: plannerList){
            PostPlanner postPlanner = new PostPlanner();
            postPlanner.setTitle(pn.getTitle());
            postPlanner.setAllday(pn.isAllday());
            postPlanner.setCategory(pn.getCategory());
            postPlanner.setStartTime(pn.getStartDatetime());
            postPlanner.setEndTime(pn.getEndDatetime());
            postPlanner.setDescription(pn.getDescription());
            postPlanner.setUser(pn.getUser());

            postPlannerLists.add(postPlanner);
        }

        return postPlannerLists;
    }

    @Transactional
    public Planner updatePlan(User user, PostPlanner postPlanner, int plan_id){
        Planner origin_planner = plannerRepository.findById(plan_id);
        if (origin_planner.getUser().getId() == user.getId()){
            origin_planner.setDescription(postPlanner.getDescription());
            origin_planner.setCategory(postPlanner.getCategory());
            origin_planner.setStartDatetime(postPlanner.getStartTime());
            origin_planner.setEndDatetime(postPlanner.getEndTime());
            origin_planner.setAllday(postPlanner.isAllday());

            return origin_planner;
        } else{
            return null;
        }
    }

    @Transactional
    public void deletePlan(User loginUser, int plan_id){
        Planner planner = plannerRepository.findById(plan_id);
        plannerRepository.delete(planner);
    }
}
