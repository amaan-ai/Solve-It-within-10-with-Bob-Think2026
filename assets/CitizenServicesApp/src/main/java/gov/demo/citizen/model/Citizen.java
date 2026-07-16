package gov.demo.citizen.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a registered citizen in the national identity registry.
 * Business rule: nationalId must be unique and exactly 12 characters.
 * Business rule: A citizen can only submit service requests when status is ACTIVE.
 */
@Entity
@Table(name = "citizens")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "national_id", unique = true, nullable = false, length = 12)
    private String nationalId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CitizenStatus status = CitizenStatus.ACTIVE;

    public Citizen() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dob) { this.dateOfBirth = dob; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public CitizenStatus getStatus() { return status; }
    public void setStatus(CitizenStatus status) { this.status = status; }
}
