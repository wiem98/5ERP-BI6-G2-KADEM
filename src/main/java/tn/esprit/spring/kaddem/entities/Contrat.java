package tn.esprit.spring.kaddem.entities;

import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

@Entity
@ToString
public class Contrat implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idContrat;

    private LocalDate dateDebutContrat;

    private LocalDate dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private Boolean archive;
    private Integer montantContrat;
    @ManyToOne(cascade = CascadeType.ALL)
    private Etudiant etudiant;

    public Contrat() {
        // TODO Auto-generated constructor stub
    }

    public Contrat(LocalDate dateDebutContrat,LocalDate dateFinContrat, Specialite specialite, Boolean archive,
                   Integer montantContrat) {
        super();
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }

    public Contrat(Integer idContrat, LocalDate dateDebutContrat, LocalDate dateFinContrat, Specialite specialite,
                   Boolean archive, Integer montantContrat) {
        super();
        this.idContrat = idContrat;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }



    public Contrat(String test_contrat) {

    }

    public Integer getIdContrat() {
        return idContrat;
    }
    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }
    public LocalDate getDateDebutContrat() {
        return dateDebutContrat;
    }
    public void setDateDebutContrat(LocalDate dateDebutContrat) {
        this.dateDebutContrat = dateDebutContrat;
    }
    public LocalDate getDateFinContrat() {
        return dateFinContrat;
    }
    public void setDateFinContrat(LocalDate dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }
    public Specialite getSpecialite() {
        return specialite;
    }
    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }
    public Boolean getArchive() {
        return archive;
    }
    public void setArchive(Boolean archive) {
        this.archive = archive;
    }
    public Integer getMontantContrat() {
        return montantContrat;
    }
    public void setMontantContrat(Integer montantContrat) {
        this.montantContrat = montantContrat;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


}
