# PostSphere — Blog Management System

A backend REST API blog platform built with Java, Spring Boot, and MySQL.

## Tech Stack
- Java 17
- Spring Boot 3.2
- Spring Security + JWT
- JPA / Hibernate
- MySQL
- Lombok
- ModelMapper
- Maven

## How to run locally

1. Make sure MySQL is running on your machine
2. Open `src/main/resources/application.properties`
3. Update your DB password:
   ```
   spring.datasource.password=yourpassword
   ```
4. Run:
   ```bash
   mvn spring-boot:run
   ```
5. Server starts at: `http://localhost:8080`

## API Endpoints

### Auth (no token needed)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register new user |
| POST | /api/auth/login | Login — returns JWT token |

### Posts (add `Authorization: Bearer <token>` header)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/posts/user/{userId} | Create post |
| GET | /api/posts | Get all posts (paginated) |
| GET | /api/posts/{postId} | Get single post |
| PUT | /api/posts/{postId} | Update post |
| DELETE | /api/posts/{postId} | Delete post |
| POST | /api/posts/{postId}/image | Upload image |

### Comments (add `Authorization: Bearer <token>` header)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/comments/post/{postId}/user/{userId} | Add comment |
| GET | /api/comments/post/{postId} | Get comments for a post |
| DELETE | /api/comments/{commentId} | Delete comment |

## Testing with Postman
1. Call `/api/auth/register` to create a user
2. Call `/api/auth/login` → copy the token from response
3. For all other requests, add header: `Authorization: Bearer <your_token>`
