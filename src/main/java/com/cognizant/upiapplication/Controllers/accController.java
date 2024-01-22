package com.cognizant.upiapplication.Controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.cognizant.upiapplication.Models.acc;
import com.cognizant.upiapplication.Models.linkUnlink;
import com.cognizant.upiapplication.Models.response;
import com.cognizant.upiapplication.Models.user;
import com.cognizant.upiapplication.Repositories.accRepo;
import com.cognizant.upiapplication.Repositories.userRepo;

public class accController {
    @Autowired
    accRepo accDb;

    @Autowired
    userRepo userDb;
    
    // TODO: add response returns
    @PostMapping("/acc/create")
    public response createAcc(@RequestBody acc accBody) {
        accDb.save(accBody);
        return null;
    }

    // TODO: add response returns
    @PostMapping("/acc/update")
    public response updateAcc(@RequestBody acc accBody) {
        accDb.save(accBody);
        return null;
    }

    // TODO: add response returns
    @DeleteMapping("/acc/delete/{id}")
    public response deleteAcc(@PathVariable String id) {
        accDb.deleteById(id);
        return null;
    }

    // TODO: add response returns
    @GetMapping("/acc/{id}")
    public response getAcc(@PathVariable String id) {
        accDb.findById(id);
        return null;
    }

    // TODO: add response returns
    @PostMapping("/acc/link")
    public response getAcc(@RequestBody linkUnlink link) {
        String userId = link.getUserId();
        String accId = link.getAccId();

        Optional<user> u = userDb.findById(userId);

        if(u.isPresent()) {
            user linkedUser = u.stream().map(e -> {
                List<String> l = e.getUserAcc();
                l.add(accId);
                e.setUserAcc(l);
                return e;
            }).findFirst().get();

            // update
            userDb.save(linkedUser);
        } else {
            return null;
        }
        return null;
    }
}
