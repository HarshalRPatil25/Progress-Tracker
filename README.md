# Progress-Tracker
Developed a Spring Boot backend for a Progress Tracker with MongoDB integration for efficient data management. The application features RESTful APIs for CRUD operations and secure user management, enhancing data access and control. It improves user experience and productivity with scalable, streamlined progress tracking.


# Features
CRUD Operations: Allows Create, Read, Update, and Delete operations on user progress entries.
User Management: Supports user creation and retrieval based on username.
Progress Tracking: Enables tracking of progress entries for individual users, including details such as title and hours logged.
Error Handling: Provides meaningful error responses and status codes for various operations.
Data Persistence: Uses MongoDB to persist user and progress data.

# Technologies Used

Spring Boot: A Java-based framework used to build the backend application with ease of setup and configuration.
MongoDB: A NoSQL database used for storing user and progress data.
Lombok: A library used to reduce boilerplate code in entity classes.


# API Endpoints
User Management
Create User

# POST /users

Request Body: User object with username and password
Response: Status message indicating whether the user was created successfully or if there was an error.
Get Users

GET /users
Response: List of all users or a message indicating no users found.
Get User by Username

GET /users/{username}
Response: User object if found, or a message indicating user does not exist.
Progress Management
Add Progress

# POST /progress/addprogress/{username}
Request Body: Progress object with title and hrs
Response: Status message indicating whether the progress was saved successfully or if there was an error.
Get Progress

GET /progress/getprogress/{username}
Response: List of progress entries for the specified user or a message indicating no progress found.
Update Progress

PUT /progress/updateprogress/{id}/{username}
Request Body: Updated Progress object with title and hrs
Response: Status message indicating whether the progress was updated successfully or if there was an error.
Delete Progress

DELETE /progress/deleteprogress/{id}/{username}
Response: Status message indicating whether the progress was removed successfully or if there was an error.


## Installation and Setup
 
 ### Clone the Repository

```
git clone https://github.com/yourusername/progress-tracker.git

```

### Navigate to the Project Directory

```
cd progress-tracker

```

### Install Dependencies Use Maven or Gradle to install the required dependencies:

```
mvn install  # or ./gradlew build

```

Configure MongoDB Ensure MongoDB is installed and running. Configure the connection details in the application.properties or application.yml file as needed.

### Run the Application


```mvn spring-boot:run  # or ./gradlew bootRun```

### Configuration
Database Configuration: Set up MongoDB connection details in the src/main/resources/application.properties or application.yml file.
Security Configuration: Adjust security settings as needed based on your requirements.
