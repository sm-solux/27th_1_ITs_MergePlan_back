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
import java.util.Optional;

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
            getPlanner.setPlannerId(pn.getId());
            getPlanner.setTitle(pn.getTitle());
            getPlanner.setAllDay(pn.isAllday());
            getPlanner.setCategory(pn.getItemFirst());
            getPlanner.setStart(pn.getStartDatetime());
//            System.out.println("==================");
            System.out.println(pn.getStartDatetime());
//            System.out.println(pn);
//            System.out.println("====================");
            getPlanner.setEnd(pn.getEndDatetime());
            getPlanner.setDescription(pn.getDescription());
            getPlanner.setCreateDate(pn.getCreateDate());
            getPlanner.setUserId(user.getId());
            getPlanner.setItemFirstWord(str[pn.getItemFirst()-1]);

            getPlannerLists.add(getPlanner);
        }

        return getPlannerLists;
    }

    @Transactional
    public Planner updatePlan(User user, PostPlanner postPlanner, Long plan_id){
        Optional<Planner> origin_planner = plannerRepository.findById(plan_id);
        String[] str = {"Work", "Party", "Shopping", "Dining", "Trip"};
        if (origin_planner.get().getUserId() == user.getId()){
            origin_planner.get().setDescription(postPlanner.getDescription());
            origin_planner.get().setItemFirstWord(str[postPlanner.getCategory()+1]);
            origin_planner.get().setTitle(postPlanner.getTitle());
            origin_planner.get().setItemFirst(postPlanner.getItemFirst());
            origin_planner.get().setStartDatetime(postPlanner.getStart());
            origin_planner.get().setEndDatetime(postPlanner.getEnd());
            origin_planner.get().setAllday(postPlanner.isAllDay());

            return origin_planner.get();
        } else{
            return null;
        }
    }

    @Transactional
    public void deletePlan(User loginUser, Long plan_id){
        Optional<Planner> planner = plannerRepository.findById(plan_id);
        plannerRepository.delete(planner.get());
    }

    @Transactional
    public GetPlanner onePlan(User user, Long plan_id){
        Optional<Planner> planner = plannerRepository.findById(plan_id);
        String[] str = {"Work", "Party", "Shopping", "Dining", "Trip"};

        GetPlanner getPlanner = new GetPlanner();
        getPlanner.setPlannerId(planner.get().getId());
        getPlanner.setUserId(planner.get().getUserId());
        getPlanner.setAllDay(planner.get().isAllday());
        getPlanner.setDescription(planner.get().getDescription());
        getPlanner.setTitle(planner.get().getTitle());
        getPlanner.setCategory(planner.get().getItemFirst());
        getPlanner.setStart(planner.get().getStartDatetime());
        getPlanner.setEnd(planner.get().getEndDatetime());
        getPlanner.setCreateDate(planner.get().getCreateDate());
        getPlanner.setItemFirstWord(str[planner.get().getItemFirst()-1]);

        return getPlanner;
    }
}
