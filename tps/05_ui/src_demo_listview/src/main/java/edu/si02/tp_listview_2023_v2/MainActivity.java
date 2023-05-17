package edu.si02.tp_listview_2023_v2;

import android.app.Person;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * Adaptateur d'une liste personne pour son affichage dans la listVIew
     */
    public class PersonneAdapter extends BaseAdapter {

        private List<Personne> personnes;

        public PersonneAdapter(List<Personne> personnes) {
            this.personnes = personnes;
        }

        /**
         * Nombre d'éléments dans la liste de données
         * @return
         */
        @Override
        public int getCount() {
            return personnes.size();
        }

        /**
         *
         * @param position
         * @return
         */
        @Override
        public Personne getItem(int position) {
            return personnes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class CacheView {
            TextView textViewNom;
            TextView textViewPrenom;
            ImageView imgVIiwGenre;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Log.i("CONVERT_VIEW", position + " -> " + convertView);

            // La personne à afficher
            Personne personne = getItem(position);

            // Créer la vue pour l'item de personne
            View itemPersonneView;
            if(convertView != null) {
                itemPersonneView = convertView;
            } else {
                // Vue à créer
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemPersonneView = layoutInflater.inflate(R.layout.item_personne, parent, false);
                // Création structure de cache
                CacheView newCacheView = new CacheView();
                newCacheView.textViewNom = itemPersonneView.findViewById(R.id.nom);
                newCacheView.textViewPrenom = itemPersonneView.findViewById(R.id.prenom);
                newCacheView.imgVIiwGenre = itemPersonneView.findViewById(R.id.imgGenre);
                itemPersonneView.setTag(newCacheView);
            }

            // Récupération du CacheView associé à la vue
            CacheView cacheView = (CacheView) itemPersonneView.getTag();

            // Alimentation des champs
            cacheView.textViewNom.setText(personne.getNom());
            cacheView.textViewPrenom.setText(personne.getPrenom());
            Drawable drawableGenre = personne.getGenre() == Personne.Genre.MASCULIN ?
                                        getDrawable(R.drawable.masculin)
                                        :getDrawable(R.drawable.feminin);
            cacheView.imgVIiwGenre.setImageDrawable(drawableGenre);

            return itemPersonneView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewPersonne = findViewById(R.id.listePersonne);
        listViewPersonne.setAdapter(new PersonneAdapter(Personne.getPersonnes()));

    }
}