# 🚀 Task Management System (Spring Boot + Security)

A professional web-based application for managing tasks with secure authentication and role-based access control.

## ✨ Features
- **User & Admin Dashboards:** Separate interfaces for different user roles.
- **Secure Authentication:** Implementation of Spring Security with BCrypt password encoding.
- **Task Tracking:** Full CRUD functionality for managing daily tasks.
- **Persistent Storage:** Integrated with MySQL Database.
- **Production Ready:** Configured for seamless deployment on Railway.app.

## 🛠️ Tech Stack
- **Language:** Java 21
- **Framework:** Spring Boot 3.x
- **Security:** Spring Security
- **Data:** Spring Data JPA & MySQL
- **Frontend:** Thymeleaf & Bootstrap

## ⚙️ How to Run Locally
1. Clone the repository:
   ```bash
   git clone https://github.com/DivayGrg/task-management-system

   task-management-system/ (Root Folder)
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── dway/
│   │   │           └── task/
│   │   │               ├── controller/     (Controllers yahan)
│   │   │               ├── model/          (Entities yahan)
│   │   │               ├── repository/     (JPA Repos yahan)
│   │   │               ├── service/        (Logic yahan)
│   │   │               └── TaskApplication.java
│   │   └── resources/
│   │       ├── static/                     (CSS/JS yahan)
│   │       ├── templates/                  (HTML/Thymeleaf yahan)
│   │       └── application.properties       <-- (Variables wali file)
│   └── test/
├── .gitignore
├── mvnw
├── pom.xml                                 <-- (Sabse important file)
├── README.md                               <-- (Jo abhi banayi)
└── system.properties                       <-- (Java 21 wali file)
