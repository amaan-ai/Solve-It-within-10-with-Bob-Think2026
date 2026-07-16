package gov.demo.citizen.service;

import gov.demo.citizen.model.*;
import gov.demo.citizen.repository.CitizenRepository;
import gov.demo.citizen.repository.ServiceRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Manages the lifecycle of service requests.
 *
 * Business rules enforced here:
 *  - Only ACTIVE citizens can raise service requests.
 *  - Status transitions must follow the defined workflow:
 *      SUBMITTED -> UNDER_REVIEW -> (PENDING_DOCUMENTS | APPROVED | REJECTED)
 *      PENDING_DOCUMENTS -> (UNDER_REVIEW | REJECTED)
 *  - resolvedAt is set automatically on APPROVED or REJECTED.
 *  - SLA target: 10 working days from submittedAt (tracked but not auto-enforced here).
 */
@Service
@Transactional
public class ServiceRequestService {

    private final ServiceRequestRepository requestRepository;
    private final CitizenRepository citizenRepository;

    public ServiceRequestService(ServiceRequestRepository requestRepository,
                                  CitizenRepository citizenRepository) {
        this.requestRepository = requestRepository;
        this.citizenRepository = citizenRepository;
    }

    public ServiceRequest submit(Long citizenId, ServiceType serviceType, String description) {
        Citizen citizen = citizenRepository.findById(citizenId)
            .orElseThrow(() -> new IllegalArgumentException("Citizen not found: " + citizenId));

        if (citizen.getStatus() != CitizenStatus.ACTIVE) {
            throw new IllegalStateException("Only ACTIVE citizens can submit service requests. Citizen status: " + citizen.getStatus());
        }

        ServiceRequest req = new ServiceRequest();
        req.setCitizen(citizen);
        req.setServiceType(serviceType);
        req.setDescription(description);
        req.setStatus(RequestStatus.SUBMITTED);
        return requestRepository.save(req);
    }

    public ServiceRequest updateStatus(Long requestId, RequestStatus newStatus, String officerName, String remarks) {
        ServiceRequest req = requestRepository.findById(requestId)
            .orElseThrow(() -> new IllegalArgumentException("Request not found: " + requestId));

        validateTransition(req.getStatus(), newStatus);

        req.setStatus(newStatus);
        if (officerName != null) req.setAssignedOfficer(officerName);
        if (remarks != null)     req.setRemarks(remarks);

        if (newStatus == RequestStatus.APPROVED || newStatus == RequestStatus.REJECTED) {
            req.setResolvedAt(LocalDateTime.now());
        }

        return requestRepository.save(req);
    }

    private void validateTransition(RequestStatus current, RequestStatus next) {
        boolean valid = false;
        switch (current) {
            case SUBMITTED:         valid = next == RequestStatus.UNDER_REVIEW; break;
            case UNDER_REVIEW:      valid = next == RequestStatus.PENDING_DOCUMENTS
                                         || next == RequestStatus.APPROVED
                                         || next == RequestStatus.REJECTED; break;
            case PENDING_DOCUMENTS: valid = next == RequestStatus.UNDER_REVIEW
                                         || next == RequestStatus.REJECTED; break;
            default:                valid = false;
        }
        if (!valid) {
            throw new IllegalStateException("Invalid status transition: " + current + " -> " + next);
        }
    }

    @Transactional(readOnly = true)
    public List<ServiceRequest> findByCitizenId(Long citizenId) {
        return requestRepository.findByCitizenId(citizenId);
    }

    @Transactional(readOnly = true)
    public List<ServiceRequest> findByStatus(RequestStatus status) {
        return requestRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public Optional<ServiceRequest> findById(Long id) {
        return requestRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new java.util.LinkedHashMap<>();
        for (RequestStatus s : RequestStatus.values()) {
            stats.put(s.name(), requestRepository.countByStatus(s));
        }
        return stats;
    }
}
