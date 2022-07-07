package com.example.entrevueSpringBoot.domains;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="films")

public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String titre;
    @Column
    private String description;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="films_id")
    private List<Acteur> acteurs;

    public Film(String titre, String description, List<Acteur> acteurs) {
        this.titre = titre;
        this.description = description;
        this.acteurs = acteurs;
    }

    public Film() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(List<Acteur> acteurs) {
        this.acteurs = acteurs;
    }
}
