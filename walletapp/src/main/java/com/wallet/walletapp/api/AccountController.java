package com.wallet.walletapp.api;

import com.wallet.walletapp.model.Account;
import com.wallet.walletapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public void insertAccount( @RequestBody Account account){
       accountService.insertAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccounts(){
        System.out.println(accountService.getAllAccounts());
        return accountService.getAllAccounts();
    }

    @GetMapping(path = "{id}")
    public Account getAccountById(@PathVariable("id") UUID id){
        return accountService
                .getAccountById(id)
                .orElse(null);
    }

    @PutMapping("/deposit")
    public void depositToAccount(@RequestParam(name = "id") UUID id, @RequestParam(name = "deposit") double depositAmount){
        accountService.depositToAccount(id,depositAmount);
        
    }

    @PutMapping("/withdrawal")
    public void withdrawalFromAccount(@RequestParam(name = "id") UUID id, @RequestParam(name = "withdrawal") double withdrawalAmount){
        accountService.withdrawalFromAccount(id, withdrawalAmount);
    }
}
                                                        