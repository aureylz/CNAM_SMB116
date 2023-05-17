package edu.cai.demo_binding2.bindable;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import edu.cai.demo_binding2.BR;

/**
 * Created by Philippe Palau on 27/01/2018.
 */

public class PersonneObservable extends BaseObservable {

    protected String nom;
    protected String prenom;

    public PersonneObservable() {
    }

    public PersonneObservable(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    @Bindable
    public String getNom() {
        return nom;
    }

    @Bindable
    public void setNom(String nom) {
        this.nom = nom;
        notifyPropertyChanged(BR.nom);
    }

    @Bindable
    public String getPrenom() {
        return prenom;
    }

    @Bindable
    public void setPrenom(String prenom) {
        this.prenom = prenom;
        notifyPropertyChanged(BR.prenom);
    }
}


