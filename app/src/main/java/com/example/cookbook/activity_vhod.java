package com.example.cookbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.cookbook.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class activity_vhod extends AppCompatActivity {

    private Button loginBtn;
    private EditText loginPhoneVhod, passwordPhoneVhod;
    private ProgressDialog loadingBar;

    private String parentDbName ="Users";
    private RadioButton radioButtonRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vhod_v_sistemy);

        loginBtn = (Button) findViewById(R.id.btn_join_avtoriz);
        loginPhoneVhod = (EditText) findViewById(R.id.login_phone_vhod);
        passwordPhoneVhod = (EditText) findViewById(R.id.password_phone_vhod);
        loadingBar = new ProgressDialog(this);
        radioButtonRememberMe = (RadioButton) findViewById(R.id.radioButton);
        Paper.init(this);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String loginPhone = loginPhoneVhod.getText().toString();
        String passwordPhone = passwordPhoneVhod.getText().toString();

        if(TextUtils.isEmpty(loginPhone))
        {
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(passwordPhone))
        {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Вход в приложение");
            loadingBar.setMessage("Пожалуйста, подождите...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateUser(loginPhone,passwordPhone);
        }
    }

    private void ValidateUser(String loginPhone, String passwordPhone) {

        if(radioButtonRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey, loginPhone);
            Paper.book().write(Prevalent.UserPasswordKey, passwordPhone);
        }


        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDbName).child(loginPhone).exists())
                {
                    User userData = dataSnapshot.child(parentDbName).child(loginPhone).getValue(User.class);

                    if(userData.getPhone().equals(loginPhone))
                    {
                        if(userData.getPassword().equals(passwordPhone))
                        {
                            loadingBar.dismiss();
                            Toast.makeText(activity_vhod.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                            Intent homeIntent = new Intent(activity_vhod.this, HomeActivity.class);
                            startActivity(homeIntent);
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(activity_vhod.this, "Неверный пароль!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    loadingBar.dismiss();
                    Toast.makeText(activity_vhod.this, "Аккаунт с номером " + loginPhone + "не существует", Toast.LENGTH_SHORT).show();

                    Intent registerIntent = new Intent(activity_vhod.this, activity_register.class);
                    startActivity(registerIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}