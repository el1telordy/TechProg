package com.itmo;

import com.itmo.utils.*;
import com.itmo.utils.Account.AccountCredit;
import com.itmo.utils.Account.AccountDebet;
import com.itmo.utils.Account.AccountDepos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        //Setting up banks
        Bank sber = new Bank();
        Bank vtb = new Bank();

        sber.setRateOfBalance(1.5);
        sber.setCreditComission(10.0);
        sber.setAddFundsLimit(50000).setWithdrawLimit(200000).setTransferLimit(500000);

        List<InterestRates> ratesForSber = new ArrayList<>();
        ratesForSber.add(new InterestRates(0, 100000, 2.0));
        ratesForSber.add(new InterestRates(100000, 200000, 3.0));
        ratesForSber.add(new InterestRates(200000, 0, 4.0));

        sber.setInterestRates(ratesForSber);

        sber.setMaskAcct("5555");

        vtb.setRateOfBalance(2.5);
        vtb.setCreditComission(7.0);
        vtb.setAddFundsLimit(80000).setWithdrawLimit(500000).setTransferLimit(8000000);

        List<InterestRates> ratesForVtb = new ArrayList<>();
        ratesForVtb.add(new InterestRates(0, 150000, 2.5));
        ratesForVtb.add(new InterestRates(150000, 300000, 3.5));
        ratesForVtb.add(new InterestRates(300000, 0, 5.6));
        vtb.setInterestRates(ratesForVtb);

        vtb.setMaskAcct("5500");

        //Setting up clients
        Client client1 = new Client("Michael", "Stevens");
        Client client2 = new Client("Linus", "Sebastian", "Canada, Vancoover, Central St. 4", "4869");

        client1.setAddress("USA, Los Angeles");

        //Setting up accounts (client/bank pair)
        AccountDebet accountDebt1 = new AccountDebet(sber, client1);
        AccountDepos accountDepos2 = new AccountDepos(vtb,
                client1, LocalDate.of(2020, 12, 12),
                LocalDate.of(2021, 12, 12),
                10000);
        AccountCredit accountCredit3 = new AccountCredit(sber, client2, 100000.0);

        accountDebt1.addFunds(90000); //must be declined

        client1.setPassport("78420");

        accountDebt1.addFunds(87654); //must be accepted
        accountDebt1.withdrawFunds(10000);
        accountDebt1.transferFunds(new Transaction(20000, accountDepos2));

        System.out.println("Debt1 balance after transaction " + accountDebt1.getBalance());
        System.out.println("Depos2 balance after transaction " + accountDepos2.getBalance());

        System.out.println("Debt1 transactions after transaction " + accountDebt1.getTransactions());

        accountDepos2.withdrawFunds(30000);
        accountDebt1.cancelLastTransaction();

        System.out.println("Debt1 balance after cancelling " + accountDebt1.getBalance());
        System.out.println("Depos2 balance after cancelling " + accountDepos2.getBalance());

        System.out.println("Debt1 transactions after cancelling " + accountDebt1.getTransactions());

        System.out.println(accountDebt1.getClient());
        System.out.println();

        accountDepos2.addFunds(300000);
        accountDepos2.skipTime(LocalDate.of(2022, 4, 2));
        System.out.println("Depos2 balance after time " + accountDepos2.getBalance());
    }
}
