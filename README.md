# Project Name
Blogging API

# Frameworks and Language Used
**Spring Boot** 2.7.0
**Java** 11.0
**Maven** 3.8.1

# API Documentation

 **UserController**

  Sign Up

  Endpoint: /blog/signup
  Method: POST
  Request Body: SignUpInputDto
  Description: Creates a new user account.
  Response: SignUpOutputDto
  HTTP Status Codes:
  200 (OK) - User account successfully created.
  400 (Bad Request) - Invalid request or missing required fields.

  Sign In

  Endpoint: /blog/signin
  Method: POST
  Request Body: SignInInputDto
  Description: Authenticates and signs in a user.
  Response: SignInOutputDto
  HTTP Status Codes:
  200 (OK) - User successfully signed in.
  401 (Unauthorized) - Invalid credentials.
  
  
  Follow User

  Endpoint: /blog/follow/{email}/{token}/{otherId}
  Method: POST
  Description: Allows a user to follow another user.
  Request Parameters:
  email: Email of the authenticated user.
  token: Authentication token of the user.
  otherId: ID of the user to be followed.
  Response: String
  HTTP Status Codes:
  200 (OK) - User successfully followed.
  400 (Bad Request) - Error occurred while following the user.
  403 (Forbidden) - Invalid user or authentication failed.
  
  
**PostController**

Add Post

  Endpoint: /post/add/{email}/{token}
  Method: POST
  Request Body: BlogPost
  Description: Creates a new blog post.
  Request Parameters:
  email: Email of the authenticated user.
  token: Authentication token of the user.
  Response: String
  HTTP Status Codes:
  200 (OK) - Post created successfully.
  403 (Forbidden) - Invalid user or authentication failed.
  
Get All Posts

  Endpoint: /post/all/{email}/{token}
  Method: GET
  Description: Retrieves all blog posts.
  Request Parameters:
  email: Email of the authenticated user.
  token: Authentication token of the user.
  Response: List of BlogPost
  HTTP Status Codes:
  200 (OK) - Posts fetched successfully.
  403 (Forbidden) - Invalid user or authentication failed.
  
Update Post Data

  Endpoint: /post/update/{postId}/{data}/{email}/{token}
  Method: PUT
  Description: Updates the data of a blog post.
  Request Parameters:
  postId: ID of the post to be updated.
  data: New data for the post.
  email: Email of the authenticated user.
  token: Authentication token of the user.
  Response: String
  HTTP Status Codes:
  200 (OK) - Post data updated successfully.
  403 (Forbidden) - Invalid user or authentication failed.
  
Delete Post by ID

  Endpoint: /post/delete/{postId}/{email}/{token}
  Method: DELETE
  Description: Deletes a blog post by its ID.
  Request Parameters:
  postId: ID of the post to be deleted.
  email: Email of the authenticated user.
  token: Authentication token of the user.
  Response: String
  HTTP Status Codes:
  200 (OK) - Post deleted successfully.
  403 (Forbidden) - Invalid user or authentication failed.
  
**CommentController**

  Add Comment

  Endpoint: /comment/add/{email}/{token}
  Method: POST
  Request Body:

# Data Flow

The following functions are used in the data flow of this project:

## Models

  **BlogUser**:

  Represents a user in the blogging system.
  Contains properties such as user ID, first name, last name, username, mobile number, email, bio, password, date of birth, and registration date.
  It is associated with BlogPosts, BlogFollowers, and BlogFollowings through relationships.

  **BlogPost**:

  Represents a blog post in the system.
  Contains properties such as post ID, post date, post data, post caption.
  It is associated with a BlogUser through a many-to-one relationship.

  **BlogFollowing**:

  Represents the relationship between a user and the users they are following.
  Contains properties such as following table ID and references to the user and the following user.

  **BlogFollower**:

  Represents the relationship between a user and their followers.
  Contains properties such as follower table ID and references to the user and the follower.

  **BlogComment**:

  Represents a comment on a blog post.
  Contains properties such as comment ID, comment date, comment body, and references to the associated BlogPost and BlogUser.

  **AuthenticationToken**:

  Represents an authentication token associated with a user.
  Contains properties such as authentication token ID, authentication token value, authentication token date, and references to the associated BlogUser.

## Controller

  **UserController:**

  Handles user-related operations such as user signup and user signin.
  Provides endpoints for signing up a new user and signing in an existing user.
  Uses UserService and AuthenticationService for user-related operations.

  **PostController:**

  Manages blog post-related operations.
  Offers endpoints for creating a new blog post, fetching all posts, updating post data, and deleting a post by its ID.
  Utilizes AuthenticationService and PostService for post-related operations.

  **CommentController:**

  Handles comment-related operations for blog posts.
  Provides an endpoint for adding a comment to a blog post.
  Relies on AuthenticationService and CommentService for comment-related operations.


## Services

  **UserService:**
  Handles user-related operations such as user signup and sign-in. It utilizes the IUserRepo repository for user data storage.

  **PostService:**
  Manages blog post-related operations, including adding a new post, fetching all posts, updating post data, and deleting a post. It interacts with the IPostRepo repository for post data persistence.

  **FollowingService:** 
  Provides functionality to save a user's following relationship with other users. It uses the IFollowingRepo repository for storing following information.

  **FollowerService:** 
  Handles the saving of a user's followers. It interacts with the IFollowerRepo repository for follower data storage.

  **CommentService:** 
  Manages operations related to comments on blog posts. It utilizes the ICommentRepo repository for comment data persistence.

  **AuthenticationService:** 
  Handles user authentication and token-based authentication checks. It interacts with the IAuthenticationRepo repository for authentication-related data storage.


_**Repository:**_ The repository layer is responsible for interacting with the database. It uses Spring Data JPA to perform CRUD (create, read, update, delete) operations on entities.

  **IAuthenticationRepo:** 
  Responsible for storing and retrieving AuthenticationToken entities. It extends the JpaRepository interface and provides a method to find an authentication token by its value.

  **ICommentRepo:** 
  Handles the persistence of BlogComment entities. It extends the JpaRepository interface and provides default CRUD operations for comments.

  **IFollowerRepo:** 
  Manages the storage and retrieval of BlogFollower entities, representing user followers. It extends the JpaRepository interface.

  **IFollowingRepo:** 
  Handles the persistence of BlogFollowing entities, representing user followings. It extends the JpaRepository interface.

  **IPostRepo:** 
  Manages the storage and retrieval of BlogPost entities, representing blog posts. It extends the JpaRepository interface and provides default CRUD operations for posts.

  **IUserRepo:** 
  Responsible for storing and retrieving BlogUser entities, representing blog users. It extends the JpaRepository interface and provides methods for checking user existence by email, finding a user by email, and finding a user by ID.

# Database Structure Used
I have used MySql as Database.

# Project Summary

This is an ecommerce application that allow user to place order and having differnt fetures also



