package com.its.mergeplan_v1.controller;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PostAccountsCategory;
import com.its.mergeplan_v1.dto.PostAccountsItem;
import com.its.mergeplan_v1.entity.AccountsCategory;
import com.its.mergeplan_v1.entity.AccountsItem;
import com.its.mergeplan_v1.entity.AccountsItemView;
import com.its.mergeplan_v1.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 쓸 일 없는 확인용 값
    @GetMapping("/auth/accounts/item")
    public List<AccountsItem> getItem(Authentication auth){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItem(principalDetails);
    }

    @GetMapping("/auth/accounts/item/{year}/{month}")
    public List<AccountsItemView> getItem(Authentication auth, @PathVariable("year") String year, @PathVariable("month") String month){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItem(principalDetails, year, month);
    }

    @GetMapping("/auth/accounts/item/{year}/{month}/{day}")
    public List<AccountsItem> getItem(Authentication auth, @PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItem(principalDetails, year, month, day);
    }
}
