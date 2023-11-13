package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DepartementServiceImplTest {

    @Mock
    DepartementRepository departementRepository;

    @InjectMocks
    DepartementServiceImpl departementService;

    Departement departement = new Departement(1, "Departement1");
    List<Departement> departementList = Arrays.asList(
            new Departement(1, "Departement1"),
            new Departement(2, "Departement2")
    );

    @Test
    void testRetrieveDepartement() {
        Mockito.when(departementRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(departement));

        Departement retrievedDepartement = departementService.retrieveDepartement(1);

        Assertions.assertNotNull(retrievedDepartement);
        Assertions.assertEquals(departement, retrievedDepartement);

        // Print the actual and expected values to the console
        System.out.println("Actual: " + retrievedDepartement);
        System.out.println("Expected: " + departement);
    }

    @Test
    void testRetrieveAllDepartements() {
        Mockito.when(departementRepository.findAll()).thenReturn(departementList);

        List<Departement> retrievedDepartements = departementService.retrieveAllDepartements();

        Assertions.assertNotNull(retrievedDepartements);
        Assertions.assertEquals(departementList, retrievedDepartements);

        // Print the actual and expected values to the console
        System.out.println("Actual: " + retrievedDepartements);
        System.out.println("Expected: " + departementList);
    }

    @Test
    void testAddDepartement() {
        Mockito.when(departementRepository.save(departement)).thenReturn(departement);

        Departement addedDepartement = departementService.addDepartement(departement);

        Assertions.assertNotNull(addedDepartement);
        Assertions.assertEquals(departement, addedDepartement);

        // Print the actual and expected values to the console
        System.out.println("Actual: " + addedDepartement);
        System.out.println("Expected: " + departement);
    }
}
