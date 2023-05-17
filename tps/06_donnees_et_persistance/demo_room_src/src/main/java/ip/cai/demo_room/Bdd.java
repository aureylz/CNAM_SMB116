package ip.cai.demo_room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Utilisateur.class}, version = 1)
public abstract class Bdd extends RoomDatabase {

    public static final String DB_NAME = "demoRoom";

    public abstract UtilisateurDao getUtilisateurDao();
}
