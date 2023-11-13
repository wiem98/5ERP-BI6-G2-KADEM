package tn.esprit.spring.kaddem.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService {

    private final EquipeRepository equipeRepository;


    public List<Equipe> retrieveAllEquipes() {
        return (List<Equipe>) equipeRepository.findAll();
    }

    public Equipe addEquipe(Equipe e) {
        return (equipeRepository.save(e));
    }

    public void deleteEquipe(Integer idEquipe) {
        Equipe e = retrieveEquipe(idEquipe);
        equipeRepository.delete(e);
    }

    public Equipe retrieveEquipe(Integer equipeId) {
        return equipeRepository.findById(equipeId).orElseThrow(() -> new IllegalArgumentException("Invalid equipe Id:" + equipeId));
    }

    public Equipe updateEquipe(Equipe e) {
        return (equipeRepository.save(e));
    }

    public void evoluerEquipes() {
        List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
        for (Equipe equipe : equipes) {
            if ((equipe.getNiveau().equals(Niveau.JUNIOR)) || (equipe.getNiveau().equals(Niveau.SENIOR))) {
                List<Etudiant> etudiants = (List<Etudiant>) equipe.getEtudiants();
                int nbEtudiantsAvecContratsActifs = 0;
                for (Etudiant etudiant : etudiants) {
                    Set<Contrat> contrats = etudiant.getContrats();
                    for (Contrat contrat : contrats) {
                        Date dateSysteme = new Date();
                        long differenceInTime = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
                        long differenceInYears = (differenceInTime / (1000L * 60 * 60 * 24 * 365));
                        if ((!contrat.getArchive()) && (differenceInYears > 1)) {
                            nbEtudiantsAvecContratsActifs++;
                            break;
                        }
                        if (nbEtudiantsAvecContratsActifs >= 3) break;
                    }
                }
                if (nbEtudiantsAvecContratsActifs >= 3) {
                    if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
                        equipe.setNiveau(Niveau.SENIOR);
                        equipeRepository.save(equipe);
                        break;
                    }
                    if (equipe.getNiveau().equals(Niveau.SENIOR)) {
                        equipe.setNiveau(Niveau.EXPERT);
                        equipeRepository.save(equipe);
                        break;
                    }
                }
            }

        }

    }
}