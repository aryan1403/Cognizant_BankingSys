package com.cognizant.upiapplication.Models;

import lombok.*;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users") // mongodb document
@NoArgsConstructor
public class user {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String addr;
    private String status;
    private List<String> userAcc;
    private String createdAt;
    private String updatedAt;
}
