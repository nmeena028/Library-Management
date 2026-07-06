# Library Management System

A RESTful backend application built using **Spring Boot**, **Spring Data JPA**, **Hibernate**, and **MySQL** for managing a library system.

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

## Features

- Manage Categories
- Manage Authors
- Manage Books
- Register Customers
- Borrow Books
- Return Books
- Track Available Copies
- View Borrowed Books
- View Available Books

---

## Database Entities

- Category
- Author
- Book
- Customer
- Borrow

### Entity Relationships

| Entity | Relationship |
|---------|--------------|
| Category | One Category → Many Books |
| Author | One Author → Many Books |
| Book | One Book → Many Borrow Records |
| Customer | One Customer → Many Borrow Records |

---

## REST APIs

### Category

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/categories` | Add Category |
| GET | `/categories` | Get All Categories |

### Author

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/authors` | Add Author |
| GET | `/authors` | Get All Authors |

### Book

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/books` | Add Book |
| GET | `/books` | Get All Books |
| GET | `/books/{id}` | Get Book By ID |
| PUT | `/books/{id}` | Update Book |
| DELETE | `/books/{id}` | Delete Book |

### Customer

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/customers` | Register Customer |
| GET | `/customers` | Get All Customers |

### Borrow

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/borrow` | Borrow Book |
| POST | `/return` | Return Book |

### Reports

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/borrowed-books` | View Borrowed Books |
| GET | `/available-books` | View Available Books |

---

## Business Rules

- Book name cannot be empty.
- Available copies cannot be negative.
- A customer cannot borrow a book if no copies are available.
- Category must exist before assigning a book.
- Author must exist before assigning a book.
- Returning a book automatically increases available copies.

---

## Project Structure

```
src
├── controller
├── dto
├── entity
├── repository
├── service
└── LibraryManagementApplication.java
```
