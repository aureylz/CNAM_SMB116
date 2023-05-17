package ip.cai.tp02__appcommunication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

public class ConfigActivity extends AppCompatActivity {

    /**
     * Valeur seuil de batterie
     */
    private int seuil;

    private SeekBar sbSeuil;
    private TextView txtValSeuil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        // Lecture valeur dans les préférences
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        seuil = preferences.getInt("seuil", 0);

        // Affectation valeur originale au widget
        sbSeuil = findViewById(R.id.sbSeuil);
        sbSeuil.setProgress(seuil);
        txtValSeuil = findViewById(R.id.txtValSeuil);
        updateSeuil(seuil);

        // Sur changement valeur de la barre: application valeur au seuil
        sbSeuil.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //updateSeuil(progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateSeuil(seekBar.getProgress());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_validconfig, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.mnuItemValid) {
            // Sauvegarde nouvelle valeur de seuil dans les préférences
            SharedPreferences preferences = getSharedPreferences("preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("seuil", seuil);
            editor.commit();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateSeuil(int seuil) {
        this.seuil = seuil;
        txtValSeuil.setText("" + seuil + "%");
    }
}
