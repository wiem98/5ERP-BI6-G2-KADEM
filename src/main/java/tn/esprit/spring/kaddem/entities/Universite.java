package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUniv;
    private String nomUniv;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Departement> departements;

    public Universite(String nomUniv) {
        super();
        this.nomUniv = nomUniv;
    }

    public Universite(Integer idUniv, String nomUniv) {
        super();
        this.idUniv = idUniv;
        this.nomUniv = nomUniv;
    }

    public void setDepartements(Set<Departement> departements) {
        this.departements = departements;
    }

    public void setIdUniv(Integer idUniv) {
        this.idUniv = idUniv;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

}
