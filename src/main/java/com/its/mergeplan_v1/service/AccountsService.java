package com.its.mergeplan_v1.service;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostAccountsCategory;
import com.its.mergeplan_v1.entity.AccountsCategory;
import com.its.mergeplan_v1.repository.AccountsCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountsService {
    private final AccountsCategoryRepository accountsCategoryRepository;

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
}
