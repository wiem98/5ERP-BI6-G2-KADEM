package tn.esprit.spring.kaddem.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Contrat implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idContrat;
    @Temporal(TemporalType.DATE)
    private Date dateDebutContrat;
    @Temporal(TemporalType.DATE)
    private Date dateFinContrat;
    @Enumerated(EnumType.STRING)
    private Specialite specialite;
    private Boolean archive;
    private Integer montantContrat;
    @ManyToOne(cascade = CascadeType.ALL)
    private Etudiant etudiant;

    public Contrat(Date dateDebutContrat, Date dateFinContrat, Specialite specialite, Boolean archive,
                   Integer montantContrat) {
        super();
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }

    public Contrat(Integer idContrat, Date dateDebutContrat, Date dateFinContrat, Specialite specialite,
                   Boolean archive, Integer montantContrat) {
        super();
        this.idContrat = idContrat;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.specialite = specialite;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }

    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }

    public void setDateDebutContrat(Date dateDebutContrat) {
        this.dateDebutContrat = dateDebutContrat;
    }

    public void setDateFinContrat(Date dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public void setMontantContrat(Integer montantContrat) {
        this.montantContrat = montantContrat;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


}
