package tn.esprit.spring.kaddem.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final ContratRepository contratRepository;
    private final EquipeRepository equipeRepository;
    private final DepartementRepository departementRepository;

    public List<Etudiant> retrieveAllEtudiants() {
        return (List<Etudiant>) etudiantRepository.findAll();
    }

    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElseThrow(() -> new IllegalArgumentException("Invalid etudiant Id:" + idEtudiant));
    }

    public void removeEtudiant(Integer idEtudiant) {
        Etudiant e = retrieveEtudiant(idEtudiant);
        etudiantRepository.delete(e);
    }

    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        Departement departement = departementRepository.findById(departementId).orElse(null);
        if (etudiant != null) {
            etudiant.setDepartement(departement);
        }
        if (etudiant != null) {
            etudiantRepository.save(etudiant);
        }
    }

    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
        Contrat c = contratRepository.findById(idContrat).orElse(null);
        Equipe eq = equipeRepository.findById(idEquipe).orElse(null);
        if (c != null) {
            c.setEtudiant(e);
        }
        if (eq != null) {
            eq.getEtudiants().add(e);
        }
        return e;
    }

    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
        return etudiantRepository.findEtudiantsByDepartement_IdDepart((idDepartement));
    }
}
