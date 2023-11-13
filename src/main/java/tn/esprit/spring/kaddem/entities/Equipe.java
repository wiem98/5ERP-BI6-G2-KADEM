package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    //@ManyToMany(mappedBy="equipes")
    @ManyToMany(cascade = CascadeType.ALL)

    @JsonIgnore
    private Set<Etudiant> etudiants;
    @OneToOne
    private DetailEquipe detailEquipe;

    public Equipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public Equipe(String nomEquipe, Niveau niveau) {
        super();
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }

    public Equipe(Integer idEquipe, String nomEquipe, Niveau niveau) {
        super();
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }

    public Equipe(String nomEquipe, Niveau niveau, Set<Etudiant> etudiants, DetailEquipe detailEquipe) {
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
        this.etudiants = etudiants;
        this.detailEquipe = detailEquipe;
    }

    public void setEtudiants(Set<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void setDetailEquipe(DetailEquipe detailEquipe) {
        this.detailEquipe = detailEquipe;
    }

    public void setIdEquipe(Integer idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

}
