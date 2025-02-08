# ContactManagementRepository
ContactCRUDRepository

Contact Management Backend Service with Chat System

A Spring Boot-based RESTful API for managing contacts and enabling real-time chat functionality using WebSocket.

Features

Contact Management:

Create, Read, Update, and Delete (CRUD) contacts.

Store contact information in an H2 in-memory database.

Real-Time Chat System:

Send and receive messages in real-time using WebSocket.

Store chat messages in the database for persistence.

Security:

Configured to allow access to H2 console, WebSocket, and API endpoints without authentication.

Testing:

Includes unit tests for API endpoints using MockMvc.

Technologies Used

Backend: Spring Boot

Database: H2 (in-memory), Spring Data JPA

Real-Time Communication: WebSocket (STOMP)

Testing: JUnit, Mockito

Build Tool: Maven

Frontend (Chat UI): HTML, JavaScript (SockJS, STOMP)

Installation and Setup

Follow these steps to set up and run the project locally:

Clone the repository:

git clone https://github.com/Sruthii02/ContactManagementRepository.git

Navigate to the project directory:

cd ContactManagementRepository

Build the project:

mvn clean install

Run the application:

mvn spring-boot:run

Access the application:

The application will start on http://localhost:8080.

Use tools like Postman to interact with the API.

API Endpoints

Contact Management

Create a Contact:

POST /contacts/create

Request Body:

{
  "name": "Sruthi",
  "email": "sruthi@123.com",
  "phone": "123456789"
}

Get All Contacts:

GET /contacts

Get a Contact by ID:

GET /contacts/{id}

Update a Contact:

PUT /contacts/{id}

Request Body:

{
  "name": "Sruthi",
  "email": "sruthi@123.com",
  "phone": "0987654321"
}

Delete a Contact:

DELETE /contacts/{id}

Chat System

WebSocket Endpoint:

ws://localhost:8080/chat-websocket

Connect to this endpoint to send and receive real-time messages.

Messages are stored in the database for persistence.

Database Schema

Contact Table:

CREATE TABLE contact (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL
);

Chat Message Table:

CREATE TABLE chat_messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE
);

Testing

Run unit tests using:

mvn test

Tests cover:

Contact CRUD operations.

WebSocket message handling.

H2 Database Console

Access the H2 database console at http://localhost:8080/h2-console.

Use the following credentials:

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (leave empty)

Real-Time Chat UI

Open index.html in your browser to use the chat interface.

Enter your name and start sending messages in real-time.

Configuration

application.properties

spring.application.name=contactapp
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

License

This project is licensed under the MIT License.

Contact

For questions or feedback, feel free to reach out:

SRUTHI T - sruthithayyil2002@gmail.com

GitHub - https://github.com/Sruthii02

Project Link - https://github.com/Sruthii02/ContactManagementRepository/tree/contact_crud/contactapp