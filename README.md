# Library Management System

A secure RESTful Library Management System built using Spring Boot, Spring Security, JWT Authentication, Spring Data JPA, Hibernate, and MySQL.

The application allows library administrators to manage books, authors, categories, and borrowing records while providing secure authentication and role-based authorization for members and administrators.

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok

---

## Features

### Authentication & Security

- User Registration
- User Login
- JWT Token Authentication
- BCrypt Password Encryption
- Stateless Authentication
- Role-Based Authorization
- Spring Security Integration

---

### Library Management

- Manage Categories
- Manage Authors
- Manage Books
- Update Book Details
- Delete Books
- Increase Available Copies
- Track Total & Available Copies

---

### Borrow Management

- Borrow Books
- Return Books
- Prevent Duplicate Borrow Requests
- Track Issue & Return Dates
- Update Available Copies Automatically

---

### Reports

- View Borrowed Books
- View Available Books

---

## User Roles

### ADMIN

- Full access to the system
- Manage Books
- Manage Categories
- Manage Authors
- View Reports
- Borrow & Return Books

### MEMBER

- Borrow Books
- Return Books
- View Books
- View Authors
- View Categories

### AUTHOR

- Reserved for future enhancements

---

## Project Structure

```
src
 ├── Controller
 ├── DTO
 ├── Entity
 ├── Repository
 ├── Service
 ├── Security
 ├── Configuration
 └── Exception
```

---

## Database Entities

- User
- Author
- Category
- Book
- Borrow

---

## Entity Relationships

| Entity | Relationship |
|----------|-------------|
| Category | One Category → Many Books |
| Author | One Author → Many Books |
| User | One User → Many Borrow Records |
| Book | One Book → Many Borrow Records |

---

# REST APIs

## Authentication

| Method | Endpoint | Access |
|--------|----------|--------|
| POST | /auth/register | Public |
| POST | /auth/login | Public |

---

## Categories

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | /category | Public |
| POST | /category | ADMIN |

---

## Authors

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | /authors | Public |
| GET | /authors/{id}/books | Public |
| POST | /authors | ADMIN |

---

## Books

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | /book | Public |
| POST | /book | ADMIN |
| PUT | /book/{id} | ADMIN |
| DELETE | /book/{id} | ADMIN |
| PUT | /book/{id}/add-copies | ADMIN |

---

## Borrow

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | /borrow | ADMIN |
| POST | /borrow | MEMBER |
| POST | /borrow/returnbook | MEMBER |

---

## Reports

| Method | Endpoint | Access |
|--------|----------|--------|
| GET | /reports/borrowed-books | ADMIN |
| GET | /reports/available-books | ADMIN |

---

## Security

- JWT-based Authentication
- BCrypt Password Hashing
- Stateless Session Management
- Protected REST APIs
- Role-Based Authorization
- Secure Password Storage
- Unauthorized Access Protection

---

## Future Improvements

- Refresh Token
- Pagination & Sorting
- Global Exception Handling
- DTO Mapping
- Validation
- Swagger Documentation
- Email Notifications
- Book Reservation System
- Fine Management
- Docker Support

---

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/LibraryManagement.git
```

### Navigate to the Project

```bash
cd LibraryManagement
```

### Configure MySQL

Update your `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_management
spring.datasource.username=root
spring.datasource.password=your_password

jwt.secret_key=your_secret_key
```

### Run the Application

```bash
mvn spring-boot:run
```

The server will start on:

```
http://localhost:8080
```

---

## Authentication

Protected APIs require a JWT token.

```
Authorization: Bearer <your_jwt_token>
```

---

## License

This project is developed for learning, practice, and portfolio purposes.