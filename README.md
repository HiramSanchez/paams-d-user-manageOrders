# **User Orders Manager (paams-d-user-manageOrders)**

[![Java](https://img.shields.io/badge/Java-21-skyblue)](https://www.oracle.com/java/)
 &emsp;
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-lightgreen)](https://spring.io/projects/spring-boot)
 &emsp;
[![Maven](https://img.shields.io/badge/Maven-3.9.9-gold)](https://maven.apache.org/)  

## **Description**
The **paams-d-user-manageOrders** microservice is responsible for managing user orders. It provides a RESTful API that allows creating, retrieving, updating, and deleting orders in the database. This service is scalable and designed to integrate easily into a microservices architecture.

## Table of Contents  
- [Features](#features)  
- [Endpoints](#api-endpoints)  
- [Documentation](#api-documentation)
- [Requirements](#requirements)
- [Installation](#installation)
- [Tests](#testing)
- [Contact](#contact)

## **Features**
- **Order Creation**: Create new orders and initiate the delivery process.
- **Order Retrieval**: Fetch order details from the database.
- **Order Update**: Updates orders status.
- **Order Deletion**: Permanently delete orders.

## **API Endpoints**

- **POST - /api/user/new/order**  
  `user-new-order`: Responsible for creating new orders and starting the delivery process.
  
- **GET - /api/user/retrieve/orders**  
  `retrieve-user-orders`: Retrieves user order data from the database.
  
- **PUT - /api/user/change/order**  
  `change-order-request`: Changes the state of a specific order.
  
- **DELETE - /api/user/delete/order**  
  `delete-user-order`: Permanently deletes a user order from the database.

## **API Documentation**
This project includes API documentation using swagger. Access the documentation by running it and navigating to:
```bash
http://localhost:8080/swagger-ui/index.html
```

## **Requirements**
Before running this project, ensure the following dependencies are installed:

- **Java 21**: [Download Java](https://www.oracle.com/java/technologies/downloads/#java21l)
- **Maven 3.9.9**: [Download Maven](https://maven.apache.org/download.cgi)
- **MongoDB**: [Download MongoDB](https://www.mongodb.com/try/download/community)

## **Installation**

1. Clone this repository:
    ```bash
    git clone https://github.com/your-username/paams-d-user-manageOrders.git
    cd paams-d-user-manageOrders
    ```

2. Configure the `application.properties` file with your database credentials:
    ```properties
    spring.datasource.url=jdbc:mongodb://localhost:27017/your_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

3. Build and run the application using Maven:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
## **Testing**
This project includes unit tests to ensure code quality and proper functionality. Additionally, it leverages **SonarQube** for static code analysis. Follow the steps below to run the tests and SonarQube analysis:
### **Running Tests**
1. Make sure all dependencies are installed and the database is properly configured.
2. Run the following Maven command to execute the tests:
   ```bash
   mvn clean test
   ```
3. The test results will be displayed in the terminal. To view detailed reports, check the `target/site/jococo/jococo.xml` directory.
### **SonarQube Analysis**
1. Start SonarQube (if using Docker):
   ```bash
   docker run -d --name sonarqube -p 9000:9000 sonarqube:latest
   ```
2. Set the `sonar-project.properties` file with your configurations, or directly send your credentials though the command.
   ```bash
   mvn sonar:sonar
   ```
   ```bash
   #Without setting properties on sonar-project file
   mvn clean verify sonar:sonar
   -Dsonar.projectKey={yourProjectKey}
   -Dsonar.host.url={yourSonarHost}
   -Dsonar.login={YourToken}
   -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
   ```
   
## **Contact**
For any questions or suggestions, feel free to contact me at:  
hiramsanchez.dev@gmail.com 
