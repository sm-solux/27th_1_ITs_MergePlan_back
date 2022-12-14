package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.GetAccountsItem;
import com.its.mergeplan_v1.dto.PatchAccountsItem;
import com.its.mergeplan_v1.dto.PostAccountsCategory;
import com.its.mergeplan_v1.dto.PostAccountsItem;
import com.its.mergeplan_v1.entity.*;
import com.its.mergeplan_v1.repository.AccountsCategoryRepository;
import com.its.mergeplan_v1.repository.AccountsItemPRepository;
import com.its.mergeplan_v1.repository.AccountsItemRepository;
import com.its.mergeplan_v1.repository.AccountsItemViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountsService {
    private final AccountsCategoryRepository accountsCategoryRepository;
    private final AccountsItemRepository accountsItemRepository;
    private final AccountsItemViewRepository accountsItemViewRepository;
    private final AccountsItemPRepository accountsItemPRepository;

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
    public List<AccountsCategory> getCategory(){
        return accountsCategoryRepository.findAll();
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
    public AccountsItem saveItem(PatchAccountsItem patchAccountsItem){
        AccountsItem accountsItem = patchAccountsItem.toEntity(
                patchAccountsItem.getId(),
                patchAccountsItem.getUserId(),
                patchAccountsItem.getCreateDatetime(),
                patchAccountsItem.getItemDatetime(),
                patchAccountsItem.isItemKind(),
                patchAccountsItem.getItemFirst(),
                patchAccountsItem.getItemSecond(),
                patchAccountsItem.getItemTitle(),
                patchAccountsItem.getItemPrice(),
                patchAccountsItem.getPlannerId());
        return accountsItemRepository.save(accountsItem);
    }

    @Transactional
    public List<AccountsItem> getItem(PrincipalDetails principalDetails){
        //return principalDetails.getUser().getId();
        return accountsItemRepository.findByUserId(principalDetails.getUser().getId());
    }

    @Transactional
    public void deleteItem(long id){
        accountsItemRepository.deleteById(id);
    }

    @Transactional
    public List<AccountsItem> getItem(PrincipalDetails principalDetails, String year, String month){
        return accountsItemRepository.findByUserIdWhereMonth(principalDetails.getUser().getId(), year+"-"+month);
    }

    @Transactional
    public List<AccountsItem> getItemIncome(PrincipalDetails principalDetails, String year, String month){
        return accountsItemRepository.findIncomeByUserIdWhereMonth(principalDetails.getUser().getId(), year+"-"+month);
    }

    @Transactional
    public List<AccountsItem> getItemExpenses(PrincipalDetails principalDetails, String year, String month){
        return accountsItemRepository.findExpensesByUserIdWhereMonth(principalDetails.getUser().getId(), year+"-"+month);
    }

    @Transactional
    public Object getItemTotal(PrincipalDetails principalDetails, String year, String month){
        return accountsItemRepository.findTotal(principalDetails.getUser().getId(), year+"-"+month);
    }

    @Transactional
    public List<AccountsItemView> getItemCalendar(PrincipalDetails principalDetails, String year, String month){
        return accountsItemViewRepository.findViewMonthly(principalDetails.getUser().getId(), year+"-"+month);
    }

    @Transactional
    public List<AccountsItem> getItemCalendar(PrincipalDetails principalDetails, String year, String month, String day){
        return accountsItemRepository.findByUserIdWhereDate(principalDetails.getUser().getId(), year+"-"+month+"-"+day);
    }

    @Transactional
    public List<AccountsItemP> getItemByPlannerId(Long plannerId){
        return accountsItemPRepository.findByPlannerId(plannerId);
    }

    @Transactional(readOnly = true)
    public GetAccountsItem oneItem(User user, Long account_id){
        Optional<AccountsItem> accountsItem = accountsItemRepository.findById(account_id);

        GetAccountsItem getAccountsItem = new GetAccountsItem();
        getAccountsItem.setId(accountsItem.get().getId());
        getAccountsItem.setUserId(accountsItem.get().getUserId());
        getAccountsItem.setItemDateTime(accountsItem.get().getItemDatetime());
        getAccountsItem.setItemFirst(accountsItem.get().getItemFirst());
        getAccountsItem.setItemSecond(accountsItem.get().getItemSecond());
        getAccountsItem.setItemTitle(accountsItem.get().getItemTitle());
        getAccountsItem.setItemPrice(accountsItem.get().getItemPrice());
        getAccountsItem.setPlannerId(accountsItem.get().getPlannerId());

        return getAccountsItem;

    }
}
