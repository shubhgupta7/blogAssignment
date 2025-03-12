# Blog Management API

This is a Spring Boot REST API for managing blog posts. It allows users to create, read, update, and delete blog entries with pagination and improved response handling using `ResponseEntity`.

## Features
- Create new blog posts
- Retrieve all blog posts (with pagination)
- Fetch individual blog details by ID
- Update existing blog posts
- Delete blog posts

## Technologies Used
- **Spring Boot** for REST API development
- **Spring Data JPA** for database interactions
- **ResponseEntity** for improved HTTP response management

## Endpoints

### Home Endpoint
- **`GET /`**
    - **Response:** `200 OK` with a timestamp message

### Blog Endpoints

- **`POST /addBlogs`**
    - **Description:** Create a new blog entry
    - **Request Parameters:**
        - `title` (String) - Blog title
        - `content` (String) - Blog content
        - `auth` (String) - Blog author
    - **Response:**
        - `200 OK` with the created blog data

- **`GET /getBlogs`**
    - **Description:** Retrieve all blogs (non-paginated)
    - **Response:**
        - `200 OK` with a list of blogs
        - `204 No Content` if no blogs exist

- **`GET /blogs`**
    - **Description:** Retrieve paginated blog entries
    - **Request Parameters:**
        - `page` (default = 0) - Page number
        - `size` (default = 5) - Number of entries per page
    - **Response:**
        - `200 OK` with paginated blog data
        - `204 No Content` if no blogs exist on the requested page

- **`GET /blogs/{id}`**
    - **Description:** Retrieve a blog by its ID
    - **Path Variable:** `id` (Long) - ID of the blog
    - **Response:**
        - `200 OK` with the blog details

- **`PUT /blogs/{id}`**
    - **Description:** Update a blog by ID
    - **Path Variable:** `id` (Long) - ID of the blog
    - **Request Parameters:**
        - `title` (String) - Updated blog title
        - `content` (String) - Updated blog content
        - `auth` (String) - Updated blog author
    - **Response:**
        - `200 OK` with the updated blog data
        - `404 Not Found` if the blog doesn't exist

- **`DELETE /blogs/{id}`**
    - **Description:** Delete a blog by ID
    - **Path Variable:** `id` (Long) - ID of the blog
    - **Response:**
        - `200 OK` with a success message

## Running the Project

1. **Clone the Repository**
   ```bash
   git clone https://github.com/shubhgupta7/blogAssignment.git
   cd blogAssignment
   ```

2. **Build the Project**
   ```bash
   ./mvnw clean install
   ```

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Test the Endpoints**
   Use tools like **Postman**, **Swagger**, or **cURL** to test the API endpoints.

## Sample Data for Testing
**POST /addBlogs**
```json
{
  "title": "Spring Boot Basics",
  "content": "This blog explains the fundamentals of Spring Boot.",
  "auth": "John Doe"
}
```

## Future Improvements
- Implement user authentication
- Add Swagger documentation for API clarity
- Enhance error handling with custom exceptions
- Add unit and integration tests for improved code coverage

## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

## License
This project is open-source and available under the [MIT License](LICENSE).

