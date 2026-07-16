package gov.demo.citizen.controller;

import gov.demo.citizen.model.RequestStatus;
import gov.demo.citizen.model.ServiceRequest;
import gov.demo.citizen.model.ServiceType;
import gov.demo.citizen.service.ServiceRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST API for citizen service requests.
 *
 * POST /api/requests                             - submit a new request
 * GET  /api/requests/{id}                        - get request by ID
 * GET  /api/requests?citizenId=X                 - all requests for a citizen
 * GET  /api/requests?status=SUBMITTED            - filter by status
 * PATCH /api/requests/{id}/status                - update request status (officer workflow)
 * GET  /api/requests/dashboard                   - aggregate stats per status
 */
@RestController
@RequestMapping("/api/requests")
public class ServiceRequestController {

    private final ServiceRequestService requestService;

    public ServiceRequestController(ServiceRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<?> submit(@RequestBody Map<String, String> body) {
        try {
            Long citizenId   = Long.parseLong(body.get("citizenId"));
            ServiceType type = ServiceType.valueOf(body.get("serviceType"));
            String desc      = body.getOrDefault("description", "");
            ServiceRequest req = requestService.submit(citizenId, type, desc);
            return ResponseEntity.status(HttpStatus.CREATED).body(req);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return requestService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ServiceRequest> list(@RequestParam(required = false) Long citizenId,
                                     @RequestParam(required = false) String status) {
        if (citizenId != null) return requestService.findByCitizenId(citizenId);
        if (status != null)    return requestService.findByStatus(RequestStatus.valueOf(status));
        return requestService.findByStatus(RequestStatus.SUBMITTED);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            RequestStatus newStatus = RequestStatus.valueOf(body.get("status"));
            String officer = body.get("officer");
            String remarks = body.get("remarks");
            return ResponseEntity.ok(requestService.updateStatus(id, newStatus, officer, remarks));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/dashboard")
    public Map<String, Long> dashboard() {
        return requestService.getDashboardStats();
    }
}
