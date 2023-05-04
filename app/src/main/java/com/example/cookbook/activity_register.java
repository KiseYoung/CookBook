package com.example.cookbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class activity_register extends AppCompatActivity {

    private Button registerBtn;
    private EditText namePhoneInput, loginPhoneInput, passwordPhoneInput, password2PhoneInput;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        registerBtn = (Button) findViewById(R.id.btn_reg_register);
        namePhoneInput = (EditText) findViewById(R.id.name_phone_input);
        loginPhoneInput = (EditText) findViewById(R.id.login_phone_input);
        passwordPhoneInput = (EditText) findViewById(R.id.password_phone_input);
        password2PhoneInput = (EditText) findViewById(R.id.password2_phone_input);
        loadingBar = new ProgressDialog(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String namePhone = namePhoneInput.getText().toString();
        String loginPhone = loginPhoneInput.getText().toString();
        String passwordPhone = passwordPhoneInput.getText().toString();
        String password2Phone = password2PhoneInput.getText().toString();


        if(TextUtils.isEmpty(namePhone))
        {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(loginPhone))
        {
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(passwordPhone))
        {
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password2Phone))
        {
            Toast.makeText(this, "Подтвердите пароль", Toast.LENGTH_SHORT).show();
        }
        else if(!(passwordPhone.equals(password2Phone)))
        {
            Toast.makeText(this, "Пароль подтвержден неверно\nПовторите попытку!", Toast.LENGTH_SHORT).show();
            password2PhoneInput.setText("");
        }
        else
        {
            loadingBar.setTitle("Создание аккаунта");
            loadingBar.setMessage("Пожалуйста, подождите...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhone(namePhone,loginPhone,passwordPhone);
        }
    }

    private void ValidatePhone(String namePhone, String loginPhone, String passwordPhone) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if  (!(dataSnapshot.child("Users").child(loginPhone).exists()))
                {

                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("phone", loginPhone);
                    userDataMap.put("name", namePhone);
                    userDataMap.put("password", passwordPhone);

                    RootRef.child("Users").child(loginPhone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(activity_register.this, "Регистрация прошла успешно.", Toast.LENGTH_SHORT).show();

                                        Intent LoginIntent = new Intent(activity_register.this, activity_vhod.class);
                                        startActivity(LoginIntent);
                                    }
                                    else {
                                        loadingBar.dismiss();
                                        Toast.makeText(activity_register.this, "Ошибка.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else {
                    loadingBar.dismiss();
                    Toast.makeText(activity_register.this, "Номер" + loginPhone + "уже зарегистрирован", Toast.LENGTH_SHORT).show();
                    Intent LoginIntent = new Intent(activity_register.this , activity_vhod.class);
                    startActivity(LoginIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}