package gov.demo.citizen.service;

import gov.demo.citizen.model.Citizen;
import gov.demo.citizen.model.CitizenStatus;
import gov.demo.citizen.repository.CitizenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Business logic for citizen registration and management.
 *
 * Business rules enforced here:
 *  - National ID must be exactly 12 alphanumeric characters.
 *  - National ID must be unique across the registry.
 *  - Only ACTIVE citizens may submit service requests (enforced in ServiceRequestService).
 */
@Service
@Transactional
public class CitizenService {

    private final CitizenRepository citizenRepository;

    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public Citizen register(Citizen citizen) {
        if (citizen.getNationalId() == null || !citizen.getNationalId().matches("[A-Z0-9]{12}")) {
            throw new IllegalArgumentException("National ID must be exactly 12 uppercase alphanumeric characters.");
        }
        if (citizenRepository.existsByNationalId(citizen.getNationalId())) {
            throw new IllegalStateException("A citizen with this National ID is already registered.");
        }
        citizen.setStatus(CitizenStatus.ACTIVE);
        return citizenRepository.save(citizen);
    }

    @Transactional(readOnly = true)
    public Optional<Citizen> findByNationalId(String nationalId) {
        return citizenRepository.findByNationalId(nationalId);
    }

    @Transactional(readOnly = true)
    public List<Citizen> findAll() {
        return citizenRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Citizen> findById(Long id) {
        return citizenRepository.findById(id);
    }

    public Citizen updateStatus(Long id, CitizenStatus newStatus) {
        Citizen citizen = citizenRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Citizen not found: " + id));
        citizen.setStatus(newStatus);
        return citizenRepository.save(citizen);
    }
}
