package gov.demo.citizen.repository;

import gov.demo.citizen.model.Citizen;
import gov.demo.citizen.model.CitizenStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    Optional<Citizen> findByNationalId(String nationalId);
    List<Citizen> findByStatus(CitizenStatus status);
    boolean existsByNationalId(String nationalId);
}
