package com.example.cookbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cookbook.Adapter.ShagiAdapter;
import com.example.cookbook.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class AddCook_Activity extends AppCompatActivity {

    //String []data = {"" , "Hi", "Welcome"};
    //int counter = 0;

    //private static Context This;
    private ImageView BackAddCook, PhotoAdd, CameraAdd, GalleryAdd;

    private AppCompatButton bytb;
    private Bitmap bitmap;
    private String checker = "";
    private StorageReference StorageCookPictureRef;
    private StorageTask uploadTask;
    private static final int GalleryPick = 1;

    private String currentUserId;
    Uri ImageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cook);

        bytb = findViewById(R.id.btn_save_cook);

        PhotoAdd = (ImageView) findViewById(R.id.photo_add);
        BackAddCook = (ImageView) findViewById(R.id.btn_back_addcook);
        CameraAdd = (ImageView) findViewById(R.id.image_camera_icon);
        GalleryAdd = (ImageView) findViewById(R.id.image_gallery_icon);

//        List<String> items = new LinkedList<>();
//        items.add("Code It");
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_add);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ShagiAdapter adapter = new ShagiAdapter(items);
//        recyclerView.setAdapter(adapter);
//
//        findViewById(R.id.btn_save_cook).setOnClickListener(view -> {
//            items.add(data[counter%3]);
//            counter++;
//            adapter.notifyItemRemoved(items.size()-1);
//        });
//        getId = user.get(SessionManager.ID);

        GalleryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checker = "clicked";

                OpenGallery();
            }
        });
        BackAddCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BackAddCook = new Intent(AddCook_Activity.this, HomeActivity.class);
                startActivity(BackAddCook);
            }
        });

        CameraAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraAdd = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraAdd, 0);
            }
        });


    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        CameraAdd.setImageBitmap(bitmap);
    }

    private void OpenGallery() {
        Intent intent_galleryAdd = new Intent();
        intent_galleryAdd.setType("image/*");
        intent_galleryAdd.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent_galleryAdd, GalleryPick);
        // startActivityForResult(Intent.createChooser(intent_galleryAdd, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                PhotoAdd.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void userInfoAndImageSaved()
    {
        if (checker.equals("clicked"))
        {
            uploadImage();
        }

    }

    private void uploadImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Обновление...");
        progressDialog.setMessage("Пожалуйста, подождите");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if (ImageUri != null) {
            final StorageReference fileRef = StorageCookPictureRef.child(currentUserId + ".WebP");

            uploadTask = fileRef.putFile(ImageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful())
                    {
                        Uri downloadUri = task.getResult();
                        String myUrl = downloadUri.toString();

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");

                        HashMap<String,Object> userMap = new HashMap<>();
                        userMap.put("image", myUrl);
                        ref.child(Prevalent.currentOnlineUser.updateChildren(userMap));

                                progressDialog.dismiss();
                        Toast.makeText(AddCook_Activity.this, "Данные успешно изменены", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

            });
                    
        }
    }
}
