package com.cognizant.upiapplication.Repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cognizant.upiapplication.Models.user;

public interface userRepo extends MongoRepository<user, String> {
    public Optional<user> findUserById(String id);
}
