package com.dev.progress_tracker.Repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dev.progress_tracker.Entities.User;

public interface userRepository extends MongoRepository<User,ObjectId> {
    
    public User findByUsername(String username);

}
