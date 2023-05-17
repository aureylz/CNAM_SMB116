package ip.cai.demo_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BddDemoOpenHelper extends SQLiteOpenHelper {

    public static final String NOM_BDD = "bddDemo";
    public static final int VERSION_BDD = 1;

    public BddDemoOpenHelper(Context context) {
        super(context, NOM_BDD, null, VERSION_BDD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Méthode appelée lorsqu'il faut créer la Bdd
        db.execSQL("DROP TABLE IF EXISTS Utilisateur;");
        db.execSQL("CREATE TABLE IF NOT EXISTS Utilisateur(Username VARCHAR,Password VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Méthode appelée lorsque la version de la Bdd a changé
        // pour mettre à jour la structure de la Bdd
        //...
    }
}
