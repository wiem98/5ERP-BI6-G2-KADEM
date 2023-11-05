package tn.esprit.spring.kaddem;/* package tn.esprit.spring.kaddem.services;*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EquipeServiceImplTest {

    @InjectMocks
    private tn.esprit.spring.kaddem.services.EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddEquipe() {
        Equipe equipe = new Equipe("test equipe");

        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);

        Equipe savedEquipe = equipeService.addEquipe(equipe);

        assertNotNull(savedEquipe);
        // Add more assertions as needed to check if the saved equipe is as expected.
    }

    @Test
    public void testRetrieveEquipe() {
        int equipeId = 1; // Initialize with an existing equipe ID for testing
        Equipe equipe = new Equipe("test equipe");

        Mockito.when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        Equipe retrievedEquipe = equipeService.retrieveEquipe(equipeId);

        assertNotNull(retrievedEquipe);
        // Add more assertions as needed to check if the retrieved equipe is as expected.
    }

 /* @Test
    public void testUpdateEquipe() {
        int equipeId = 1; // Initialize with an existing equipe ID for testing
        Equipe equipe =new Equipe("test equipe");

        Mockito.when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));
        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);

        Equipe updatedEquipe = equipeService.updateEquipe(equipe);

        assertNotNull(updatedEquipe);
        // Add more assertions as needed to check if the updated equipe is as expected.
    }
*/
    @Test
    public void testDeleteEquipe() {
        int equipeId = 1; // Initialize with an existing equipe ID for testing
        Equipe equipe = new Equipe("test equipe");

        Mockito.when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        equipeService.deleteEquipe(equipeId);

        // Verify that delete was called on the repository
        Mockito.verify(equipeRepository, Mockito.times(1)).delete(equipe);
    }
}
