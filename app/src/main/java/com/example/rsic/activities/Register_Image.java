package com.example.rsic.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rsic.R;
import com.google.android.material.imageview.ShapeableImageView;

public class Register_Image extends AppCompatActivity {
    Button continue_regist;
    ShapeableImageView main_image;
    ActivityResultLauncher<Intent> resultLauncher;
    Uri uri;
    boolean cekGambar=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_image);

        main_image=findViewById(R.id.register_image);
        main_image.setOnClickListener(v -> pickImage());
        continue_regist=findViewById(R.id.continue_regist);
        continue_regist.setOnClickListener(v -> next_page());
        registerResult();
    }

    private void next_page() {
            if (cekGambar!=true){
                Toast.makeText(Register_Image.this, "Please Upload Your Photo", Toast.LENGTH_SHORT).show();

            }else{
                Intent intent=new Intent(Register_Image.this,Register_Page.class);
                startActivity(intent);
            }
    }


    private void pickImage(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        resultLauncher.launch(intent);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&data!=null){
            uri=data.getData();
            main_image.setImageURI(uri);
        }
    }
    private void registerResult(){
        resultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try{
                            Intent data = result.getData();
                            if (data != null) {
                                Uri imageUri = data.getData();
                                main_image.setImageURI(imageUri);
                                cekGambar=true;
                            } else {
                                cekGambar=false;
                                Toast.makeText(Register_Image.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(Register_Image.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

    }
}