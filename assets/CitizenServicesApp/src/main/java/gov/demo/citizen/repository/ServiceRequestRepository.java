package gov.demo.citizen.repository;

import gov.demo.citizen.model.RequestStatus;
import gov.demo.citizen.model.ServiceRequest;
import gov.demo.citizen.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByCitizenId(Long citizenId);
    List<ServiceRequest> findByStatus(RequestStatus status);
    List<ServiceRequest> findByServiceType(ServiceType serviceType);

    @Query("SELECT sr FROM ServiceRequest sr WHERE sr.citizen.nationalId = :nationalId ORDER BY sr.submittedAt DESC")
    List<ServiceRequest> findByCitizenNationalId(String nationalId);

    @Query("SELECT COUNT(sr) FROM ServiceRequest sr WHERE sr.status = :status")
    long countByStatus(RequestStatus status);
}
