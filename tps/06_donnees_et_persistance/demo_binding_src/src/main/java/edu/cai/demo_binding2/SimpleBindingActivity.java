package edu.cai.demo_binding2;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import edu.cai.demo_binding2.bindable.Personne;
import edu.cai.demo_binding2.databinding.ClasseDeBindingGeneree;

public class SimpleBindingActivity extends AppCompatActivity {

    protected Personne personne;
    protected ClasseDeBindingGeneree binding;
    protected List<Personne> personnes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_binding);

        personne = new Personne("Latrume", "Bob");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_simple_binding);
        binding.setPersonne(personne);
        binding.setListener(new MonListener());
        personnes = Arrays.asList(
                new Personne("P1", "Nom 1"),
                new Personne("P2", "Nom 2")
        );
        binding.setListePersonnes(personnes);
    }

    public void onBtnChgNomPersonne(View view) {
        personne.setNom(personne.getNom() + (new Date().getTime() % 30) );
        personne.setPrenom(personne.getNom() + (new Date().getTime() % 98) );
        Toast.makeText(this, personne.getNom(), Toast.LENGTH_SHORT).show();
        binding.setPersonne(personne);
        //personnes.add(new Personne("ZZZ", "RRRR"));
    }

}
