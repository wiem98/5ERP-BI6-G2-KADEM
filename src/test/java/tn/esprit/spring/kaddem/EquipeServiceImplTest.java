package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllEquipes() {
        List<Equipe> equipes = new ArrayList<>();
        // Add some sample equipes to the list
        equipes.add(new Equipe("FC Raoued",  Niveau.SENIOR));
        equipes.add(new Equipe("FC Tela",  Niveau.SENIOR));

        // Mock the behavior of the repository
        when(equipeRepository.findAll()).thenReturn(equipes);

        List<Equipe> retrievedEquipes = equipeService.retrieveAllEquipes();

        assertNotNull(retrievedEquipes);
        assertEquals(2, retrievedEquipes.size());
    }

    @Test
    public void testAddEquipe() {
        Equipe newEquipe = new Equipe("FC Raoued",  Niveau.SENIOR);

        // Mock the behavior of the repository
        when(equipeRepository.save(newEquipe)).thenReturn(newEquipe);

        Equipe addedEquipe = equipeService.addEquipe(newEquipe);

        assertNotNull(addedEquipe);
        assertEquals(newEquipe.getIdEquipe(), addedEquipe.getIdEquipe());
        assertEquals(newEquipe.getNomEquipe(), addedEquipe.getNomEquipe());
    }

    @Test
    public void testUpdateEquipe() {
        Equipe existingEquipe = new Equipe("9alebes", Niveau.JUNIOR);

        // Mock the behavior of the repository
        when(equipeRepository.save(existingEquipe)).thenReturn(existingEquipe);

        Equipe updatedEquipe = equipeService.updateEquipe(existingEquipe);

        assertNotNull(updatedEquipe);
        assertEquals(existingEquipe.getIdEquipe(), updatedEquipe.getIdEquipe());
        assertEquals(existingEquipe.getNomEquipe(), updatedEquipe.getNomEquipe());
    }

    @Test
    public void testRetrieveEquipe() {
        // Arrange
        Integer equipeId = 1;
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(equipeId);

        // Mock the behavior of the repository
        when(equipeRepository.findById(equipeId)).thenReturn(Optional.of(equipe));

        // Act
        Equipe retrievedEquipe = equipeService.retrieveEquipe(equipeId);

        // Assert
        assertEquals(equipe, retrievedEquipe);

        // Verify that the repository's findById method was called once with the provided equipeId
        verify(equipeRepository, times(1)).findById(equipeId);
    }

    // You can add test cases for other methods, including evoluerEquipes, based on your specific requirements.
}
