package com.dev.progress_tracker.Services;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dev.progress_tracker.Entities.User;
import com.dev.progress_tracker.Entities.Progress;
import com.dev.progress_tracker.Repositories.progressTrackerRepository;

@Component
public class progressTrackerServices {

    @Autowired 
    private progressTrackerRepository trackerRepo;

    @Autowired
    private userServices userService;

    // Add new progress and associate with a user
    public Progress addProgress(Progress progress, String username) {
        Progress savedProgress = trackerRepo.save(progress);
        User userData = userService.GetUserByUsername(username);
        
        // Null check for user data
        if (userData != null) {
            // Add the saved progress to the user's progress list
            userData.getProgress().add(savedProgress);
            userService.createUser(userData);  // Save the updated user data
        }
        
        return savedProgress;
    }

    // Get all progress for a user
    public List<Progress> getProgress(String username) {
        User userData = userService.GetUserByUsername(username);
        
        if (userData != null) {
            return userData.getProgress();  // Return user's progress list
        }
        
        return null;  // Return null if user not found
    }

    // Delete progress by ID and username
    public boolean deleteProgress(ObjectId id, String username) {
        boolean flag = false;
        User currentUser = userService.GetUserByUsername(username);
        
        if (currentUser != null) {
            List<Progress> progressList = currentUser.getProgress();
            
            Progress progressToRemove = null;
            for (Progress progress : progressList) {
                if (progress.getProgressID().equals(id)) {
                    progressToRemove = progress;
                    break;
                }
            }
    
            if (progressToRemove != null) {
                progressList.remove(progressToRemove);  // Remove the progress entry
                userService.createUser(currentUser);    // Update the user with the new progress list
                trackerRepo.deleteById(id);  // Delete the progress from the trackerRepo
                flag = true;
            }
        }
        
        return flag;  // Return true if the deletion was successful
    }

    // Update progress by ID and username
    public boolean updateProgress(ObjectId id, Progress updatedProgress, String username) {
        boolean flag = false;
        
        // Find the user by username
        User currentUser = userService.GetUserByUsername(username);
        
        if (currentUser != null) {
            List<Progress> progressList = currentUser.getProgress();
            
            // Find the progress item that matches the given ID
            for (Progress existingProgress : progressList) {
                if (existingProgress.getProgressID().equals(id)) {
                    // Check for changes and update fields if necessary
                    boolean isUpdated = false;
                    
                    if (!existingProgress.getTitle().equals(updatedProgress.getTitle())) {
                        existingProgress.setTitle(updatedProgress.getTitle());
                        isUpdated = true;
                    }

                    if (existingProgress.getHrs()!=(updatedProgress.getHrs())) {
                        existingProgress.setHrs(updatedProgress.getHrs());
                        isUpdated = true;
                    }
                    
                    // Add other fields that need to be updated as per your Progress model
                    
                    if (isUpdated) {
                        // Save the updated progress in the trackerRepo
                        trackerRepo.save(existingProgress);
                        
                        // Save the updated user in userService
                        userService.createUser(currentUser);
                        
                        flag = true;
                    }
                    break;  // Exit the loop after finding and updating the matching progress
                }
            }
        }
        
        return flag;  // Return true if the update was successful
    }
}
