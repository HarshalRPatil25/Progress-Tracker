package com.dev.progress_tracker.Entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.micrometer.common.lang.NonNull;
import lombok.Data;

/**
 * Progress entity representing a user's progress data.
 * It is stored in the MongoDB collection "ProgressData".
 */
@Data
@Document(collection = "ProgressData")
public class Progress {

    @Id
    private ObjectId progressID;  // Unique ID for each progress entry
    
    @NonNull
    private String title;  // Title of the progress entry
    
    @NonNull
    private int hrs;  // Number of hours spent or logged in the progress
}
