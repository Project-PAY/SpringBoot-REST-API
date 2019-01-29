package com.pay.as.controller;

import com.pay.as.domain.UserDomain;
import com.pay.as.service.AccountService;
import com.pay.as.service.JWTService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    JWTService jwtService;


    @PostMapping("/accounts")
    @ApiOperation(value = "Join")
    public void join(
            @RequestParam @NotNull @Size(max = 32) String identify,
            @RequestParam @NotNull @Size(max = 64) String password,
            @RequestParam @NotNull @Size(max = 32) String name,
            @RequestParam(defaultValue = "0") Long fixedIncome,
            @RequestParam(defaultValue = "00") @Pattern(regexp = "^[0-3][0-9]$") String cycleIncome,
            @RequestParam(defaultValue = "0") Long salary) {
        accountService.join(identify, password, name, fixedIncome, cycleIncome, salary);
    }



    @GetMapping("/accounts")
    @ApiOperation(value = "Login")
    public String login(
            @RequestParam @NotNull @Size(max = 32) String identify,
            @RequestParam @NotNull @Size(max = 64) String password) {
        // JWT
        UserDomain userDomain = accountService.login(identify, password);
        return jwtService.createUser(userDomain.getIndex(), userDomain.getIdentify(), userDomain.getName());
    }

}
