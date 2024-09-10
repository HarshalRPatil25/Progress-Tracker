package com.dev.progress_tracker.Entities;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;
import lombok.Data;

/**
 * User entity representing a system user.
 * It is stored in the MongoDB collection "Users".
 */
@Data
@Document(collection = "Users")
public class User {

    @Id
    private ObjectId userId;  // Unique ID for each user

    @Indexed(unique = true)
    @NonNull
    private String username;  // Username must be unique and is used for login

    @NonNull
    private String password;  // Password for the user, typically stored securely
    
    @DBRef
    private List<Progress> progress = new ArrayList<>();  // List of progress entries associated with the user
}
