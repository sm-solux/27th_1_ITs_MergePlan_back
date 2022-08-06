package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostAccountsCategory;
import com.its.mergeplan_v1.dto.PostAccountsItem;
import com.its.mergeplan_v1.entity.AccountsCategory;
import com.its.mergeplan_v1.entity.AccountsItem;
import com.its.mergeplan_v1.entity.AccountsItemView;
import com.its.mergeplan_v1.repository.AccountsCategoryRepository;
import com.its.mergeplan_v1.repository.AccountsItemRepository;
import com.its.mergeplan_v1.repository.AccountsItemViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountsService {
    private final AccountsCategoryRepository accountsCategoryRepository;
    private final AccountsItemRepository accountsItemRepository;
    private final AccountsItemViewRepository accountsItemViewRepository;

    @Transactional
    public AccountsCategory saveCategory(PrincipalDetails principalDetails, PostAccountsCategory postAccountsCategory){
        AccountsCategory accountsCategory = postAccountsCategory.toEntity(
                principalDetails.getUser().getId(),
                //postAccountsCategory.getUserId(),
                postAccountsCategory.getType(),
                postAccountsCategory.getFirst(),
                postAccountsCategory.getFirstId(),
                postAccountsCategory.getSecond());
        return accountsCategoryRepository.save(accountsCategory);
    }

    @Transactional
    public AccountsItem saveItem(PrincipalDetails principalDetails, PostAccountsItem postAccountsItem){
        AccountsItem accountsItem = postAccountsItem.toEntity(
                principalDetails.getUser().getId(),
                postAccountsItem.getCreateDatetime(),
                postAccountsItem.getItemDatetime(),
                postAccountsItem.isItemKind(),
                postAccountsItem.getItemFirst(),
                postAccountsItem.getItemSecond(),
                postAccountsItem.getItemTitle(),
                postAccountsItem.getItemPrice(),
                postAccountsItem.getPlannerId());
        return accountsItemRepository.save(accountsItem);
    }

    @Transactional
    public List<AccountsItem> getItem(PrincipalDetails principalDetails){
        //return principalDetails.getUser().getId();
        return accountsItemRepository.findByUserId(principalDetails.getUser().getId());
    }

    @Transactional
    public List<AccountsItemView> getItem(PrincipalDetails principalDetails, String year, String month){
        return accountsItemViewRepository.findViewMonthly(principalDetails.getUser().getId(), year+"-"+month);
    }

    @Transactional
    public List<AccountsItem> getItem(PrincipalDetails principalDetails, String year, String month, String day){
        return accountsItemRepository.findByUserIdWhereDate(principalDetails.getUser().getId(), year+"-"+month+"-"+day);
    }
}
