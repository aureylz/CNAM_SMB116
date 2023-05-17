package ip.cai.tp02__appcommunication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * Flag authentification
     */
    private boolean authentifie;

    /**
     * Receiver du signal de batterie faible
     */
    private BatteryStatusReceiver batteryStatusReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Authentification
        authentifier_implicite();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Lecture valeur dans les préférences
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        int seuilMini = preferences.getInt("seuil", 0);
        // Abonnement aux intents "ACTION_BATTERY_LOW"
        batteryStatusReceiver = new BatteryStatusReceiver(this, seuilMini);
        registerReceiver(batteryStatusReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Désabonnement aux intents "ACTION_BATTERY_LOW"
        unregisterReceiver(batteryStatusReceiver);
    }

    // Constante utilisées comme RequestCode
    private static final int RESULT_PICK_CONTACT__MAIL = 1;
    private static final int RESULT_PICK_CONTACT__TELEPHONE = 2;
    private static final int RESULT_LOGIN = 3;

    private void authentifier_explicite() {
        authentifie = false;
        Intent startLogin = new Intent() ;
        // Lectures valeurs par défaut dans les préférences
        SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
        String loginPref = sharedPreferences.getString("login", "");
        String passwordPref = sharedPreferences.getString("password", "");
        // Affectation valeurs par défaut à l'intent de login
        startLogin.putExtra("login", loginPref);
        startLogin.putExtra("password", passwordPref);
        startLogin.setClassName("ip.cai.tp02__copposantlogin","ip.cai.tp02__copposantlogin.LoginActivity");
        startActivityForResult(startLogin, RESULT_LOGIN);
    }


    /**
     * Code de la requête de demande de permission
     */
    public static final int REQUEST_LOGIN_PERMISSION = 1;

    private void authentifier_implicite() {
        authentifie = false;

        // Contrôle si permission d'utiliser le login
        int permissionCheck = checkSelfPermission("complogin.permission.LOGIN");
        if(permissionCheck == PackageManager.PERMISSION_DENIED) {
            // Pas de permission : on la demande en interactif à l'utilisateur
            requestPermissions(new String[]{"complogin.permission.LOGIN"}, REQUEST_LOGIN_PERMISSION);
        } else {
            Intent startLogin = new Intent() ;
            // Lectures valeurs par défaut dans les préférences
            SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
            String loginPref = sharedPreferences.getString("login", "");
            String passwordPref = sharedPreferences.getString("password", "");
            // Affectation valeurs par défaut à l'intent de login
            startLogin.setAction("LOGIN_ACTION");
            startLogin.putExtra("login", loginPref);
            startLogin.putExtra("password", passwordPref);

            if(startLogin.resolveActivity(getPackageManager()) == null) {
                Log.i("LOGIN_ACTION","Pas d'activity correspondante pour LOGIN_ACTIONZ");
                finish();
            } else {
                startActivityForResult(startLogin, RESULT_LOGIN);
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Réponse à la demande de permission
        if(requestCode == REQUEST_LOGIN_PERMISSION) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                authentifier_implicite();
            } else {
                // Message pour prévenir l'utilisateur que l'application va s'arrêter
                new AlertDialog.Builder(this).setTitle("Manque autorisation pour utiliser Login")
                        .setMessage("Votre application va être arrêtée.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Arrêt activité
                                finish();
                            }
                        })
                        //.setCancelable(false)
                        .create().show();
            }
        }
    }

    /**
     * Vérifie le login et le password
     * @param login
     * @param password
     */
    private void processLogin(String login, String password) {
        authentifie = ("toto".equals(login) && "secret".equals(password));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Si option config sélectionnée
        if(item.getItemId() == R.id.mnuItemConfig) {
            //.. Démarer l'activité pour saisir le % limite de batterie
            Intent intent = new Intent();
            intent.setClass(this, ConfigActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Bouton demande envoi Mail
    public void onSndMail(View vIew) {
        Intent selectContact = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Email.CONTENT_URI);
        selectContact.setType(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
        startActivityForResult(selectContact, RESULT_PICK_CONTACT__MAIL);
    }

    // Bouton demande envoi SMS
    public void onSndSms(View vIew) {
        Intent selectContact = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        selectContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(selectContact, RESULT_PICK_CONTACT__TELEPHONE);
    }

    // Bouton demande envoi SMS
    public void onQuitter(View vIew) {
        liberationRessources();
        finish();
    }

    @Override
    public void onBackPressed() {
        liberationRessources();
        super.onBackPressed();
    }

    private void liberationRessources() {
        Log.i("LIBERATION","LIBERATION RESSOURCES");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String resultat = "";
        if(resultCode == RESULT_OK && data != null) {

            if(requestCode == RESULT_PICK_CONTACT__MAIL) {
                Cursor cursor = null;
                try {
                    String eMail = null;
                    // Obtention URI
                    Uri uri = data.getData();
                    // Utilisation COntentREsolver pour exploiter URI (requête)
                    cursor = getContentResolver().query(uri, null, null, null, null);
                    cursor.moveToFirst();
                    // Lecture champ Email
                    int emailIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);
                    eMail = cursor.getString(emailIndex);
                    resultat = eMail;
                    composerMail(eMail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(requestCode == RESULT_PICK_CONTACT__TELEPHONE) {
                Cursor cursor = null;
                try {
                    String phone = null;
                    Uri uri = data.getData();
                    cursor = getContentResolver().query(uri, null, null, null, null);
                    cursor.moveToFirst();
                    int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    phone = cursor.getString(numberIndex);
                    resultat = phone;
                    composerSMS(phone);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if(requestCode == RESULT_LOGIN) {
                String login = data.getExtras().getString("login");
                String password = data.getExtras().getString("password");
                // Enregistrement dans les préférences
                SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("login", login);
                editor.putString("password", password);
                editor.commit();
                processLogin(login, password);
            }
            Toast.makeText(getApplicationContext(), "RequestCode=" + requestCode + ", Resultat=" + resultat, Toast.LENGTH_SHORT).show();
        }

        if(!authentifie) {
            Toast.makeText(getApplicationContext(),"Non authentifié", Toast.LENGTH_LONG).show();
            finish();
        }

    }


    private void composerMail(String adresseDestinataire) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        //intent.setData(Uri.parse("mailto:" + adresseDestinataire));
        intent.putExtra(Intent.EXTRA_EMAIL, adresseDestinataire);
        intent.putExtra(Intent.EXTRA_SUBJECT,  "Envoi par Application TP");
        intent.putExtra(Intent.EXTRA_TEXT,  "le contenu du message");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void composerSMS(String numeroDestinataire) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + numeroDestinataire));
        intent.putExtra("sms_body", "le contenu du message Défaut");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
