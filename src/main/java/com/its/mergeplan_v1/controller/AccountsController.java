package com.its.mergeplan_v1.controller;

import com.its.mergeplan_v1.config.auth.PrincipalDetails;
import com.its.mergeplan_v1.dto.PatchAccountsItem;
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

    @GetMapping("/auth/accounts/category")
    public List<AccountsCategory> getCategory(){
        return accountsService.getCategory();
    }

    @PostMapping("/auth/accounts/item")
    public AccountsItem setItem(Authentication auth, @RequestBody PostAccountsItem postAccountsItem){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.saveItem(principalDetails, postAccountsItem);
    }

    @GetMapping("/auth/accounts/item")
    public List<AccountsItem> getItem(Authentication auth){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItem(principalDetails);
    }

    @PatchMapping("/auth/accounts/item")
    public AccountsItem patchItem(@RequestBody PatchAccountsItem patchAccountsItem){
        return accountsService.saveItem(patchAccountsItem);
    }

    @DeleteMapping("/auth/accounts/item/{id}")
    public String deleteItem(@PathVariable("id") int id){
        accountsService.deleteItem(id);
        return "test!";
    }

    @GetMapping("/auth/accounts/item/{year}/{month}")
    public List<AccountsItem> getItem(Authentication auth, @PathVariable("year") String year, @PathVariable("month") String month){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItem(principalDetails, year, month);
    }

    @GetMapping("/auth/accounts/item/income/{year}/{month}")
    public List<AccountsItem> getItemIncome(Authentication auth, @PathVariable("year") String year, @PathVariable("month") String month){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItemIncome(principalDetails, year, month);
    }

    @GetMapping("/auth/accounts/item/expenses/{year}/{month}")
    public List<AccountsItem> getItemExpenses(Authentication auth, @PathVariable("year") String year, @PathVariable("month") String month){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItemExpenses(principalDetails, year, month);
    }

    @GetMapping("/auth/accounts/item/total/{year}/{month}")
    public Object getItemTotal(Authentication auth, @PathVariable("year") String year, @PathVariable("month") String month){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItemTotal(principalDetails, year, month);
    }

    @GetMapping("/auth/accounts/item/calendar/{year}/{month}")
    public List<AccountsItemView> getItemMonthly(Authentication auth, @PathVariable("year") String year, @PathVariable("month") String month){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItemCalendar(principalDetails, year, month);
    }

    @GetMapping("/auth/accounts/item/calendar/{year}/{month}/{day}")
    public List<AccountsItem> getItem(Authentication auth, @PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("day") String day){
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
        return accountsService.getItemCalendar(principalDetails, year, month, day);
    }
}
