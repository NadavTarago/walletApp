package com.wallet.walletapp.service;

import com.wallet.walletapp.dao.AccountDao;
import com.wallet.walletapp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountDao accountDao;

    @Autowired
    public AccountService(@Qualifier("Postgres") AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public int insertAccount(Account account) {
        return accountDao.insertAccount(account);
    }

    public List<Account> getAllAccounts() {
        return accountDao.selectAllAccounts();
    }

    public Optional<Account> getAccountById(UUID id) {
        return accountDao.selectAccountById(id);
    }

    public int deleteAccountById(UUID id) {
        return accountDao.deleteAccountById(id);
    }

    public int updateAccountById(UUID id, Account account) {
        return accountDao.updateAccountById(id, account);
    }

    public int depositToAccount(UUID id, double depositAmount) {
        return accountDao.depositToAccount(id, depositAmount);
    }

    public int withdrawalFromAccount(UUID id, double withdrawalAmount) {
        return accountDao.withdrawalFromAccount(id, withdrawalAmount);
    }
}
