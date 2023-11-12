package tn.esprit.kaddemproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootConfiguration
@ExtendWith(MockitoExtension.class)
public class ContratServiceImplTest {

    @InjectMocks
    private tn.esprit.spring.kaddem.services.ContratServiceImpl contratService ;

    @Mock
    private ContratRepository contratRepository;

    @BeforeEach
    public void openMocks() { MockitoAnnotations.openMocks(this);    }

    @Test
    public void testRetrieveContrat() {
        int contratID = 1; // Initialize with an existing equipe ID for testing
        Contrat contrat = new Contrat("test contrat");

        Mockito.when(contratRepository.findById(contratID)).thenReturn(Optional.of(contrat));

        Contrat retrievedContrat = contratService.retrieveContrat(contratID);

        assertNotNull(retrievedContrat);
        // Add more assertions as needed to check if the retrieved equipe is as expected.
    }
    @Test
    public void testRemoveContrat() {
        int contratId = 1; // Initialize with an existing equipe ID for testing
        Contrat contrat  = new Contrat("test contrat");

        Mockito.when(contratRepository.findById(contratId)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(contratId);

        // Verify that delete was called on the repository
        Mockito.verify(contratRepository, Mockito.times(1)).delete(contrat);
    }
    @Test
    public void testAddContrat() {
        Contrat  contrat  = new Contrat("test contrat");

        Mockito.when(contratRepository.save(Mockito.any(Contrat.class))).thenReturn(contrat);

        Contrat savedContrat = contratService.addContrat(contrat);

        assertNotNull(savedContrat);
        // Add more assertions as needed to check if the saved equipe is as expected.
    }

}




