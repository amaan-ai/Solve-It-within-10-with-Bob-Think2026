package gov.demo.citizen.service;

import gov.demo.citizen.model.Citizen;
import gov.demo.citizen.model.CitizenStatus;
import gov.demo.citizen.repository.CitizenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CitizenServiceTest {

    @Mock
    private CitizenRepository citizenRepository;

    @InjectMocks
    private CitizenService citizenService;

    @Test
    public void register_validCitizen_succeeds() {
        Citizen c = new Citizen();
        c.setNationalId("SGA1234567AB");
        c.setFullName("Test Citizen");

        when(citizenRepository.existsByNationalId("SGA1234567AB")).thenReturn(false);
        when(citizenRepository.save(c)).thenReturn(c);

        Citizen result = citizenService.register(c);
        assertEquals(CitizenStatus.ACTIVE, result.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void register_invalidNationalId_throwsException() {
        Citizen c = new Citizen();
        c.setNationalId("TOOSHORT");
        citizenService.register(c);
    }

    @Test(expected = IllegalStateException.class)
    public void register_duplicateNationalId_throwsException() {
        Citizen c = new Citizen();
        c.setNationalId("SGA1234567AB");
        when(citizenRepository.existsByNationalId("SGA1234567AB")).thenReturn(true);
        citizenService.register(c);
    }
}
