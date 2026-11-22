# Quantity Measurement App

A comprehensive Java-based Quantity Measurement Application developed incrementally through 17 use cases (UC1–UC17). The application evolves from a standalone object-oriented measurement system into a Spring Boot RESTful web service with persistent storage, database integration, API documentation, validation, and automated testing.

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Objectives](#2-project-objectives)
3. [Technology Stack](#3-technology-stack)
4. [Features](#4-key-features)
5. [Supported Measurement Categories](#5-supported-measurement-categories)
6. [Project Architecture](#6-project-architecture)
7. [Project Structure](#7-project-structure)
8. [Core Design and Implementation](#8-core-design-and-implementation)
9. [Measurement Operations](#9-measurement-operations)
10. [Repository and Persistence Architecture](#10-repository-and-persistence-architecture)
11. [Database Configuration](#11-database-configuration)
12. [REST API Documentation](#12-rest-api-documentation)
13. [Request Validation](#13-request-validation)
14. [Global Exception Handling](#14-global-exception-handling)
15. [JSON and XML Support](#15-json-and-xml-support)
16. [Swagger and OpenAPI](#16-swagger-and-openapi-documentation)
17. [Spring Boot Actuator](#17-spring-boot-actuator)
18. [Testing Strategy](#18-testing-strategy)
19. [Installation and Setup](#19-installation-and-setup)
20. [Running the Application](#20-running-the-application)
21. [API Testing with Postman](#21-api-testing-with-postman)
22. [Build and Packaging](#22-build-and-packaging)
23. [Configuration Reference](#23-configuration-reference)
24. [Design Patterns and Engineering Principles](#24-design-patterns-and-engineering-principles)
25. [Backward Compatibility](#25-backward-compatibility)
26. [Future Enhancements](#26-future-enhancements)

---

# 1. Project Overview

The Quantity Measurement App is a Java application designed to perform operations on physical quantities belonging to different measurement categories.

The project initially focuses on object-oriented programming concepts, measurement equality, unit conversion, and arithmetic operations. As development progresses, it introduces generic programming, abstraction, reusable measurement models, repository-based persistence, JDBC connectivity, Spring Data JPA, and REST API development.

The final application is built using Spring Boot and exposes REST endpoints that allow clients to perform measurement operations through HTTP requests.

The application supports both JSON and XML response formats and provides persistent storage using MySQL and Spring Data JPA.

### Evolution of the application

The project follows an incremental development approach:

- **Initial development:** Measurement models, unit enums, equality, conversion, and arithmetic.
- **Generic architecture:** Reusable quantity models and measurement abstractions.
- **Persistence:** Repository interfaces, cache-based storage, and JDBC integration.
- **Spring Boot integration:** Dependency injection, service layer, REST controllers, and JPA.
- **API development:** JSON/XML responses, validation, exception handling, Swagger, Actuator, and MockMvc testing.

---

# 2. Project Objectives

The primary objectives are:

1. Develop a reusable measurement system using object-oriented programming.
2. Support multiple measurement categories and units.
3. Implement measurement equality and conversion.
4. Perform arithmetic operations between compatible quantities.
5. Use Java generics to reduce code duplication.
6. Apply abstraction and interfaces for extensibility.
7. Implement repository-based data persistence.
8. Integrate MySQL using JDBC and connection pooling.
9. Migrate the Spring Boot application to Spring Data JPA.
10. Expose application functionality through REST APIs.
11. Support JSON and XML response representations.
12. Implement centralized exception handling and input validation.
13. Provide interactive API documentation using Swagger.
14. Monitor application health using Spring Boot Actuator.
15. Verify application behavior through automated unit and integration tests.

---

# 3. Technology Stack

| Technology | Purpose |
|---|---|
| Java 21 | Primary programming language target |
| Maven | Dependency and build management |
| Spring Boot 4.0.1 | Application framework |
| Spring Web MVC | REST API development |
| Spring Data JPA | Database persistence |
| Hibernate | JPA implementation and ORM |
| JDBC | Direct database connectivity |
| MySQL | Production relational database |
| H2 Database | In-memory test database |
| HikariCP | Database connection pooling |
| Jakarta Validation | Request validation |
| Spring Boot Actuator | Application monitoring |
| Springdoc OpenAPI | Swagger UI and API documentation |
| Jackson 3 XML | XML serialization |
| JUnit 5 | Unit testing |
| Spring Boot Test | Application integration testing |
| MockMvc | REST controller testing |
| Postman | Manual API testing |
| Embedded Tomcat | Application server |

---

# 4. Key Features

### Measurement functionality

- Measurement equality.
- Unit conversion.
- Addition of compatible quantities.
- Subtraction of compatible quantities.
- Division of compatible quantities.
- Temperature conversion and comparison.
- Generic measurement handling.

### Application architecture

- Object-oriented design.
- Generic quantity representation.
- Interface-based abstraction.
- Service and repository separation.
- Dependency injection.
- Repository selection through configuration.

### Persistence

- File-based cache persistence.
- JDBC-based MySQL persistence.
- Spring Data JPA persistence.
- H2 in-memory database for automated testing.
- Transaction management.

### REST functionality

- RESTful endpoints.
- JSON request and response support.
- XML response support.
- Input validation.
- Centralized exception handling.
- HTTP status code management.

### Monitoring and documentation

- Swagger UI.
- OpenAPI specification.
- Actuator health endpoint.
- MockMvc integration testing.
- Maven-based automated testing.

---

# 5. Supported Measurement Categories

The application supports four major measurement categories.

| Category | Description |
|---|---|
| Length | Represents physical distance |
| Weight | Represents mass or weight measurements |
| Volume | Represents liquid or volumetric measurements |
| Temperature | Represents temperature values |

Each measurement category has its own unit enum.

The application uses unit metadata and conversion factors to determine whether two quantities are compatible and how their values should be converted.

Examples:

- Feet to inches.
- Centimeters to another supported length unit.
- Kilograms to another supported weight unit.
- Liters to another supported volume unit.
- Celsius to Fahrenheit.

The conversion logic is maintained within the measurement model and unit abstractions rather than duplicated throughout the application.

---

# 6. Project Architecture

The application follows a layered architecture.

```text
                    CLIENT
               (Postman / HTTP)
                       |
                       v
              REST CONTROLLER
                       |
                       v
                SERVICE LAYER
                       |
                       v
               REPOSITORY LAYER
                       |
             +---------+---------+
             |         |         |
             v         v         v
           CACHE     JDBC       JPA
             |         |         |
             v         v         v
          File      MySQL      MySQL
                                |
                                v
                         H2 for Tests
```

### Layer responsibilities

**1. Controller Layer**

Responsible for:

- Receiving HTTP requests.
- Mapping endpoints.
- Reading request payloads.
- Calling service methods.
- Returning HTTP responses.
- Applying request validation.

**2. Service Layer**

Responsible for:

- Business logic.
- Unit compatibility checks.
- Conversion operations.
- Comparison operations.
- Arithmetic calculations.
- Saving and retrieving measurements.
- Transaction management.

**3. Repository Layer**

Responsible for:

- Saving measurement records.
- Retrieving all records.
- Finding records by ID.
- Clearing stored records.
- Abstracting persistence implementation.

**4. Model and Entity Layer**

Responsible for:

- Representing quantities.
- Defining measurement units.
- Maintaining measurement values.
- Representing database records.

**5. DTO Layer**

Responsible for:

- Transferring request data.
- Separating API input structures from internal persistence entities.
- Supporting request validation.

---

# 7. Project Structure

The primary source structure is organized as follows:

```text
quantitymeasurementapp/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── quantitymeasurement/
│   │   │           │
│   │   │           ├── config/
│   │   │           │   ├── RepositoryConfiguration.java
│   │   │           │   └── XmlConfiguration.java
│   │   │           │
│   │   │           ├── controller/
│   │   │           │   └── QuantityMeasurementController.java
│   │   │           │
│   │   │           ├── service/
│   │   │           │   ├── IQuantityMeasurementService.java
│   │   │           │   └── QuantityMeasurementServiceImpl.java
│   │   │           │
│   │   │           ├── repository/
│   │   │           │   ├── IQuantityMeasurementRepository.java
│   │   │           │   ├── QuantityMeasurementCacheRepository.java
│   │   │           │   ├── QuantityMeasurementDatabaseRepository.java
│   │   │           │   ├── QuantityMeasurementJpaRepository.java
│   │   │           │   ├── QuantityMeasurementJpaRepositoryAdapter.java
│   │   │           │   ├── DatabaseSchemaInitializer.java
│   │   │           │   └── RepositoryFactory.java
│   │   │           │
│   │   │           ├── dto/
│   │   │           │   └── QuantityDTO.java
│   │   │           │
│   │   │           ├── entity/
│   │   │           │   └── QuantityMeasurementEntity.java
│   │   │           │
│   │   │           ├── model/
│   │   │           │   └── QuantityModel.java
│   │   │           │
│   │   │           ├── exception/
│   │   │           │   ├── QuantityMeasurementException.java
│   │   │           │   └── GlobalExceptionHandler.java
│   │   │           │
│   │   │           ├── enums/
│   │   │           │   └── Measurement unit enums
│   │   │           │
│   │   │           ├── IMeasurable.java
│   │   │           ├── Quantity.java
│   │   │           ├── QuantityLength.java
│   │   │           ├── QuantityWeight.java
│   │   │           ├── QuantityMeasurementApp.java
│   │   │           └── QuantitymeasurementappApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── quantitymeasurement/
│       │           ├── controller/
│       │           ├── repository/
│       │           ├── service/
│       │           └── application tests
│       │
│       └── resources/
│           └── application-test.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

The exact number of test classes and package contents may evolve as additional use cases are implemented.

---

# 8. Core Design and Implementation

## 8.1 Measurement Abstraction

The application uses the `IMeasurable` interface to define common behavior for measurement units.

It provides a common abstraction for identifying measurement categories and resolving supported units.

Important methods include:

```java
getMeasurementType(String unitName)
getUnit(String unitName)
```

This allows the application to identify the measurement category associated with a unit and retrieve the corresponding unit implementation.

## 8.2 Generic Quantity Model

The generic quantity model is represented by:

```java
Quantity<U extends IMeasurable>
```

Using Java generics allows the same quantity structure to represent different measurement categories.

Instead of creating completely separate implementations for every unit, the application can reuse the generic quantity model.

Benefits:

- Type safety.
- Code reusability.
- Reduced duplication.
- Easier extension.
- Better maintainability.

## 8.3 Unit Enums

Each measurement category has its own enum.

The enums represent supported units and their conversion information.

For example, a length unit contains the information required to convert its value into a common reference unit.

This makes conversion logic consistent and reusable.

## 8.4 Legacy Compatibility

The application retains legacy classes such as:

- `QuantityLength`
- `QuantityWeight`

These classes are maintained to preserve compatibility with earlier use cases and tests.

The generic architecture extends the existing implementation instead of unnecessarily removing the original functionality.

---

# 9. Measurement Operations

The application supports the following major operations.

## 9.1 Equality

Two measurements are equal when they represent the same physical quantity after converting them into a common unit.

For example:

```text
1 FEET = 12 INCHES
```

The values are different, but the physical quantities are equivalent.

## 9.2 Conversion

Conversion changes the unit representation while preserving the physical quantity.

Example:

```text
Input:
5 FEET

Target:
INCHES

Output:
60 INCHES
```

## 9.3 Addition

Addition converts compatible measurements into a common unit before performing the operation.

Example:

```text
1 FEET + 12 INCHES

Result:
24 INCHES
```

## 9.4 Subtraction

Subtraction converts compatible measurements into a common unit before calculating the difference.

Example:

```text
2 FEET - 12 INCHES

Result:
12 INCHES
```

## 9.5 Division

Division calculates the ratio between compatible measurements.

Example:

```text
2 FEET / 1 FEET

Result:
2
```

The application validates unit compatibility before performing arithmetic operations.

---

# 10. Repository and Persistence Architecture

The application separates persistence logic from business logic using the repository pattern.

The common repository interface is:

```java
public interface IQuantityMeasurementRepository {

    void save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> findAll();

    QuantityMeasurementEntity findById(String id);

    void clear();
}
```

Different implementations provide different persistence strategies.

## 10.1 Cache Repository

`QuantityMeasurementCacheRepository` provides file-based persistence.

Features:

- Stores measurement records in a serialized file.
- Supports saving records.
- Supports retrieving all records.
- Supports finding records by ID.
- Provides a singleton instance.
- Supports an isolated test repository.

The cache implementation is useful for standalone functionality and compatibility with earlier use cases.

## 10.2 JDBC Repository

`QuantityMeasurementDatabaseRepository` provides direct database access through JDBC.

Features:

- Uses JDBC connections.
- Uses PreparedStatement for database operations.
- Supports CRUD operations.
- Uses transactions.
- Supports rollback on failures.
- Maps database rows into measurement entities.

## 10.3 Spring Data JPA Repository

The application also uses Spring Data JPA.

```java
@Repository
public interface QuantityMeasurementJpaRepository
        extends JpaRepository<QuantityMeasurementEntity, String> {
}
```

Spring Data JPA automatically provides common persistence operations through `JpaRepository`.

This reduces repetitive database access code.

## 10.4 JPA Adapter

`QuantityMeasurementJpaRepositoryAdapter` adapts the Spring Data JPA repository to the application's existing repository interface.

This preserves the repository abstraction while allowing the application to use Spring Data JPA.

## 10.5 Repository Factory

`RepositoryFactory` selects the appropriate repository implementation based on configuration.

Supported repository types:

| Repository type | Description |
|---|---|
| `CACHE` | File-based persistence |
| `DATABASE` | JDBC-based persistence |
| `JPA` | Spring Data JPA persistence |

The default Spring Boot repository configuration uses JPA.

---

# 11. Database Configuration

The application uses MySQL for persistent database storage.

Database name:

```sql
CREATE DATABASE quantity_measurement_db;
```

The application uses a measurement table named:

```text
quantity_measurements
```

The table stores measurement information such as:

- Unique record ID.
- First measurement value.
- First measurement unit.
- Second measurement value.
- Second measurement unit.
- Operation type.
- Result.
- Result unit.
- Error message.
- Creation timestamp.

The exact fields populated depend on the operation being performed.

## 11.1 JDBC Configuration

The application uses HikariCP for database connection pooling.

HikariCP manages a pool of reusable database connections instead of creating a new connection for every database operation.

## 11.2 Environment Variables

Database credentials are configured through environment variables.

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/quantity_measurement_db"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="YOUR_DATABASE_PASSWORD"
```

Replace the placeholder with your local MySQL password.

Do not commit actual database credentials to Git.

---

# 12. REST API Documentation

Base URL:

```text
http://localhost:8080/api/measurements
```

The REST controller exposes five main endpoints.

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/convert` | Convert a measurement |
| POST | `/compare` | Compare two measurements |
| POST | `/calculate` | Perform arithmetic operations |
| GET | `/` | Retrieve all measurement records |
| GET | `/{id}` | Retrieve a measurement by ID |

## 12.1 Convert Measurement

**Endpoint**

```http
POST /api/measurements/convert
```

**Request**

```json
{
  "source": {
    "value": 5,
    "unit": "FEET"
  },
  "targetUnit": "INCHES"
}
```

**Expected result**

```text
5 FEET = 60 INCHES
```

The API returns the converted measurement entity.

## 12.2 Compare Measurements

**Endpoint**

```http
POST /api/measurements/compare
```

**Request**

```json
{
  "first": {
    "value": 1,
    "unit": "FEET"
  },
  "second": {
    "value": 12,
    "unit": "INCHES"
  }
}
```

The service converts compatible measurements into a common unit and compares their values.

The comparison result is returned by the API.

## 12.3 Calculate Measurements

**Endpoint**

```http
POST /api/measurements/calculate
```

**Request**

```json
{
  "first": {
    "value": 2,
    "unit": "FEET"
  },
  "second": {
    "value": 12,
    "unit": "INCHES"
  },
  "operation": "ADD"
}
```

Supported operation names:

```text
ADD
SUBTRACT
DIVIDE
```

The service validates measurement compatibility and performs the requested calculation.

## 12.4 Retrieve All Measurements

**Endpoint**

```http
GET /api/measurements
```

Returns all saved measurement records.

## 12.5 Retrieve Measurement by ID

**Endpoint**

```http
GET /api/measurements/{id}
```

Example:

```http
GET /api/measurements/17a3c3ef-81eb-47bb-aa1b-3bf8ded77dca
```

Returns the measurement record associated with the supplied ID.

If the ID does not exist, the application returns HTTP 404.

---

# 13. Request Validation

The application uses Jakarta Bean Validation to validate incoming API requests.

Validation annotations include:

```java
@NotBlank
@AssertTrue
@Valid
```

### Unit validation

The unit field must not be empty or blank.

### Measurement value validation

The measurement value must be finite.

This prevents invalid values such as:

```text
NaN
Infinity
-Infinity
```

### Nested request validation

The controller uses `@Valid` on request objects and their nested quantity DTOs.

Invalid requests are rejected before normal business processing.

Example validation response:

```json
{
  "status": 400,
  "validationErrors": {
    "source.unit": "Measurement unit cannot be empty"
  },
  "message": "Request validation failed",
  "error": "Bad Request"
}
```

---

# 14. Global Exception Handling

The application centralizes exception handling using Spring's `@RestControllerAdvice`.

This avoids repeating exception-handling logic in every controller endpoint.

The handler manages:

| Exception | HTTP Status | Purpose |
|---|---|---|
| `QuantityMeasurementException` | 400 | Invalid measurement operation |
| `NoSuchElementException` | 404 | Measurement record not found |
| `MethodArgumentNotValidException` | 400 | Request validation failure |
| Other unexpected exceptions | 500 | Internal server error |

The response includes useful information such as:

- Timestamp.
- HTTP status.
- Error description.
- Error message.
- Validation errors, when applicable.

This provides consistent error responses throughout the REST API.

---

# 15. JSON and XML Support

The application supports JSON and XML response representations through Spring MVC message converters.

## JSON

JSON is the default API representation.

Request header:

```http
Accept: application/json
```

## XML

XML responses are enabled using Jackson XML support and the custom XML message converter configuration.

Request header:

```http
Accept: application/xml
```

Example XML conversion response:

```xml
<QuantityMeasurementEntity>
    <firstUnit>FEET</firstUnit>
    <firstValue>5.0</firstValue>
    <operation>CONVERSION</operation>
    <result>60.0</result>
    <resultUnit>INCHES</resultUnit>
    <successful>true</successful>
</QuantityMeasurementEntity>
```

### Content negotiation

Spring MVC uses the `Accept` header to determine the requested response format.

The `Content-Type` header identifies the format of the request body.

Example:

```http
Content-Type: application/json
Accept: application/xml
```

This allows clients to send JSON requests and receive XML responses.

---

# 16. Swagger and OpenAPI Documentation

The application integrates Springdoc OpenAPI for automatic REST API documentation.

Swagger UI provides an interactive interface for exploring and testing API endpoints.

**Swagger UI**

```text
http://localhost:8080/swagger-ui.html
```

**OpenAPI JSON**

```text
http://localhost:8080/v3/api-docs
```

Swagger documentation includes:

- Available API endpoints.
- HTTP methods.
- Request body schemas.
- Response information.
- API testing interface.

Swagger allows developers to test endpoints without manually constructing every request in Postman.

---

# 17. Spring Boot Actuator

Spring Boot Actuator provides application monitoring endpoints.

The application exposes:

```text
/actuator/health
/actuator/info
```

### Health endpoint

```http
GET http://localhost:8080/actuator/health
```

Returns application health information.

Example:

```json
{
  "status": "UP"
}
```

### Info endpoint

```http
GET http://localhost:8080/actuator/info
```

Provides application information when configured.

Actuator helps verify whether the application is running and provides a foundation for production monitoring.

---

# 18. Testing Strategy

The application uses multiple testing approaches.

## 18.1 Unit Testing

JUnit 5 is used to test individual components independently.

Examples:

- Unit equality.
- Conversion calculations.
- Arithmetic operations.
- Unit compatibility.
- Service logic.
- Repository factory behavior.

## 18.2 Repository Testing

Repository tests verify persistence operations.

They cover:

- Saving records.
- Retrieving records.
- Finding records by ID.
- Clearing repository data.
- Cache persistence.
- JDBC persistence.
- Spring Data JPA operations.

## 18.3 Integration Testing

Spring Boot integration tests verify the interaction between application layers.

They test:

- Spring application startup.
- Dependency injection.
- Repository configuration.
- Service integration.
- Controller integration.
- Database persistence.

## 18.4 MockMvc Testing

MockMvc is used to test REST endpoints without starting a real HTTP server.

Controller tests verify:

- Successful conversion requests.
- HTTP response status codes.
- Request validation.
- JSON response structures.

## 18.5 Test Database

Automated integration tests use an H2 in-memory database.

The test configuration is maintained separately from production database configuration.

This ensures that test execution does not depend on the production MySQL database.

---

# 19. Installation and Setup

## Prerequisites

Install the following:

1. JDK 21 or compatible newer JDK.
2. Maven or use the included Maven Wrapper.
3. MySQL Server.
4. IntelliJ IDEA or another Java IDE.
5. Postman (optional, for manual API testing).

Verify Java:

```powershell
java -version
```

Verify Maven Wrapper:

```powershell
.\mvnw.cmd -version
```

## Clone the repository

```powershell
git clone <YOUR_REPOSITORY_URL>
```

Navigate into the project:

```powershell
cd quantitymeasurementapp
```

Configure database environment variables:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/quantity_measurement_db"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="YOUR_DATABASE_PASSWORD"
```

Download dependencies:

```powershell
.\mvnw.cmd dependency:resolve
```

---

# 20. Running the Application

## Run tests

```powershell
.\mvnw.cmd clean test
```

## Compile the application

```powershell
.\mvnw.cmd clean compile
```

## Start Spring Boot

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

The default embedded server is Tomcat.

---

# 21. API Testing with Postman

The following requests can be used to verify application functionality.

| Test | Method | Endpoint |
|---|---|---|
| Conversion | POST | `/api/measurements/convert` |
| Comparison | POST | `/api/measurements/compare` |
| Calculation | POST | `/api/measurements/calculate` |
| Get all records | GET | `/api/measurements` |
| Get by ID | GET | `/api/measurements/{id}` |
| Swagger | GET | `/swagger-ui.html` |
| Health | GET | `/actuator/health` |

### Recommended test scenarios

1. Convert FEET to INCHES.
2. Compare 1 FEET with 12 INCHES.
3. Add compatible length measurements.
4. Subtract compatible measurements.
5. Divide compatible measurements.
6. Send an invalid or empty unit.
7. Send a non-finite measurement value.
8. Retrieve all saved measurements.
9. Retrieve a measurement using a valid ID.
10. Retrieve a measurement using an invalid ID.
11. Request a JSON response.
12. Request an XML response.
13. Verify the health endpoint.

---

# 22. Build and Packaging

Maven is used to build and package the application.

## Clean and package

```powershell
.\mvnw.cmd clean package
```

This command:

1. Removes previous build output.
2. Compiles application source code.
3. Compiles test source code.
4. Executes tests.
5. Packages the application into an executable JAR.

The generated JAR is located in:

```text
target/quantitymeasurementapp-0.0.1-SNAPSHOT.jar
```

Run the packaged application:

```powershell
java -jar target/quantitymeasurementapp-0.0.1-SNAPSHOT.jar
```

On Windows PowerShell:

```powershell
java -jar .\target\quantitymeasurementapp-0.0.1-SNAPSHOT.jar
```

---

# 23. Configuration Reference

The main configuration file is:

```text
src/main/resources/application.properties
```

Important properties include:

```properties
spring.application.name=QuantityMeasurementApp

# Repository selection
app.repository.type=${APP_REPOSITORY_TYPE:JPA}

# Database configuration
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/quantity_measurement_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# HikariCP
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.pool-name=QuantityMeasurementPool

# Actuator
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=always

# JPA
spring.jpa.open-in-view=false
```

### Repository selection

The application supports repository selection through:

```properties
app.repository.type=JPA
```

Alternative configurations:

```properties
app.repository.type=CACHE
```

```properties
app.repository.type=DATABASE
```

The default Spring Boot configuration selects JPA.

### Test configuration

The test properties are maintained separately in:

```text
src/test/resources/application-test.properties
```

The test profile uses H2:

```properties
spring.datasource.url=jdbc:h2:mem:quantity_test_db;MODE=MySQL;DATABASE_TO_LOWER=TRUE;DB_CLOSE_DELAY=-1
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver

spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=1

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.open-in-view=false
```

This keeps automated test data isolated from production MySQL data.

---

# 24. Design Patterns and Engineering Principles

The application uses several important software engineering concepts.

## 24.1 Repository Pattern

The repository interface abstracts data storage from the business layer.

The service does not need to know whether data is stored in a file, through JDBC, or using JPA.

## 24.2 Factory Pattern

The repository factory selects the appropriate repository implementation based on configuration.

This makes it possible to switch persistence strategies without rewriting service logic.

## 24.3 Adapter Pattern

The JPA adapter implements the existing repository interface and delegates operations to Spring Data JPA.

This allows the newer persistence mechanism to work with the existing repository abstraction.

## 24.4 Dependency Injection

Spring manages application dependencies and injects required components.

This reduces tight coupling and simplifies testing.

## 24.5 Layered Architecture

The application separates controllers, services, repositories, models, DTOs, and entities.

Each layer has a distinct responsibility.

## 24.6 Generic Programming

Java generics allow the same quantity model to represent multiple measurement types.

This reduces duplication and improves type safety.

## 24.7 Transaction Management

Spring's transaction management ensures that transactional service operations are handled consistently.

Database operations can be committed or rolled back according to transaction outcome.

## 24.8 Centralized Exception Handling

`@RestControllerAdvice` centralizes error handling and provides consistent API responses.

---

# 25. Backward Compatibility

An important implementation objective is preserving existing functionality while extending the application.

The Spring Boot REST implementation does not unnecessarily remove earlier measurement classes or legacy application methods.

The application maintains:

- Existing measurement behavior.
- Legacy quantity classes.
- Generic quantity implementation.
- Existing service logic.
- Repository abstraction.
- Compatibility with earlier use-case tests.

This allows the project to evolve without breaking its previously implemented functionality.

---

# 26. Future Enhancements

Potential future improvements include:

1. Authentication and authorization using Spring Security.
2. Role-based access control.
3. User-specific measurement history.
4. Pagination and sorting for measurement records.
5. More measurement categories and units.
6. API rate limiting.
7. Docker-based deployment.
8. CI/CD pipeline integration.
9. Production monitoring and structured logging.
10. Cloud deployment.

These are possible extensions and are not claimed as completed features.

---

## Conclusion

The Quantity Measurement App demonstrates the evolution of a Java application from basic object-oriented programming into a layered, persistent, and API-driven Spring Boot application.

Across its 17 use cases, the project incorporates Java fundamentals, generics, abstraction, design patterns, repository-based persistence, JDBC, MySQL, Spring Data JPA, REST API development, JSON/XML serialization, validation, exception handling, API documentation, monitoring, and automated testing.

The final architecture emphasizes modularity, maintainability, reusability, and backward compatibility while providing a foundation for further enterprise application development.

**Project:** Quantity Measurement App  
**Framework:** Spring Boot 4.0.1  
**Language:** Java  
**Build Tool:** Maven  
**Persistence:** MySQL / Spring Data JPA / JDBC / Cache  
**API:** REST with JSON and XML support
