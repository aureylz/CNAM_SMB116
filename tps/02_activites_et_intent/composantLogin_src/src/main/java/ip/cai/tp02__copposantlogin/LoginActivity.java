package ip.cai.tp02__copposantlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            // Initialisation des editText avec les Extra passés dans l'intent
            ((EditText)findViewById(R.id.txtUsername)).setText(intent.getExtras().getString("login", ""));
            ((EditText)findViewById(R.id.txtPassword)).setText(intent.getExtras().getString("password", ""));
        }
    }

    public void onCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
    public void onLogin(View view) {
        Intent intentResult = new Intent();
        intentResult.putExtra("login", ((EditText)findViewById(R.id.txtUsername)).getText().toString());
        intentResult.putExtra("password", ((EditText)findViewById(R.id.txtPassword)).getText().toString());
        setResult(RESULT_OK, intentResult);
        finish();
    }
}
