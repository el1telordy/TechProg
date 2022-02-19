package com.itmo.utils.Account;

import com.itmo.utils.Bank;
import com.itmo.utils.Client;

public class AccountDebet extends Account {

    public AccountDebet(Bank bank, Client client) {
        super();
        countAccount++;
        this.acctId = "40817" + bank.getMaskAcct() + countAccount;
        this.balance = 0.0;
        this.bank = bank;
        this.client = client;
        this.isQuestionable = (client.getAddress().isEmpty() || client.getPassport().isEmpty()) ? true : false;
    }
}
