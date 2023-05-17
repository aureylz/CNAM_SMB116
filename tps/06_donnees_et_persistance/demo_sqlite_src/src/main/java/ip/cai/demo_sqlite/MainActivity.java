package ip.cai.demo_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UtilisateurDao utilisateurDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoPersistance();
    }

    private void demoPersistance() {
        utilisateurDao = new UtilisateurDao();
        utilisateurDao.open(this);

        System.out.println("*** Création ***");
        for(int i = 0; i < 10; i++) {
            utilisateurDao.createUtilisateur(new Utilisateur("User" + i, "Secret" + i));
        }

        List<Utilisateur> utilisateurs = utilisateurDao.getAllUtilisateurs();
        listerUtilisateurs(utilisateurs);

        System.out.println("*** Suppression ***");
        utilisateurDao.deleteUtilisateur(utilisateurs.get(0));

        utilisateurs = utilisateurDao.getAllUtilisateurs();
        listerUtilisateurs(utilisateurs);

        System.out.println("*** Update ***");
        utilisateurs.get(0).setPassword("terces");
        utilisateurDao.updateUtilisateur(utilisateurs.get(0));

        utilisateurs = utilisateurDao.getAllUtilisateurs();
        listerUtilisateurs(utilisateurs);
    }

    private void listerUtilisateurs(List<Utilisateur> utilisateurs) {
        System.out.println("*** Liste ***");
        for(Utilisateur utilisateur : utilisateurs) {
            System.out.println(utilisateur);
        }
    }
}
