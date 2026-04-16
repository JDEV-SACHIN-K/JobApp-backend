# JobApp — Backend

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.1-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-6-6DB33F?style=flat-square&logo=spring-security&logoColor=white)](https://spring.io/projects/spring-security)
[![H2](https://img.shields.io/badge/H2-In--Memory-1E4C9A?style=flat-square)](https://www.h2database.com/)
[![Maven](https://img.shields.io/badge/Maven-Wrapper-C71A36?style=flat-square&logo=apache-maven&logoColor=white)](https://maven.apache.org/)

A Spring Boot REST API for a job posting platform, built to demonstrate practical backend engineering across API design, security, JPA persistence, and layered application architecture.

---

## Objective

This service is a portfolio-oriented backend that showcases:

- Clean Spring Boot application structure with layered separation
- Secured endpoint flow with Spring Security and BCrypt-based DAO authentication
- Relational persistence using Spring Data JPA and Hibernate
- Entity modeling including `@ElementCollection` for embedded collections
- API-first design consumed by a real React frontend
- Auto-loaded seed data for immediate usability out of the box

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.2.1 |
| Web | Spring Web |
| Persistence | Spring Data JPA + Hibernate |
| Security | Spring Security |
| Database (local) | H2 in-memory |
| Database (prod-ready) | PostgreSQL driver included |
| Utilities | Lombok |
| Build | Maven Wrapper |

---

## Project Structure

```text
src/main/java/com/project/jobapp/springbootrest
├── config
│   └── SecurityConfig.java
├── controller
│   └── JobRestController.java
├── model
│   ├── JobPost.java
│   ├── User.java
│   └── UserPrincipal.java
├── repo
│   ├── JobRepo.java
│   └── UserRepo.java
├── service
│   ├── JobService.java
│   ├── MyUserDetailsService.java
│   └── UserService.java
└── SpringBootRestApplication.java
```

---

## Domain Model

### `JobPost`

| Field | Type | Description |
|---|---|---|
| `postId` | Integer | Primary key |
| `postProfile` | String | Job title or role name |
| `postDesc` | String | Job description |
| `reqExperience` | Integer | Required years of experience |
| `postTechStack` | `List<String>` | Tech stack tags persisted via `@ElementCollection` |

### `User`

| Field | Type | Description |
|---|---|---|
| `id` | Integer | Primary key |
| `username` | String | Login identity |
| `password` | String | BCrypt-hashed password |

---

## Security Model

Authentication is handled through Spring Security with a `DaoAuthenticationProvider` and `BCryptPasswordEncoder`.

| Concern | Behavior |
|---|---|
| Auth mechanism | HTTP Basic |
| Password encoding | BCrypt |
| Public endpoints | `GET /jobPosts`, `GET /jobPost/{postId}`, `GET /jobPosts/keyword/{keyword}`, `/h2-console/**`, `/load` |
| Write endpoints | Currently open for demo flow; security pipeline is in place and can be tightened easily |
| Session policy | `STATELESS` |
| CORS | Local frontend origins whitelisted during development |

This keeps the application easy to demo while still demonstrating Spring Security integration, DAO authentication, and user lookup through `UserDetailsService`.

---

## Running Locally

**Prerequisite:** JDK 21

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Application URL: `http://localhost:8080`

---

## Database

The local profile runs against an in-memory H2 database with zero setup.

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create-drop
```

**H2 Console:** `http://localhost:8080/h2-console`

| Setting | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:testdb` |
| Username | `sa` |
| Password | *(blank)* |

Schema is created and dropped on each run. Seed data is loaded automatically at startup.

---

## Seed Data

The application seeds a realistic job dataset automatically through `CommandLineRunner`. That makes the API immediately usable for:

- Frontend integration
- Manual API exploration
- H2 console inspection
- Search demonstrations

No fixture import or manual setup is required.

---

## API Reference

### Job Endpoints

| Method | Endpoint | Auth Required | Description |
|---|---|---|---|
| `GET` | `/jobPosts` | No | List all job posts |
| `GET` | `/jobPost/{postId}` | No | Get a single job post by ID |
| `GET` | `/jobPosts/keyword/{keyword}` | No | Search by keyword |
| `POST` | `/jobPost` | Demo-open | Create a new job post |
| `PUT` | `/jobPost` | Demo-open | Update an existing job post |
| `DELETE` | `/jobPost/{postId}` | Demo-open | Delete a job post |
| `GET` | `/load` | No | Trigger seed data load |

### Example Response

```json
{
  "postId": 1,
  "postProfile": "Senior Java Developer",
  "postDesc": "Build scalable backend systems with Spring Boot and microservices architecture.",
  "reqExperience": 5,
  "postTechStack": ["Java", "Spring Boot", "Docker", "AWS", "Microservices"]
}
```

---

## Tests

```bash
./mvnw test
```

---

## Production Checklist

This codebase is production-shaped but currently configured for local demo. Before deploying:

- [ ] Replace H2 with PostgreSQL and persistent environment-based datasource config
- [ ] Externalize database URL, username, and password via environment variables
- [ ] Tighten write endpoint authorization rules
- [ ] Bootstrap users and hashed passwords through a proper migration or initializer
- [ ] Add Flyway or Liquibase for schema versioning
- [ ] Disable H2 console outside local development
- [ ] Add deployment-specific profile configuration

---

## Related

- **Frontend:** [JobApp Frontend](../jobapp-frontend/README.md)
