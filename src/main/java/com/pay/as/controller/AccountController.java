package com.pay.as.controller;

import com.pay.as.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;



    @PostMapping("/accounts")
    @ApiOperation(value = "Join")
    public void join(
            @RequestParam @NotNull String identify,
            @RequestParam @NotNull String password,
            @RequestParam @NotNull String name,
            @RequestParam(defaultValue = "0") Long fixedIncome,
            @RequestParam(defaultValue = "00") String cycleIncome,
            @RequestParam(defaultValue = "0") Long salary) {
        accountService.join(identify, password, name, fixedIncome, cycleIncome, salary);
    }



    @GetMapping("/accounts")
    @ApiOperation(value = "Login")
    public void login(
            @RequestParam @NotNull String identify,
            @RequestParam @NotNull String password) {

        System.out.println(accountService.login(identify, password));
    }

}
