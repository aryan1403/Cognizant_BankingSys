package com.cognizant.upiapplication.Controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.upiapplication.Models.response;
import com.cognizant.upiapplication.Models.user;
import com.cognizant.upiapplication.Repositories.userRepo;

@RestController
public class userController {
    @Autowired
    userRepo userdb;

    @GetMapping("/") 
    public String health() {
        return "Application is running/healthy";
    }

    // Get User by id
    @GetMapping("/user/{id}")
    public response getUser(@PathVariable String id) {
        Optional<user> ou = userdb.findUserById(id);

        return ou.isPresent() 
            ? new response(200, "user fetched successfully", ou.get()) 
            : new response(404, "user not found", null);
    }

    // create user
    @PostMapping("/user/createUser")
    public response createUser(@RequestBody user body) {
        String uid = UUID.randomUUID().toString();
        body.setId(uid);
        user savedUser = userdb.save(body);
        return new response(201, "user saved successfully", savedUser);
    }

    // update user 
    // TODO: fix this later on
    @PostMapping("/user/{id}")
    public response updateUser(@RequestBody user body) {
        user updatedUser = userdb.save(body);
        return new response(200, "user updated successfully", updatedUser);
    }

    // delete user
    @DeleteMapping("/user/{id}")
    public response deleteUser(@PathVariable String id) {
        userdb.deleteById(id);
        return new response(200, "user deleted successfully", null);
    }

    // get User Accounts
    @GetMapping("/user/acc/{id}")
    public response getUserAccounts(@PathVariable String id) {
        Optional<user> u = userdb.findById(id);
        return u.isPresent() 
            ? new response(200, "User Accounts fetched successfully", u.get().getUserAcc())
            : new response(404, "User not found", null);
    }
}
