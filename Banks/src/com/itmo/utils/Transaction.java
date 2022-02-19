package com.itmo.utils;

import com.itmo.utils.Account.Account;

public class Transaction {
    protected double funds;
    protected Account receiver;

    public Transaction(double funds, Account receiver) {
        this.funds = funds;
        this.receiver = receiver;
    }

    public double getFunds() {
        return funds;
    }

    public Account getReceiver() {
        return receiver;
    }

    @Override
    public String toString() {
        return "funds: " + funds +
                ", receiver: " + receiver.getAcctId();
    }
}
