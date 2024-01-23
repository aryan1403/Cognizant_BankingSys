package com.cognizant.upiapplication.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cognizant.upiapplication.Models.acc;
import com.cognizant.upiapplication.Models.transaction;
import com.cognizant.upiapplication.Repositories.accRepo;
import com.cognizant.upiapplication.Repositories.transactionsRepo;
import com.cognizant.upiapplication.Repositories.userRepo;

public class transactionHelper {
    public boolean checkSenderReceiver(String senderId, String receriverId, userRepo userDb) {
        // TODO: check the users existance
        return true;
    }

    public boolean checkAmt(String senderId, double amt, accRepo accDb) {
        // TODO: check the amt for the user --> balance >= amt
        return true;
    }

    @Transactional
    public transaction sendMoneyToReceiver(transaction t, userRepo userDb, accRepo accDb,  transactionsRepo transDb) {
        String senderId = t.getSenderId();
        String receriverId = t.getReceiverId();
        double amt = t.getAmt();

        acc senderAcc = accDb.findById(userDb.findById(senderId).get().getUserAcc().get(0)).get();
        acc receiverAcc = accDb.findById(userDb.findById(receriverId).get().getUserAcc().get(0)).get();

        final double senderBalance = senderAcc.getBalance();
        final double receiverBalance = senderAcc.getBalance();

        senderAcc.setBalance(senderBalance - amt); // amt has deducted from sender
        receiverAcc.setBalance(receiverBalance + amt); // receiver has added the amt

        List<acc> acclist = new ArrayList<>();
        acclist.add(senderAcc);
        acclist.add(receiverAcc);

        List<acc> a = accDb.saveAll(acclist); // update the balance in the acc.

        return a.get(0) != null && a.get(1) != null ? transDb.save(t) : null;
    }
}
