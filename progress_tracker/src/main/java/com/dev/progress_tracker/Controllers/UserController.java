package com.dev.progress_tracker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.progress_tracker.Entities.User;
import com.dev.progress_tracker.Services.userServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private userServices userService;

    /**
     * Fetches the list of all users.
     * @return ResponseEntity containing the list of users or an appropriate error message.
     */
    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            List<User> userList = userService.getUsers();
            if (!userList.isEmpty()) {  // Check if user list is not empty
                return new ResponseEntity<>(userList, HttpStatus.OK);  // Return the list of users
            } else {
                return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);  // Return message if no users found
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);  // Handle server errors
        }
    }

    /**
     * Adds a new user.
     * @param user The user data to be added.
     * @return ResponseEntity indicating success or failure.
     */
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            if (createdUser != null) {
                return new ResponseEntity<>("User created: " + createdUser, HttpStatus.CREATED);  // User created successfully
            } else {
                return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_GATEWAY);  // Fallback for unexpected issues
            }
        } catch (InternalError e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Handle internal server errors
        }
    }

    /**
     * Retrieves a user by their username.
     * @param username The username of the user to be fetched.
     * @return ResponseEntity containing the user or an error message.
     */
    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.GetUserByUsername(username);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);  // Return user if found
            } else {
                return new ResponseEntity<>("User not exists", HttpStatus.NOT_FOUND);  // Return error if user not found
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Some error occurred", HttpStatus.NOT_FOUND);  // Handle errors during user retrieval
        }
    }
}
