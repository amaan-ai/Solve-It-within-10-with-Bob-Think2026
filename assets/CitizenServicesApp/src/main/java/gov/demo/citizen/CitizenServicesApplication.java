package gov.demo.citizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CitizenServicesApp — A Public Sector Citizen Services Portal
 *
 * Demonstrates a typical government agency backend: citizen registry,
 * multi-type service requests, officer workflow, and SLA tracking.
 *
 * Built with Java 8 + Spring Boot 2.7 to simulate a legacy government system
 * that is a candidate for modernisation to Java 21.
 *
 * Run with: mvn spring-boot:run
 * H2 console available at: http://localhost:8080/h2-console
 */
@SpringBootApplication
public class CitizenServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(CitizenServicesApplication.class, args);
    }
}
