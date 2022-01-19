package com.wallet.walletapp.dao;

import com.wallet.walletapp.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("Postgres")
public class AccountDataAccessService implements AccountDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertAccount(UUID id, Account account) {
        final String sql = "INSERT INTO account (id, name, balance) VALUES (?,?,?)";
        return jdbcTemplate.update(sql,id, account.getName(), account.getBalance());
    }

    @Override
    public List<Account> selectAllAccounts() {
        final String sql = "SELECT id, name, balance FROM account";
        return jdbcTemplate.query(sql,(resultSet,i)-> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            double amount = resultSet.getDouble("balance");
            return new Account(id,name,amount);
        });
    }

    @Override
    public Optional<Account> selectAccountById(UUID id) {

        final String sql = "SELECT id, name, balance FROM account WHERE id = ?";
        Account account = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet,i) -> {
            UUID accountId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            double amount = resultSet.getDouble("balance");
            return new Account(accountId,name,amount);
        });
        return Optional.ofNullable(account);
    }

    @Override
    public int deleteAccountById(UUID id) {
        final String sql = "DELETE FROM account WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateAccountById(UUID id, Account account) {
        final String sql = "UPDATE account SET name = ?, balance = ? WHERE id = ?";
        return jdbcTemplate.update(sql,account.getName(), account.getBalance(), id);
    }

    @Override
    public int depositToAccount(UUID id, double depositAmount) {
        Optional<Account> accountMaybe = selectAccountById(id);
        if (accountMaybe.isPresent()) {
            Account account = accountMaybe.get();
            if (account.depositToAccount(depositAmount)) {
                return updateAccountById(id, account);
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    @Override
    public int withdrawalFromAccount(UUID id, double withdrawalAmount) {
        Optional<Account> accountMaybe = selectAccountById(id);
        if (accountMaybe.isPresent()) {
            Account account = accountMaybe.get();
            if (account.withdrawalFromAccount(withdrawalAmount)) {
                return updateAccountById(id, account);
            }else{
                return -1;
            }
        }else{
        return -1;
        }
    }
}
