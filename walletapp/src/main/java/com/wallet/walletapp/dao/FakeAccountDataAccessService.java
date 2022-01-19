package com.wallet.walletapp.dao;

import com.wallet.walletapp.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeAccountDataAccessService implements AccountDao {

    private  static List<Account> DB = new ArrayList<>();

    @Override
    public int insertAccount(UUID id, Account account) {
        DB.add(new Account(id,account.getName(),account.getBalance()));
        return 1;
    }

    @Override
    public List<Account> selectAllAccounts() {
        return DB;
    }

    @Override
    public Optional<Account> selectAccountById(UUID id) {
        return DB.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteAccountById(UUID id) {
        Optional<Account> accountExist = selectAccountById(id);
        if (accountExist.isPresent()){
            DB.remove(accountExist.get());
            return 1;
        }else { // account not exist
            return 0;
        }
    }

    @Override
    public int updateAccountById(UUID id, Account update) {
        return selectAccountById(id)
                .map(account -> {
                    int indexOfAccountToUpdate = DB.indexOf(account);
                    if (indexOfAccountToUpdate >= 0){
                        DB.set(indexOfAccountToUpdate,new Account(id,update.getName(), account.getBalance()));
                        return 1;
                    }else{
                        return 0;
                    }
                })
                .orElse(0);
    }

    @Override
    public int depositToAccount(UUID id, double depositAmount) {
        return selectAccountById(id)
                .map(account -> {
                    int indexOfAccountToDeposit = DB.indexOf(account);
                    if (indexOfAccountToDeposit >= 0){
                        if( DB.get(indexOfAccountToDeposit).depositToAccount(depositAmount)){
                            return 1;
                        }
                    }else{
                        return 0;
                    }
                    return null;
                })
                .orElse(0);
    }

    @Override
    public int withdrawalFromAccount(UUID id, double withdrawalAmount) {
        return selectAccountById(id)
                .map(account -> {
                    int indexOfAccountToDeposit = DB.indexOf(account);
                    if (indexOfAccountToDeposit >= 0){
                        if (DB.get(indexOfAccountToDeposit).withdrawalFromAccount(withdrawalAmount)){
                            return 1;
                        }
                    }else{
                        return 0;
                    }
                    return null;
                })
                .orElse(0);
    }
}
