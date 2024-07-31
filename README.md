Library Management System

This project is a Library Management System built with Spring Boot. It provides RESTful APIs for managing users and books, including functionality for registering users, adding books, and borrowing books. The project includes robust validation and exception handling, ensuring a smooth and reliable user experience.

Features:

User Management: Register users with details like username, mobile number, email, address, and role.
Book Management: Add and manage books with details like book name, author, and number of copies.
Borrowing Books: Users can borrow books, and the system ensures only one book can be borrowed at a time. The number of copies is updated accordingly.
Validation: All inputs are validated to ensure data integrity and security.
Exception Handling: Comprehensive exception handling provides meaningful error messages and ensures the application is robust.
Testing: APIs tested using Postman.

Technologies Used:

Spring Boot: For building the RESTful APIs and managing the application.
Spring Data JPA: For database interactions.
MySQL Database: As the database for development and production.
Hibernate Validator: For implementing validation.
