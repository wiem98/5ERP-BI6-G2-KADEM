import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.kaddem.KaddemApplication;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest(classes = KaddemApplication.class)
@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("test")
public class EtudiantServiceTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    public void setup() {
        // L'initialisation des mocks est gérée par MockitoExtension.
    }

    @Test
    public void testRetrieveAllEtudiants() {
        List<Etudiant> fakeEtudiants = Arrays.asList(
                new Etudiant("Alice", "Smith"),
                new Etudiant("Bob", "Johnson"),
                new Etudiant("Charlie", "Brown")
        );

        Mockito.when(etudiantRepository.findAll()).thenReturn(fakeEtudiants);

        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(fakeEtudiants.size(), etudiants.size());
    }


    @Test
    public void testAddEtudiant() {
        Etudiant etudiant = new Etudiant("Nom", "Prenom");
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);

        assertNotNull(savedEtudiant);
        assertEquals("Nom", savedEtudiant.getNomE());
        assertEquals("Prenom", savedEtudiant.getPrenomE());
    }


}
