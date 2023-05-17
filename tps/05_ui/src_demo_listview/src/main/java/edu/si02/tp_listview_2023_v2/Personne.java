package edu.si02.tp_listview_2023_v2;

import java.util.ArrayList;
import java.util.List;

public class Personne {

    public static List<Personne> personnes;

    /**
     * Fournit une liste de personnes
     *
     * @return
     */
    public static List<Personne> getPersonnes() {
        if (personnes == null) {
            personnes = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                personnes.add(
                        new Personne("NomPers" + i,
                                "Prenom" + i,
                                Math.random() * 1000 > 800 ? Genre.MASCULIN : Genre.FEMININ
                        )
                );
            }
        }
        return personnes;
    }

    public enum Genre {
        MASCULIN,
        FEMININ
    }

    private String nom;
    private String prenom;
    private Genre genre;

    public Personne(String nom, String prenom, Genre genre) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
