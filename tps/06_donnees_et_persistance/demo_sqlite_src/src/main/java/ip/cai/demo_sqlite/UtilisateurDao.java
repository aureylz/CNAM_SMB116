package ip.cai.demo_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour les utilisateur
 */
public class UtilisateurDao {

    public SQLiteDatabase db;

    /**
     * Ouverture de la BDD
     * @param context
     */
    public void open(Context context) {
        db = context.openOrCreateDatabase("bddDemo", context.MODE_PRIVATE, null);
        // Création des tables
        db.execSQL("DROP TABLE IF EXISTS Utilisateur;");
        db.execSQL("CREATE TABLE IF NOT EXISTS Utilisateur(Username VARCHAR, Password VARCHAR);");
    }

    /**
     * Création d'un utilisateur
     * @param utilisateur
     * @return
     */
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        db.execSQL("INSERT INTO Utilisateur VALUES(?, ?)",
                                            new String[]{utilisateur.getUsername(),
                                                        utilisateur.getPassword()
        });
        return retrieveUtilisateur(utilisateur.getUsername());
    }

    /**
     * Obtention d'un utilisateur par son nom
     * @param userName
     * @return
     */
    public Utilisateur retrieveUtilisateur(String userName) {
        Cursor resultset = db.rawQuery("SELECT * FROM Utilisateur WHERE username = ?", new String[]{userName});
        resultset.moveToFirst();
        return cursorToUtilisateur(resultset);
    }

    /**
     * Mise à jour d'un utilisateur
     * @param utilisateur
     */
    public void updateUtilisateur(Utilisateur utilisateur) {
        db.execSQL("UPDATE Utilisateur SET password = ? WHERE username = ?",
                        new String[]{utilisateur.getPassword(), utilisateur.getUsername()}
        );
    }

    /**
     * Suppression d'un utilisateur
     * @param utilisateur
     */
    public void deleteUtilisateur(Utilisateur utilisateur) {
        db.execSQL("DELETE FROM Utilisateur WHERE username = ?",
                        new String[]{utilisateur.getUsername()}
        );
    }

    /**
     * Retourne la liste de tous les utilisateurs
     * @return
     */
    public List<Utilisateur> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Cursor resultset = db.rawQuery("SELECT * FROM Utilisateur", null);
        resultset.moveToFirst();
        while(!resultset.isAfterLast()) {
            utilisateurs.add(cursorToUtilisateur(resultset));
            resultset.moveToNext();
        }
        return utilisateurs;
    }

    /**
     * Fonction pour instancier une entité utilisateur
     * à partir d'un tuple pointé par un Curseur ouvert
     * @param cursor
     * @return
     */
    private Utilisateur cursorToUtilisateur(Cursor cursor) {
        if(cursor.isBeforeFirst() || cursor.isAfterLast() || cursor.isClosed()) {
            return null;
        } else {
            String username = cursor.getString(0);
            String password = cursor.getString(1);
            Utilisateur utilisateur = new Utilisateur(username, password);
            return  utilisateur;
        }
    }

    /**
     * Fermeture de la Bdd
     */
    public void close() {
        db.close();
    }
}
