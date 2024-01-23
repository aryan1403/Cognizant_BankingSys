package com.cognizant.upiapplication.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cognizant.upiapplication.Models.transaction;

public interface transactionsRepo extends MongoRepository<transaction, String> {

}

