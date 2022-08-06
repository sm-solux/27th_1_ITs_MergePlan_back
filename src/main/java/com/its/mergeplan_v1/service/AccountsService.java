package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostAccountsCategory;
import com.its.mergeplan_v1.dto.PostAccountsItem;
import com.its.mergeplan_v1.entity.AccountsCategory;
import com.its.mergeplan_v1.entity.AccountsItem;
import com.its.mergeplan_v1.repository.AccountsCategoryRepository;
import com.its.mergeplan_v1.repository.AccountsItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountsService {
    private final AccountsCategoryRepository accountsCategoryRepository;
    private final AccountsItemRepository accountsItemRepository;

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
                postAccountsItem.getItemFirst(),
                postAccountsItem.getItemSecond(),
                postAccountsItem.getItemTitle(),
                postAccountsItem.getPlannerId());
        return accountsItemRepository.save(accountsItem);
    }
}
