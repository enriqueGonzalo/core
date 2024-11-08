
# Core - Java Application with Hexagonal Architecture

This project is a **Java** application that follows the **Hexagonal Architecture** (also known as Ports and Adapters Architecture). The main goal is to provide a robust and scalable solution for managing prices in an e-commerce database.

## Table of Contents

- [Introduction](#introduction)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Setup and Execution](#setup-and-execution)
- [Usage](#usage)
- [Project Structure](#project-structure)


## Introduction

The application exposes a **REST API** that allows querying the price of a specific product based on its **application date**, **product ID**, and **brand ID**. The system is designed using **Domain-Driven Design (DDD)** principles and follows a **Hexagonal Architecture** to separate the core business logic from external dependencies.

## Architecture

The **Hexagonal Architecture** used in this project follows a **ports and adapters** approach:
- **api-rest**: Contains the REST API controllers and DTOs.
- **Domain**: Contains domain entities and business logic.
- **Application**: Defines use cases and business rules.
- **Infrastructure**: Manages configuration, persistence, and other infrastructure components.

### Architecture Diagram

```
+-------------------+
|   Application     |
+-------------------+
        |
+-------------------+
|     Domain        |
+-------------------+
|      Core         |
+-------------------+
```

## Technologies Used

- **Java 17**
- **Spring Boot 3.x**
- **H2 Database** (for local environment)
- **MapStruct** (for DTO conversion)
- **Lombok** (to reduce boilerplate code)
- **JUnit 5** (for unit testing)
- **Maven** (dependency management)

## Prerequisites

Make sure you have the following installed before getting started:

- **Java 17** or higher
- **Maven** 3.x
- **Git**

## Setup and Execution

### Clone the Repository

```bash
git clone https://github.com/enriqueGonzalo/core.git
cd core
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

## Usage

### Price Query Endpoint

The main endpoint exposes the following route:

```http
GET /api/v1/prices?applicationDate={applicationDate}&productId={productId}&brandId={brandId}
```

**Parameters:**
- `applicationDate` (String): Date in the format `yyyy-MM-dd'T'HH:mm:ss`.
- `productId` (Integer): Product identifier.
- `brandId` (Integer): Brand identifier.

**Sample request:**
```bash
curl -X GET "http://localhost:8080/api/v1/prices?applicationDate=2024-11-06T10:00:00&productId=35455&brandId=1"
```

**Expected response:**
```json
{
  "productId": 35455,
  "brandId": 1,
  "startDate": "2024-11-06T10:00:00",
  "price": 35.50
}
```

## Project Structure

```
core
├── api-rest
│   ├── controller
│   ├── mapper
├── application
│   └── usecase
├── domain
│   ├── entity
│   ├── repository
│   └── usecase
└───infrastructure
    └──repository
```

