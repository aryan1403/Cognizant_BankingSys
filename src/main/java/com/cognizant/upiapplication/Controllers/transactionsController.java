package com.cognizant.upiapplication.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.upiapplication.Helpers.transactionHelper;
import com.cognizant.upiapplication.Models.response;
import com.cognizant.upiapplication.Models.transaction;
import com.cognizant.upiapplication.Repositories.accRepo;
import com.cognizant.upiapplication.Repositories.transactionsRepo;
import com.cognizant.upiapplication.Repositories.userRepo;

@RestController
public class transactionsController {
    @Autowired
    transactionsRepo transDb;

    @Autowired
    userRepo userDb;

    @Autowired
    accRepo accDb;


    @PostMapping("/transaction/send")
    public response sendMoney(@RequestBody transaction t) {
        final String tId = UUID.randomUUID().toString();
        t.setTransId(tId);

        // Verify sender & receiver --> exists 
        transactionHelper tHelper = new transactionHelper();
        boolean isExist = tHelper.checkSenderReceiver(t.getSenderId(), t.getReceiverId(), userDb);

        // Check the balance --> balance >= amt
        if(isExist && tHelper.checkAmt(t.getSenderId(), t.getAmt(), accDb)) {
            // transaction
            transaction isTransaction = tHelper.sendMoneyToReceiver(t, userDb, accDb, transDb);

            return isTransaction != null
                ? new response(200, "Transaction successfull", isTransaction)
                : new response(500, "Transaction failed", null);
        } else {
            return !isExist 
                ? new response(404, "User not found", null) 
                : new response(404, "Insufficient balance", null);
        }
    }
}
