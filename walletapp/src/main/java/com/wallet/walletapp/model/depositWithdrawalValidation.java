package com.wallet.walletapp.model;

public class depositWithdrawalValidation {

    public static final double MAX_DEPOSIT_ALLOWED = 100000;
    public static final double MAX_WITHDRAWAL_ALLOWED = 50000;
    public static final double MAX_OVERDRAFT_ALLOWED = 0;

    public static boolean isAllowedDeposit(double deposit){
        return ((deposit > 0) && (deposit < MAX_DEPOSIT_ALLOWED));
        }

    public static boolean isAllowedWithdrawal(double withdrawal, double balance){
        return ((withdrawal > 0) && (withdrawal<MAX_WITHDRAWAL_ALLOWED) && (balance-withdrawal>=MAX_OVERDRAFT_ALLOWED));
    }

}
