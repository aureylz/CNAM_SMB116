package edu.cai.demo_binding2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnSimpleBindingClick(View v) {
        this.startActivity(new Intent(this, SimpleBindingActivity.class));
    }

    public void onBtnBidiBinding(View v) {
        this.startActivity(new Intent(this, BidirectionalBindingActivity.class));
    }
}
