package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {
    private ImageView addCook,settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        addCook = (ImageView)findViewById(R.id.addcook_plusik);
        settingBtn = (ImageView)findViewById(R.id.btn_setting);

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingBtnIntent = new Intent(HomeActivity.this, btnSetting_Activity.class);
                startActivity(settingBtnIntent);
            }
        });


        addCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addcook_plusikIntent = new Intent(HomeActivity.this, AddCook_Activity.class);
                startActivity(addcook_plusikIntent);
            }
        });

    }
}