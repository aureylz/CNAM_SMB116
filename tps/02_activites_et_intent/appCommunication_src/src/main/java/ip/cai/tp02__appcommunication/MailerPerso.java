package ip.cai.tp02__appcommunication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MailerPerso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailer_perso);

        String adresse = (String)(getIntent().getExtras().get(Intent.EXTRA_EMAIL));
        ((TextView)findViewById(R.id.txtMail)).setText(adresse);
    }
}
