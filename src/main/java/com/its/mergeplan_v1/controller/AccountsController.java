package com.its.mergeplan_v1.controller;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostAccountsCategory;
import com.its.mergeplan_v1.dto.PostAccountsItem;
import com.its.mergeplan_v1.entity.AccountsCategory;
import com.its.mergeplan_v1.entity.AccountsItem;
import com.its.mergeplan_v1.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;

    @PostMapping("/auth/accounts/category")
    public AccountsCategory setCategory(Authentication auth, @RequestBody PostAccountsCategory postAccountsCategory){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.saveCategory(principalDetails, postAccountsCategory);
    }

    @PostMapping("/auth/accounts/item")
    public AccountsItem setItem(Authentication auth, @RequestBody PostAccountsItem postAccountsItem){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.saveItem(principalDetails, postAccountsItem);
    }
}
