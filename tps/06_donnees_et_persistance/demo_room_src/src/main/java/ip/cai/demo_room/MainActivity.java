package ip.cai.demo_room;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoPersistanceRoom();
    }

    private void demoPersistanceRoom() {
        // Supprime la Bdd (POur Test)
        this.deleteDatabase(Bdd.DB_NAME);

        // Obtention de la Bdd (création éventuelle)
        Bdd database = Room.databaseBuilder(this, Bdd.class, Bdd.DB_NAME)
                .allowMainThreadQueries() // En Production on se l'interdit
                .build();
        // Obtention du DAO
        UtilisateurDao utilisateurDao = database.getUtilisateurDao();

        // Tout est prêt pour exploiter ROOM
        // ...

        System.out.println("*** Création ***");
        for(int i = 0; i < 10; i++) {
            utilisateurDao.create(new Utilisateur("User" + i, "Secret" + i));
        }

        List<Utilisateur> utilisateurs = utilisateurDao.getAllUtilisateurs();
        listerUtilisateurs(utilisateurs);

        System.out.println("*** Suppression ***");
        utilisateurDao.delete(utilisateurs.get(0));

        utilisateurs = utilisateurDao.getAllUtilisateurs();
        listerUtilisateurs(utilisateurs);

        System.out.println("*** Update ***");
        utilisateurs.get(0).setPassword("terces");
        utilisateurDao.update(utilisateurs.get(0));

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
