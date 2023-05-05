package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cookbook.R;

import io.paperdb.Paper;

public class btnSetting_Activity extends AppCompatActivity {
    private Button logoutBtn;
    private ImageView backSettingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_setting);

        logoutBtn =(Button)findViewById(R.id.btn_logout);
        backSettingImage = (ImageView)findViewById(R.id.image_back_setting);

        backSettingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backSettingIntent = new Intent(btnSetting_Activity.this, HomeActivity.class);
                startActivity(backSettingIntent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Paper.book().destroy();

                Intent logoutIntent = new Intent(btnSetting_Activity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });
    }
}