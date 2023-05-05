package com.example.cookbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class AddCook_Activity extends AppCompatActivity {

    private ImageView PhotoAdd;
    private String checker = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cook);

        PhotoAdd = (ImageView)findViewById(R.id.photo_add);

        PhotoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker = "clicked";
            }
        });

    }
}