package com.dev.progress_tracker.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev.progress_tracker.Entities.User;
import com.dev.progress_tracker.Repositories.userRepository;


@Component
public class userServices {
    @Autowired
    private userRepository userRepo;


    public List<User> getUsers(){

        return userRepo.findAll();
    }






    public User createUser(User user){
        return userRepo.save(user);
    }



    public  User GetUserByUsername(String Username){
        return userRepo.findByUsername(Username);
    }


    


    
}
