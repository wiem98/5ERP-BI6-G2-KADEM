package tn.esprit.spring.kaddem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailEquipe implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;
    @OneToOne(mappedBy="detailEquipe")
    private Equipe equipe;

    public DetailEquipe(Integer salle, String thematique) {
        super();
        this.salle = salle;
        this.thematique = thematique;
    }

    public DetailEquipe(Integer idDetailEquipe, Integer salle, String thematique) {
        super();
        this.idDetailEquipe = idDetailEquipe;
        this.salle = salle;
        this.thematique = thematique;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public void setIdDetailEquipe(Integer idDetailEquipe) {
        this.idDetailEquipe = idDetailEquipe;
    }

    public void setSalle(Integer salle) {
        this.salle = salle;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }

}
