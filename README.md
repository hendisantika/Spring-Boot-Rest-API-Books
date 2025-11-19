# Spring Boot REST API - Books Management System

A RESTful API for managing a library book borrowing system (Buku Management System) built with Spring Boot and MySQL.

## Technology Stack

- **Spring Boot**: 3.4.5
- **Java**: 21
- **Database**: MySQL
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Additional Libraries**: Joda-Time 2.14.0

## Prerequisites

Before running this application, ensure you have the following installed:

1. **Java 21** or higher
2. **Maven 3.x**
3. **MySQL 9.x** or compatible version
4. MySQL server running on `localhost:3306`

## Database Configuration

The application is configured to connect to MySQL with the following settings (in `application.properties`):

```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/libraryDB?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta&useSSL=false
spring.datasource.username=root
spring.datasource.password=root
```

**Note**: The database `libraryDB` will be created automatically when the application starts.

## How to Run

### Option 1: Using Maven (Recommended)

```bash
mvn clean spring-boot:run
```

### Option 2: Build and Run JAR

```bash
# Build the project
mvn clean install

# Run the JAR file
java -jar target/buku-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## API Endpoints

### 1. Get All Books

**Endpoint**: `GET /api/buku`

**Description**: Retrieves all books in the library

**Response Example**:

```json
[
  {
    "bookId": "B001",
    "title": "Spring Boot in Action",
    "category": "Programming",
    "registered": "2025-11-19",
    "total": 1,
    "available": 1,
    "peminjaman": []
  }
]
```

### 2. Add New Book

**Endpoint**: `POST /api/buku`

**Content-Type**: `application/json`

**Request Body**:

```json
{
  "bookId": "B001",
  "title": "Spring Boot in Action",
  "category": "Programming"
}
```

**Response**:

```json
{
  "Payload": {
    "bookId": "B001",
    "title": "Spring Boot in Action",
    "category": "Programming",
    "registered": "2025-11-19",
    "total": 0,
    "available": 0,
    "peminjaman": null
  },
  "message": "Buku created successfully",
  "Success": "True"
}
```

### 3. Filter Books by Title

**Endpoint**: `POST /api/buku/filter/title`

**Content-Type**: `application/json`

**Request Body**:

```json
{
  "title": "Spring Boot in Action"
}
```

**Response**:

```json
{
  "Payload": [
    {
      "bookId": "B001",
      "title": "Spring Boot in Action",
      "category": "Programming",
      "registered": "2025-11-19",
      "total": 1,
      "available": 1,
      "peminjaman": []
    }
  ],
  "message": "Buku ditemukan!",
  "Success": "True"
}
```

### 4. Filter Books by Category

**Endpoint**: `POST /api/buku/filter/category`

**Content-Type**: `application/json`

**Request Body**:

```json
{
  "category": "Programming"
}
```

### 5. Filter Books by Title and Category

**Endpoint**: `POST /api/buku/filter/title/category`

**Content-Type**: `application/json`

**Request Body**:

```json
{
  "title": "Spring Boot in Action",
  "category": "Programming"
}
```

### 6. Get All Borrowers

**Endpoint**: `GET /api/peminjam`

**Description**: Retrieves all registered borrowers

### 7. Add New Borrower

**Endpoint**: `POST /api/peminjam`

**Content-Type**: `application/json`

**Request Body**:

```json
{
  "uid": "U001",
  "name": "John Doe",
  "address": "123 Main Street"
}
```

**Response**:

```json
{
  "Payload": {
    "uid": "U001",
    "name": "John Doe",
    "address": "123 Main Street",
    "registered": "2025-11-19",
    "expired": "2026-01-19",
    "peminjaman": null
  },
  "message": "Data Peminjam sukses",
  "Success": "True"
}
```

### 8. Create Book Borrowing Transaction

**Endpoint**: `POST /api/peminjaman2`

**Content-Type**: `application/json`

**Request Body**:

```json
{
  "bookId": "B001",
  "userId": "U001"
}
```

**Response**:

```json
{
  "Payload": {
    "id": 1,
    "startDt": "2025-11-19",
    "returnDt": "2025-11-28",
    "bookId": "B001",
    "userId": "U001"
  },
  "message": "Data Peminjaman sukses",
  "Success": "True"
}
```

### 9. View Borrowing History

**Endpoint**: `POST /api/peminjaman2/find`

**Content-Type**: `application/json`

**Request Body**:

```json
{
  "bookId": "B001",
  "userId": "U001"
}
```

**Response**:

```json
{
  "Payload": [
    {
      "id": 1,
      "startDt": "2025-11-19",
      "returnDt": "2025-11-28",
      "bookId": "B001",
      "userId": "U001"
    }
  ],
  "message": "Data Peminjaman berhasil ditemukan",
  "Success": "True"
}
```

## Testing with cURL

### Create a Book

```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"bookId": "B001", "title": "Spring Boot in Action", "category": "Programming"}' \
  http://localhost:8080/api/buku
```

### Get All Books

```bash
curl http://localhost:8080/api/buku
```

### Create a Borrower

```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"uid": "U001", "name": "John Doe", "address": "123 Main Street"}' \
  http://localhost:8080/api/peminjam
```

### Create a Borrowing Transaction

```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"bookId": "B001", "userId": "U001"}' \
  http://localhost:8080/api/peminjaman2
```

## Testing with Postman

1. Open Postman
2. Set the request method (GET, POST, etc.)
3. Set the URL (e.g., `http://localhost:8080/api/buku`)
4. For POST requests:
    - Go to Headers tab
    - Add header: `Content-Type: application/json`
    - Go to Body tab
    - Select "raw" and "JSON"
    - Enter the JSON request body
5. Click "Send"

## Project Structure

```
src/main/java/com/hendisantika/buku/
├── BukuApplication.java          # Main Spring Boot application
├── JsonDateSerializer.java       # Custom JSON date serializer
├── controller/                   # REST Controllers
│   ├── BukuController.java      # Book management endpoints
│   ├── PeminjamController.java  # Borrower management endpoints
│   ├── PeminjamanController.java
│   └── Peminjaman2Controller.java
├── model/                        # Entity models
│   ├── Buku.java                # Book entity
│   ├── Peminjam.java            # Borrower entity
│   ├── Peminjaman.java
│   └── Peminjaman2.java
├── repository/                   # Data repositories
│   ├── BukuRepo.java
│   ├── PeminjamRepo.java
│   ├── PeminjamanRepo.java
│   └── Peminjaman2Repo.java
└── service/                      # Business logic
    ├── BukuService.java
    └── BukuImpl.java
```

## Database Schema

The application automatically creates the following tables:

- **buku**: Stores book information
- **peminjam**: Stores borrower information
- **peminjaman**: Stores borrowing transactions with relationships
- **peminjaman2**: Stores borrowing transactions (simplified)

## Important Notes

1. **Jakarta EE Migration**: This project uses Spring Boot 3.x which requires Jakarta EE 9+ packages (`jakarta.*`
   instead of `javax.*`). All JPA and validation imports use the Jakarta namespace.

2. **Auto DDL**: The application is configured with `spring.jpa.hibernate.ddl-auto=update`, which means tables are
   created/updated automatically on startup.

3. **Default Credentials**: The default MySQL credentials are `root/root`. Change these in `application.properties` for
   production use.

4. **Timezone**: The application is configured for `Asia/Jakarta` timezone.

5. **Return Date**: Books borrowed will have an automatic return date set to 9 days from the borrowing date.

## Troubleshooting

### MySQL Connection Error

- Ensure MySQL is running: `ps aux | grep mysql`
- Verify credentials in `application.properties`
- Check if port 3306 is accessible

### Build Errors

- Ensure Java 21 is installed: `java -version`
- Clean Maven cache: `mvn clean install -U`

### Port Already in Use

- Change the port in `application.properties`: `server.port=8081`
- Or kill the process using port 8080: `lsof -ti:8080 | xargs kill`

## Author

**@hendisantika**

## License

This is a demo project for Spring Boot REST API development.
