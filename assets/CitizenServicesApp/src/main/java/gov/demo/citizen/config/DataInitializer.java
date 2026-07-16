package gov.demo.citizen.config;

import gov.demo.citizen.model.*;
import gov.demo.citizen.repository.CitizenRepository;
import gov.demo.citizen.service.ServiceRequestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

/**
 * Seeds demo data on startup so the application is immediately explorable.
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedData(CitizenRepository citizenRepository,
                                      ServiceRequestService requestService) {
        return args -> {
            Citizen alice = new Citizen();
            alice.setNationalId("SGA1234567AB");
            alice.setFullName("Alice Tan Wei Ling");
            alice.setDateOfBirth(LocalDate.of(1985, 3, 12));
            alice.setAddress("15 Orchard Road, Singapore 238841");
            alice.setEmail("alice.tan@example.sg");
            alice.setPhone("+65 9123 4567");
            citizenRepository.save(alice);

            Citizen bob = new Citizen();
            bob.setNationalId("SGM9876543CD");
            bob.setFullName("Bob Lim Ah Kow");
            bob.setDateOfBirth(LocalDate.of(1972, 8, 25));
            bob.setAddress("88 Marina Bay, Singapore 018956");
            bob.setEmail("bob.lim@example.sg");
            bob.setPhone("+65 8765 4321");
            citizenRepository.save(bob);

            Citizen carol = new Citizen();
            carol.setNationalId("SGF2345678EF");
            carol.setFullName("Carol Rajendran");
            carol.setDateOfBirth(LocalDate.of(1990, 11, 5));
            carol.setAddress("42 Jurong West, Singapore 640042");
            carol.setEmail("carol.r@example.sg");
            carol.setPhone("+65 9988 7766");
            citizenRepository.save(carol);

            // Seed some service requests
            requestService.submit(alice.getId(), ServiceType.PASSPORT_APPLICATION, "Renew passport expiring next month");
            requestService.submit(alice.getId(), ServiceType.TAX_FILING, "Annual income tax filing for FY2023");
            ServiceRequest sr = requestService.submit(bob.getId(), ServiceType.SOCIAL_BENEFIT_CLAIM, "Applying for ComCare short-to-medium term assistance");
            requestService.updateStatus(sr.getId(), RequestStatus.UNDER_REVIEW, "Officer Priya", "Initial review started");
            requestService.submit(carol.getId(), ServiceType.VOTER_REGISTRATION, "First-time voter registration");
            requestService.submit(carol.getId(), ServiceType.DRIVING_LICENCE_RENEWAL, "Renew Class 3 driving licence");
        };
    }
}
