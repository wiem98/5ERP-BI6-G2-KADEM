package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ContratServiceImplTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllContrats() {
        List<Contrat> contrats = new ArrayList<>();
        // Add some sample contrats to the list
        contrats.add(new Contrat(new Date(),Date.from(Instant.now().plus(Duration.ofDays(30))), Specialite.RESEAUX,true, 1500));
        contrats.add(new Contrat(new Date(),Date.from(Instant.now().plus(Duration.ofDays(30))), Specialite.RESEAUX,false, 1500));

        // Mock the behavior of the repository
        when(contratRepository.findAll()).thenReturn(contrats);

        List<Contrat> retrievedContrats = contratService.retrieveAllContrats();

        assertNotNull(retrievedContrats);
        assertEquals(2, retrievedContrats.size());
    }

    @Test
    public void testAddContrat() {
        Contrat newContrat = new Contrat(new Date(),Date.from(Instant.now().plus(Duration.ofDays(30))), Specialite.RESEAUX,true, 1500);

        // Mock the behavior of the repository
        when(contratRepository.save(newContrat)).thenReturn(newContrat);

        Contrat addedContrat = contratService.addContrat(newContrat);

        assertNotNull(addedContrat);
        assertEquals(newContrat.getIdContrat(), addedContrat.getIdContrat());
        assertEquals(newContrat.getSpecialite(), addedContrat.getSpecialite());
    }

    // Add test cases for other methods like updateContrat, retrieveContrat, removeContrat, affectContratToEtudiant, nbContratsValides, retrieveAndUpdateStatusContrat, and getChiffreAffaireEntreDeuxDates.
}

