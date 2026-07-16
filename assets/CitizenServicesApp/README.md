# CitizenServicesApp

A **Public Sector Citizen Services Portal** — a demo legacy Java 8 application that simulates a typical government agency backend.

## What It Does
- **Citizen Registry**: Register citizens with a unique 12-character National ID
- **Service Requests**: Submit and track government service requests (passport, tax filing, benefits, etc.)
- **Officer Workflow**: Status lifecycle with validation — SUBMITTED → UNDER_REVIEW → APPROVED/REJECTED
- **SLA Tracking**: Records submission and resolution timestamps for 10-day SLA monitoring
- **Dashboard API**: Aggregate request counts per status

## Technology Stack
- **Java 8** (legacy LTS — candidate for Java 21 modernisation)
- **Spring Boot 2.7**
- **Spring Data JPA** + **H2** in-memory database
- **Maven**

## Running the Application
```bash
mvn spring-boot:run
```
- API base: http://localhost:8080
- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:citizendb`)
- Health check: http://localhost:8080/actuator/health

## Key API Endpoints
| Method | Path | Description |
|--------|------|-------------|
| GET | /api/citizens | List all citizens |
| POST | /api/citizens | Register a new citizen |
| GET | /api/citizens/lookup?nationalId=XXX | Look up by National ID |
| POST | /api/requests | Submit a service request |
| GET | /api/requests/dashboard | Stats per request status |
| PATCH | /api/requests/{id}/status | Advance request through workflow |

## Project Structure
```
CitizenServicesApp/
├── src/main/java/gov/demo/citizen/
│   ├── CitizenServicesApplication.java   ← Entry point
│   ├── model/                            ← JPA entities & enums
│   ├── repository/                       ← Spring Data repositories
│   ├── service/                          ← Business logic & rules
│   ├── controller/                       ← REST API layer
│   └── config/                           ← Demo data seeder
├── src/test/                             ← JUnit 4 unit tests
├── src/main/resources/application.properties
└── pom.xml
```

## Business Rules (documented in source)
- National ID: exactly 12 uppercase alphanumeric characters, must be unique
- Only ACTIVE citizens can submit service requests
- Status workflow: SUBMITTED → UNDER_REVIEW → PENDING_DOCUMENTS | APPROVED | REJECTED
- resolvedAt is auto-set when a request reaches APPROVED or REJECTED
- SLA target: 10 working days from submittedAt

---
*Demo codebase for IBM Bob AI — Public Sector modernisation scenario (Java 8 → Java 21)*
