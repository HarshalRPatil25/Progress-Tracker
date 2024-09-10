package com.dev.progress_tracker.Repositories;




import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dev.progress_tracker.Entities.Progress;

public interface progressTrackerRepository extends MongoRepository<Progress, ObjectId> {
    // List<Progress> getProgressById(ObjectId id);
    // List<Progress> getProgress(String username);
  
}

