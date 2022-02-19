package com.itmo.utils.Account;

import com.itmo.utils.Bank;
import com.itmo.utils.Client;
import com.itmo.utils.Transaction;

public class AccountCredit extends Account {
    private double limit;

    public AccountCredit(Bank bank, Client client, double limit) {
        super();
        countAccount++;
        this.acctId = "40777" + bank.getMaskAcct() + countAccount;
        this.balance = 0.0;
        if (limit > 0) {
            this.limit = -limit;
        } else {
            this.limit = limit;
        }
        this.bank = bank;
        this.client = client;
        this.isQuestionable = (client.getAddress().isEmpty() || client.getPassport().isEmpty()) ? true : false;
    }

    @Override
    public void transferFunds(Transaction transaction) {
        checkQuestionable();
        double funds = transaction.getFunds();
        double finalFunds = funds + (funds * (bank.getCreditComission() / 100));
        Account receiver = transaction.getReceiver();
        if (isQuestionable) {
            if (funds <= bank.getTransferLimit() && ((balance - finalFunds) >= limit)) {
                balance -= finalFunds;
                receiver.balance += funds;
                transactions.add(transaction);
            } else {
                System.out.println("You can't transfer more funds than limit/balance");
            }
        } else {
            if (((balance - finalFunds) >= limit)) {
                balance -= finalFunds;
                receiver.balance += funds;
                transactions.add(transaction);
            } else {
                System.out.println("You haven't enough cash on your balance");
            }
        }
    }

    @Override
    public void withdrawFunds(double funds) {
        checkQuestionable();
        double finalFunds = funds + (funds * (bank.getCreditComission() / 100));
        if (isQuestionable) {
            if (funds < bank.getWithdrawLimit() && ((balance - finalFunds) >= limit)) {
                balance -= finalFunds;
            } else {
                System.out.println("You can't withdraw more funds than limit/balance");
            }
        } else {
            if ((balance - finalFunds) >= limit) {
                balance -= finalFunds;
            } else {
                System.out.println("You haven't enough cash on your balance");
            }
        }
    }
}
