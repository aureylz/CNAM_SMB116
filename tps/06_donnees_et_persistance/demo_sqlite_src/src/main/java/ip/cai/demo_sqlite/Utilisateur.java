package ip.cai.demo_sqlite;

/**
 * Entité Utilisateur
 */
public class Utilisateur {

    protected String username;
    protected String password;

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
