package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAuth, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glavnoe_okno_avtoriz);

        btnAuth = (Button) findViewById(R.id.btn_register_goa);
        btnLogin = (Button) findViewById(R.id.btn_vhod_goa);

        btnAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AuthIntent = new Intent(MainActivity.this, activity_register.class);
                startActivity(AuthIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(MainActivity.this , activity_vhod.class);
                startActivity(LoginIntent);
            }
        });
    }

}