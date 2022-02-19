package com.itmo.utils.Account;

import com.itmo.utils.Bank;
import com.itmo.utils.Client;
import com.itmo.utils.Transaction;

import java.time.LocalDate;

public class AccountDepos extends Account {
    private LocalDate open;
    private LocalDate close;

    public AccountDepos(Bank bank, Client client, LocalDate open, LocalDate close, double summ) {
        super();
        countAccount++;
        this.acctId = "40823" + bank.getMaskAcct() + countAccount;
        this.balance = summ;
        this.bank = bank;
        this.client = client;
        this.isQuestionable = (client.getAddress().isEmpty() || client.getPassport().isEmpty()) ? true : false;
        this.open = open;
        this.close = close;
    }

    public double getDailyRate(int i) {
        return getBalance() * getBank().getInterestRates().get(i).getRate()/365/100;
    }

    @Override
    public void withdrawFunds(double funds) {
        if (LocalDate.now().isAfter(close)) {
            super.withdrawFunds(funds);
        } else {
            System.out.println("Please wait until your account will be closed");
        }
    }

    @Override
    public void transferFunds(Transaction transaction) {
        if (LocalDate.now().isAfter(close)) {
            super.transferFunds(transaction);
        } else {
            System.out.println("Please wait until your account will be closed");
        }
    }
}
