package com.itmo.utils.Account;

import com.itmo.utils.Bank;
import com.itmo.utils.CalculateInterestRates;
import com.itmo.utils.Client;
import com.itmo.utils.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Account {
    protected static int countAccount = 0;
    protected String acctId;
    protected double balance;
    protected Client client;
    protected Bank bank;
    protected boolean isQuestionable;
    protected double interestRatesCache;
    //here we should have list of (amountOfMoney, receiver) so
    // then we will able to return money to account and delete money from receiver account.
    // after that delete last Array element

    List<Transaction> transactions = new ArrayList<>();

    public void checkQuestionable() {
        this.isQuestionable = (client.getAddress().isEmpty() || client.getPassport().isEmpty()) ? true : false;
    }

    //Transactions
    public void addFunds(double funds) {
        checkQuestionable();
        if (isQuestionable) {
            if (funds <= bank.getAddFundsLimit()) {
                balance += funds;
            } else {
                System.out.println("You can't add more funds than limit");
            }
        } else {
            balance += funds;
        }
    }

    public void withdrawFunds(double funds) {
        checkQuestionable();
        if (isQuestionable) {
            if (funds <= bank.getWithdrawLimit() && funds <= balance) {
                balance -= funds;
            } else {
                System.out.println("You can't withdraw more funds than limit/balance");
            }
        } else {
            if (funds <= balance) {
                balance -= funds;
            } else {
                System.out.println("You haven't enough cash on your balance");
            }
        }
    }

    public void transferFunds(Transaction transaction) {
        checkQuestionable();
        double funds = transaction.getFunds();
        Account receiver = transaction.getReceiver();
        if (isQuestionable) {
            if (funds <= bank.getTransferLimit() && funds <= balance) {
                balance -= funds;
                receiver.balance += funds;
                transactions.add(transaction);
            } else {
                System.out.println("You can't transfer more funds than limit/balance");
            }
        } else {
            if (funds <= balance) {
                balance -= funds;
                receiver.balance += funds;
                transactions.add(transaction);
            } else {
                System.out.println("You haven't enough cash on your balance");
            }
        }
    }

    public void cancelLastTransaction() {
        Transaction latest = transactions.get(transactions.size() - 1);
        Account receiver = latest.getReceiver();
        double funds = latest.getFunds();
        if (funds < receiver.balance) {
            receiver.balance -= funds;
            balance += funds;
            transactions.remove(latest);
        } else {
            System.out.println("Looks like receiver withdraw all cash and you're lost your money! Oops...");
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getAcctId() {
        return acctId;
    }

    public double getBalance() {
        return balance;
    }

    public Client getClient() {
        return client;
    }

    public Bank getBank() {
        return bank;
    }

    public void skipTime(LocalDate futureDate) {
        LocalDate startDate = LocalDate.now();
        int days = (int) ChronoUnit.DAYS.between(startDate, futureDate);
        int months = days/30;
        int otherDays = days - months*30;
        for(int i = 0; i < months; ++i) {
            addInterestRatesForOneMonth();
        }
        for (int i = 0; i < otherDays; ++i) {
            addInterestRatesForOneDay();
        }
    }

    private void addInterestRatesForOneDay() {
        interestRatesCache += CalculateInterestRates.calculateRatesForDay(this);
    }

    private void addInterestRatesForOneMonth() {
        for (int i = 0; i < 30; ++i) {
            addInterestRatesForOneDay();
        }
        balance += interestRatesCache;
        interestRatesCache = 0;
    }
}
