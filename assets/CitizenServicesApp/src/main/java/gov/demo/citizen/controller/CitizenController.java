package gov.demo.citizen.controller;

import gov.demo.citizen.model.Citizen;
import gov.demo.citizen.model.CitizenStatus;
import gov.demo.citizen.service.CitizenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST API for citizen registration and profile management.
 *
 * GET  /api/citizens                          - list all citizens
 * POST /api/citizens                          - register a new citizen
 * GET  /api/citizens/{id}                     - get citizen by internal ID
 * GET  /api/citizens/lookup?nationalId=XXX    - look up by national ID
 * PATCH /api/citizens/{id}/status             - update citizen status
 */
@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    private final CitizenService citizenService;

    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping
    public List<Citizen> list() {
        return citizenService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody Citizen citizen) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(citizenService.register(citizen));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return citizenService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lookup")
    public ResponseEntity<?> lookupByNationalId(@RequestParam String nationalId) {
        return citizenService.findByNationalId(nationalId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            CitizenStatus status = CitizenStatus.valueOf(body.get("status"));
            return ResponseEntity.ok(citizenService.updateStatus(id, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
