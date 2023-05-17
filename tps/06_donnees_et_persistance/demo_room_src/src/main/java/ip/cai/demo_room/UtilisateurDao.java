package ip.cai.demo_room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public abstract class UtilisateurDao {

    @Insert
    public abstract void create(Utilisateur... utilisateurs);

    @Update
    public abstract void update(Utilisateur... utilisateurs);

    @Delete
    public abstract void delete(Utilisateur... utilisateurs);

    @Query("SELECT * FROM table_utilisateur WHERE username = :parmUsername")
    public abstract Utilisateur getUtilisateurs(String parmUsername);

    @Query("SELECT * FROM table_utilisateur")
    public abstract List<Utilisateur> getAllUtilisateurs();

}
