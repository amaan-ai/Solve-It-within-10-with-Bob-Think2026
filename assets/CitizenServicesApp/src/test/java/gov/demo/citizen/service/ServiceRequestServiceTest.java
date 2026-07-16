package gov.demo.citizen.service;

import gov.demo.citizen.model.*;
import gov.demo.citizen.repository.CitizenRepository;
import gov.demo.citizen.repository.ServiceRequestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceRequestServiceTest {

    @Mock private ServiceRequestRepository requestRepository;
    @Mock private CitizenRepository citizenRepository;

    @InjectMocks
    private ServiceRequestService requestService;

    @Test(expected = IllegalStateException.class)
    public void submit_inactiveCitizen_throwsException() {
        Citizen c = new Citizen();
        c.setId(1L);
        c.setStatus(CitizenStatus.INACTIVE);
        when(citizenRepository.findById(1L)).thenReturn(Optional.of(c));
        requestService.submit(1L, ServiceType.PASSPORT_APPLICATION, "test");
    }

    @Test(expected = IllegalStateException.class)
    public void updateStatus_invalidTransition_throwsException() {
        ServiceRequest req = new ServiceRequest();
        req.setId(1L);
        req.setStatus(RequestStatus.SUBMITTED);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(req));
        // SUBMITTED -> APPROVED is invalid (must go through UNDER_REVIEW first)
        requestService.updateStatus(1L, RequestStatus.APPROVED, "Officer", "skipped review");
    }

    @Test
    public void updateStatus_validTransition_succeeds() {
        ServiceRequest req = new ServiceRequest();
        req.setId(1L);
        req.setStatus(RequestStatus.SUBMITTED);
        when(requestRepository.findById(1L)).thenReturn(Optional.of(req));
        when(requestRepository.save(req)).thenReturn(req);
        ServiceRequest updated = requestService.updateStatus(1L, RequestStatus.UNDER_REVIEW, "Officer Priya", null);
        assertEquals(RequestStatus.UNDER_REVIEW, updated.getStatus());
    }
}
