package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.GetPlanner;
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
        Planner planner = postPlannerReq.toEntity(loginUser.getId(), postPlannerReq.isAllDay(), postPlannerReq.getStart(), postPlannerReq.getEnd(), postPlannerReq.getTitle(), postPlannerReq.getCategory(), postPlannerReq.getDescription());
        return plannerRepository.save(planner);
    }

    @Transactional(readOnly = true)
    public List<GetPlanner> mainPlannerPage(User loginUser){
        User user = userRepository.findById(loginUser.getId());
        List<Planner> plannerList = plannerRepository.findByUserId(user.getId());
        List<GetPlanner> getPlannerLists = new ArrayList<>();
        String[] str = {"Work", "Party", "Shopping", "Dining", "Trip"};

        for (Planner pn: plannerList){
            GetPlanner getPlanner = new GetPlanner();
            getPlanner.setTitle(pn.getTitle());
            getPlanner.setAllDay(pn.isAllday());
            getPlanner.setCategory(pn.getItemFirst());
            getPlanner.setStart(pn.getStartDatetime());
            getPlanner.setEnd(pn.getEndDatetime());
            getPlanner.setDescription(pn.getDescription());
            getPlanner.setUserId(user.getId());
            getPlanner.setItemFirstWord(str[pn.getItemFirst()-1]);

            getPlannerLists.add(getPlanner);
        }

        return getPlannerLists;
    }

    @Transactional
    public Planner updatePlan(User user, PostPlanner postPlanner, int plan_id){
        Planner origin_planner = plannerRepository.findById(plan_id);
        String[] str = {"Work", "Party", "Shopping", "Dining", "Trip"};
        if (origin_planner.getUserId() == user.getId()){
            origin_planner.setDescription(postPlanner.getDescription());
            origin_planner.setItemFirstWord(str[postPlanner.getCategory()-1]);
            origin_planner.setTitle(postPlanner.getTitle());
            origin_planner.setItemFirstWord(postPlanner.getItemFirstWord());
            origin_planner.setStartDatetime(postPlanner.getStart());
            origin_planner.setEndDatetime(postPlanner.getEnd());
            origin_planner.setAllday(postPlanner.isAllDay());

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
