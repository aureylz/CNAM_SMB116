package ip.cai.demo_room;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "table_utilisateur")
public class Utilisateur {

    @PrimaryKey
    @NonNull
    protected String username;

    protected String password;

    public Utilisateur() {}

    @Ignore
    public Utilisateur(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}


    @Override
    public String toString() {
        return "Utilisateur{"
                + "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
