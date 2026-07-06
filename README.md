Library Management System (Spring Boot Backend)

This is a backend system for managing library operations. It allows management of books, authors, categories, customers, and book borrowing/returning functionality. The system is built using Spring Boot, Spring Data JPA, Hibernate, and MySQL.

Project Overview

The system is designed to replace a manual register-based library process with a digital REST API backend. It handles book inventory, user borrowing flow, and availability tracking.

Tech Stack
Java
Spring Boot
Spring Web
Spring Data JPA
Hibernate
MySQL
Maven
Database Entities

The system contains the following entities:

Book
Author
Category
Customer
Borrow
Relationships
One Author can have multiple Books
One Category can have multiple Books
One Book can have multiple Borrow records
One Customer can borrow multiple Books
Core Features
Manage books with available and total copies
Manage authors and categories
Register customers
Borrow books with availability validation
Return books and update stock automatically
Track all borrowed books
View available books
REST APIs
Category APIs
POST /categories – Create category
GET /categories – Get all categories
Author APIs
POST /authors – Create author
GET /authors – Get all authors
Book APIs
POST /books – Add book
GET /books – Get all books
GET /books/{id} – Get book by id
PUT /books/{id} – Update book
DELETE /books/{id} – Delete book
Customer APIs
POST /customers – Register customer
GET /customers – Get all customers
Borrow System
POST /borrow – Borrow a book
POST /return – Return a book
Reports APIs
GET /borrowed-books – List all borrowed books
GET /available-books – List all available books
Business Rules
Book name cannot be empty
Book copies cannot go below zero
Book cannot be borrowed if no copies are available
Author and Category must exist before assigning to a book
Borrow increases/decreases stock automatically
Returned books are marked as returned and stock is restored
Project Structure

src
├── controller
├── repository
├── entity
├── dto
└── LibraryManagementApplication.java
