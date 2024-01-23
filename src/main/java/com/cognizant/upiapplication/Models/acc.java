package com.cognizant.upiapplication.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Document(collection = "accounts") // mongodb document
public class acc {
    @Id
    private String id;

    private String accNumber;
    private double balance;
    private String status;
    private String createdAt;
    private String updatedAt;
}
