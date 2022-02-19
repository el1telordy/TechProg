package com.itmo.utils;

import com.itmo.utils.Account.Account;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<InterestRates> interestRates = new ArrayList<>(3);
    private double rateOfBalance;
    private double creditComission;

    public double withdrawLimit;
    public double transferLimit;
    public double addFundsLimit;

    private String maskAcct;

    public List<InterestRates> getInterestRates() {
        return interestRates;
    }

    public void setInterestRates(List<InterestRates> interestRates) {
        int i = interestRates.size();
        while (i > 3) {
            interestRates.remove(interestRates.size() - 1);
            i = interestRates.size();
        }
        this.interestRates = interestRates;
    }

    public double getRateOfBalance() {
        return rateOfBalance;
    }

    public void setRateOfBalance(double rateOfBalance) {
        this.rateOfBalance = rateOfBalance;
    }

    public String getMaskAcct() {
        return maskAcct;
    }

    public void setMaskAcct(String maskAcct) {
        this.maskAcct = maskAcct;
    }

    public double getCreditComission() {
        return creditComission;
    }

    public void setCreditComission(double creditComission) {
        this.creditComission = creditComission;
    }

    public Bank setAddFundsLimit(double addFundsLimit) {
        this.addFundsLimit = addFundsLimit;
        return this;
    }

    public void setTransferLimit(double transferLimit) {
        this.transferLimit = transferLimit;
    }

    public Bank setWithdrawLimit(double withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
        return this;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public double getTransferLimit() {
        return transferLimit;
    }

    public double getAddFundsLimit() {
        return addFundsLimit;
    }
}
