package com.dev.progress_tracker.Health;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.progress_tracker.Entities.User;
import com.dev.progress_tracker.Services.userServices;
import org.springframework.web.bind.annotation.GetMapping;


@Component
@RestController
@RequestMapping("/health")

public class HealthCheck {

    @Autowired
    private userServices userService;

    @GetMapping
    public ResponseEntity<?> healthCheck() {
       try{
            List<User> user=userService.getUsers();
            if(user.size()>=0){
                return new ResponseEntity<>("<h2>System Health is good .</br> Users are actives</h2>",HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("<h2>Health is not Good.<br> User is Not Active</h2>",HttpStatusCode.valueOf(501));
            }
       }
       catch(Exception e){

          return new ResponseEntity<>("Some Error is Occured",HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }
    
    
}
