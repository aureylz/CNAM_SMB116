package edu.cai.demo_binding2;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import edu.cai.demo_binding2.bindable.Personne;
import edu.cai.demo_binding2.bindable.PersonneObservable;
import edu.cai.demo_binding2.databinding.ClasseDeBindingBidirectionelGeneree;

public class BidirectionalBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidirectional_binding);

        final PersonneObservable personne = new PersonneObservable("Latrume", "Bob");

        ClasseDeBindingBidirectionelGeneree binding = DataBindingUtil.setContentView(this, R.layout.activity_bidirectional_binding);
        binding.setPersonne(personne);

        EditText txtNom = findViewById(R.id.txtNomBid);
        txtNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(BidirectionalBindingActivity.this, personne.getNom(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Toast.makeText(BidirectionalBindingActivity.this, personne.getNom(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(BidirectionalBindingActivity.this, personne.getNom(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
