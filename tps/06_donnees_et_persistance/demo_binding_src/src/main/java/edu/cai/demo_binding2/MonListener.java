package edu.cai.demo_binding2;

import android.view.View;
import android.widget.Toast;

/**
 * Classe Listener
 */
public class MonListener {
    public void clickSurTexte(View view) {
        Toast.makeText(view.getContext(),
                "clickSurTexte",
                Toast.LENGTH_SHORT).show();
    }
}


