package com.itmo.utils;

import com.itmo.utils.Account.Account;
import com.itmo.utils.Account.AccountCredit;
import com.itmo.utils.Account.AccountDebet;
import com.itmo.utils.Account.AccountDepos;

import java.text.DecimalFormat;

public class CalculateInterestRates {
    public static double calculateRatesForDay(Account account) {
        DecimalFormat df = new DecimalFormat("#.##");
        if (account instanceof AccountCredit) {
            return 0;
        } else if (account instanceof AccountDepos) {
            //add returning balance by every variant

            if (account.getBalance() >= (account.getBank().getInterestRates().get(0).getMinSum()) && account.getBalance()
                    < (account.getBank().getInterestRates().get(0).getMaxSum())) {
                return Double.parseDouble(df.format(((AccountDepos) account).getDailyRate(0)));
            } else if (account.getBalance() >= (account.getBank().getInterestRates().get(1).getMinSum()) && account.getBalance()
                    < (account.getBank().getInterestRates().get(1).getMaxSum())) {
                return Double.parseDouble(df.format(((AccountDepos) account).getDailyRate(1)));
            } else if (account.getBalance() >= (account.getBank().getInterestRates().get(2).getMinSum())) {
                if ((account.getBank().getInterestRates().get(2).getMaxSum()) == 0) {
                    return Double.parseDouble(df.format(((AccountDepos) account).getDailyRate(2)));
                } else if (account.getBalance() < (account.getBank().getInterestRates().get(2).getMaxSum())) {
                    return Double.parseDouble(df.format(((AccountDepos) account).getDailyRate(2)));
                }
            }
        } else if (account instanceof AccountDebet) {
            return Double.parseDouble(df.format((account.getBalance() * ((account.getBank().getRateOfBalance()/365)/100))));
        }
        return 0;
    }
}
