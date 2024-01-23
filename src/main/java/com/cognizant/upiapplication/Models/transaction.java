package com.cognizant.upiapplication.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "transactions")
public class transaction {
    @Id
    private String transId;

    private String senderId; // sender acc_details
    private String receiverId; // receiver acc_details
    private double amt;
    private String status; // Success / Failed
    private String date; // date & time of transaction
}
