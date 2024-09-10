package com.dev.progress_tracker.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.progress_tracker.Entities.Progress;
import com.dev.progress_tracker.Services.progressTrackerServices;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/progress")
public class ProgressTrackerController {

    @Autowired
    private progressTrackerServices ProgressService;

    /**
     * Adds new progress for the given user.
     * @param progress The progress data to add.
     * @param username The username to associate with the progress.
     * @return Response with status indicating success or failure.
     */
    @PostMapping("/addprogress/{username}")
    public ResponseEntity<?> takeProgress(@RequestBody Progress progress, @PathVariable String username) {
        try {
            ProgressService.addProgress(progress, username);
            return new ResponseEntity<>("Progress is saved", HttpStatus.CREATED); // Resource created successfully
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all progress entries for a given user.
     * @param username The username to fetch progress for.
     * @return List of progress entries or appropriate error message.
     */
    @GetMapping("/getprogress/{username}")
    public ResponseEntity<?> getProgress(@PathVariable String username) {
        try {
            List<Progress> progressList = ProgressService.getProgress(username);
            if (!progressList.isEmpty()) { // Check if progress entries exist
                return new ResponseEntity<>(progressList, HttpStatus.OK); // Return the list of progress
            } else {
                return new ResponseEntity<>("No progress found", HttpStatus.NOT_FOUND); // No progress found for the user
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Some error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a specific progress entry for a user.
     * @param id The ID of the progress entry to delete.
     * @param username The username associated with the progress.
     * @return Response indicating success or failure of the deletion.
     */
    @DeleteMapping("/deleteprogress/{id}/{username}")
    public ResponseEntity<?> deleteProgress(@PathVariable ObjectId id, @PathVariable String username) {
        try {
            boolean flag = ProgressService.deleteProgress(id, username);
            if (flag) {
                return new ResponseEntity<>("Progress removed successfully", HttpStatus.OK); // Progress deleted
            } else {
                return new ResponseEntity<>("Unable to remove the progress OR Progress not found", HttpStatus.NOT_FOUND); // Progress not found
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing progress entry for a user.
     * @param updatedProgress The updated progress data.
     * @param id The ID of the progress entry to update.
     * @param username The username associated with the progress.
     * @return Response indicating success or failure of the update.
     */
    @PutMapping("/updateprogress/{id}/{username}")
    public ResponseEntity<?> updateProgress(@RequestBody Progress updatedProgress, @PathVariable ObjectId id, @PathVariable String username) {
        try {
            boolean flag = ProgressService.updateProgress(id, updatedProgress, username);
            if (flag) {
                return new ResponseEntity<>("Progress updated successfully", HttpStatus.OK); // Progress updated
            } else {
                return new ResponseEntity<>("Progress not found or already updated", HttpStatus.NOT_FOUND); // Progress not found or no changes made
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
