# IdentityHub - User Management System

<p align="center">
  <b>A scalable Spring Boot backend for secure User Identity and Access Management.</b>
</p>

---

# Project Overview

IdentityHub is a backend User Management System developed using **Spring Boot** following a layered architecture and RESTful API design principles.

The project is intended to evolve into a production-ready Identity and Access Management (IAM) solution supporting:

* User Registration
* Authentication (JWT)
* Authorization (Role-Based Access Control)
* User Lifecycle Management
* Password Management
* Account Locking
* Soft Delete
* Audit & Logging
* Secure REST APIs

> **Current Development Stage:** User CRUD Module (Authentication Pending)

---

# Technology Stack

| Technology         | Version               |
| ------------------ | --------------------- |
| Java               | 21                    |
| Spring Boot        | 3.x                   |
| Spring Data JPA    | Latest                |
| Hibernate          | ORM                   |
| MySQL              | Database              |
| Maven              | Build Tool            |
| Lombok             | Boilerplate Reduction |
| Jakarta Validation | Bean Validation       |
| Postman            | API Testing           |

---

# Project Structure

```
src
│
├── controller
│      UserController.java
│
├── dto
│   ├── request
│   └── response
│
├── entity
│      UserEntity.java
│
├── enums
│      Gender.java
│      Role.java
│      UserStatus.java
│
├── exception
│      GlobalExceptionHandler.java
│      ResourceNotFoundException.java
│      UserAlreadyExistsException.java
│
├── repository
│      UserRepository.java
│
├── service
│      UserService.java
│
├── serviceImpl
│      UserServiceImpl.java
│
└── config
```

---

# Features Implemented

## User Registration

* Create new user
* Duplicate email validation
* Duplicate mobile validation
* Default USER role assignment
* Default ACTIVE status assignment

---

## User Retrieval

* Fetch user by ID
* Fetch all users

---

## User Update

* Update profile information
* Prevent duplicate email updates
* Entity validation

---

## Soft Delete

Implemented service logic to:

* Mark user as deleted
* Update status to DELETED
* Lock account
* Preserve database records

---

## DTO Mapping

Request DTO

```
RegisterRequestDTO
```

Response DTO

```
UserResponseDTO
```

Password DTO

```
ChangePasswordDTO
```

---

# Business Rules

## Registration

* Email must be unique
* Mobile number must be unique
* Default Role = USER
* Default Status = ACTIVE
* Account Locked = false
* Failed Login Attempts = 0
* isDeleted = false

---

## Update

* User must exist
* Updated email must remain unique

---

## Delete

Instead of permanently removing a user:

```
isDeleted = true
status = DELETED
accountLocked = true
```

---

# REST APIs

## Create User

```
POST /api/v1/users
```

### Request

```json
{
  "firstName":"Aditya",
  "lastName":"Binxxxxath",
  "email":"aditya@gmail.com",
  "mobile":9876543210,
  "password":"Password@123",
  "gender":"MALE"
}
```

---

## Get User

```
GET /api/v1/users/{id}
```

---

## Get All Users

```
GET /api/v1/users
```

---

## Update User

```
PUT /api/v1/users/{id}
```

---

## Delete User

```
DELETE /api/v1/users/{id}
```

---

## Activate User

```
PATCH /api/v1/users/{id}/activate
```

> Pending

---

## Block User

```
PATCH /api/v1/users/{id}/block
```

> Pending

---

## Change Password

```
PATCH /api/v1/users/{id}/change-password
```

> Pending

---

# HTTP Status Codes

| Status | Meaning               |
| ------ | --------------------- |
| 200    | Success               |
| 201    | Created               |
| 204    | No Content            |
| 400    | Validation Failed     |
| 404    | Resource Not Found    |
| 409    | Duplicate Resource    |
| 500    | Internal Server Error |

---

# Exception Handling

Implemented custom exceptions:

```
ResourceNotFoundException
```

```
UserAlreadyExistsException
```

Future:

* InvalidCredentialsException
* UnauthorizedException
* ForbiddenException
* PasswordMismatchException

---

# Repository Methods

Implemented custom JPA methods:

```java
findByEmailAndIsDeletedFalse()

findByMobileAndIsDeletedFalse()

findByIdAndIsDeletedFalse()

findAll()
```

---

# Current Development Progress

## Completed

* Project setup
* MySQL configuration
* Entity creation
* DTO creation
* Repository layer
* Service layer
* CRUD implementation
* Soft delete logic
* REST Controller
* Exception handling
* Postman testing

---

## Successfully Tested

| Feature        | Status |
| -------------- | ------ |
| Create User    | ✅      |
| Get User By ID | ✅      |
| Get All Users  | ✅      |
| Update User    | ✅      |

---

## Pending Testing

* Soft Delete
* Activate User
* Block User
* Change Password

---

# Upcoming Features

## Authentication

* Spring Security
* JWT Authentication
* Login API
* Logout API
* Refresh Token

---

## Authorization

Role Based Access Control

```
ADMIN
USER
SUPER_ADMIN
```

Using

```
@PreAuthorize()
```

---

## Password Security

* BCrypt Password Encoder
* Forgot Password
* Reset Password
* Password Change
* Password Policy

---

## Account Security

* Failed Login Tracking
* Account Lock
* Unlock Account
* OTP Verification
* Email Verification

---

## Audit

* Created Date
* Updated Date
* Created By
* Updated By
* Login History

---

# API Testing

Tool Used

```
Postman
```

Current APIs Tested

* POST User
* GET User
* GET All Users
* PUT User

---

# Future Enhancements

* Swagger/OpenAPI Documentation
* Docker Support
* CI/CD Pipeline
* Unit Testing
* Integration Testing
* Redis Cache
* API Rate Limiting
* Email Notifications
* Logging
* Monitoring
* Kubernetes Deployment

---

# Author

**Aditya Binjagermath**

B.Tech Computer Science Engineering

Backend Developer | Java | Spring Boot | REST APIs | MySQL | Hibernate

---

# License

This project is developed for educational and learning purposes and is intended to evolve into a production-ready Identity and Access Management System.
