package gov.demo.citizen.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A service request submitted by a citizen (e.g. passport, benefit claim, licence renewal).
 * Business rule: SLA target is 10 working days from submittedAt.
 * Business rule: Only SUBMITTED requests can be moved to UNDER_REVIEW.
 * Business rule: Only UNDER_REVIEW or PENDING_DOCUMENTS requests can be APPROVED or REJECTED.
 * Business rule: resolvedAt must be set when status transitions to APPROVED or REJECTED.
 */
@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false)
    private ServiceType serviceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status = RequestStatus.SUBMITTED;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @Column(name = "assigned_officer")
    private String assignedOfficer;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @PrePersist
    public void prePersist() {
        this.submittedAt = LocalDateTime.now();
    }

    public ServiceRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Citizen getCitizen() { return citizen; }
    public void setCitizen(Citizen c) { this.citizen = c; }
    public ServiceType getServiceType() { return serviceType; }
    public void setServiceType(ServiceType t) { this.serviceType = t; }
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus s) { this.status = s; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime t) { this.submittedAt = t; }
    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime t) { this.resolvedAt = t; }
    public String getAssignedOfficer() { return assignedOfficer; }
    public void setAssignedOfficer(String o) { this.assignedOfficer = o; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String r) { this.remarks = r; }
}
