package com.wallet.walletapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Account {

    private UUID id;
    private String name;
    private double balance;


    public Account( @JsonProperty("id")  UUID id, @JsonProperty("name") String name,@JsonProperty("balance") double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Account(Account other){
        this.id  = other.getId();
        this.name = other.getName();
        this.balance = other.getBalance();
    }

    public  Account(UUID id,
                     String name){
        this.name = name;
        this.balance = 0.0;
        this.id =  id;
    }

    public boolean depositToAccount(double depositAmount){
        if (depositWithdrawalValidation.isAllowedDeposit(depositAmount)){
            setBalance(getBalance() + depositAmount);
            return true;
        }else{
            return false;
        }
    }

    public boolean withdrawalFromAccount(double withdrawalAmount){
        if (depositWithdrawalValidation.isAllowedWithdrawal(withdrawalAmount,getBalance())){
            setBalance(getBalance() - withdrawalAmount);
            return true;
        } else{
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
