package com.example.jobfit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobfit.databinding.ActivityRegisterImageBinding;

import java.io.ByteArrayOutputStream;

public class activity_register_image extends AppCompatActivity {

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
            Toast.makeText(activity_register_image.this, "Please Upload Your Photo", Toast.LENGTH_SHORT).show();

        }else{
            // Dapatkan data dari activity sebelumnya
            Intent prevIntent = getIntent();
            String uName = prevIntent.getStringExtra("name");
            String uEmail = prevIntent.getStringExtra("email");
            String uPhoneNumber = prevIntent.getStringExtra("phone");
            String uGender = prevIntent.getStringExtra("gender");
            String skills = prevIntent.getStringExtra("skills");
            String experiences = prevIntent.getStringExtra("experiences");
            String achievements = prevIntent.getStringExtra("achievements");
            String education = prevIntent.getStringExtra("education");
            Toast.makeText(this, "Skills: " + skills + "\nExperiences: " + experiences, Toast.LENGTH_LONG).show();
            // Simpan gambar sebagai byte array
            byte[] uProfilePicture = imageToByte(uri);

            Intent nextintent = new Intent(activity_register_image.this, MainActivity.class);
            saveUserData(uName, uEmail, uPhoneNumber, uGender, skills, experiences, achievements, education, uProfilePicture);
            startActivity(nextintent);
        }
    }

    private byte[] imageToByte(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
                                Toast.makeText(activity_register_image.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(activity_register_image.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

    }
    public void saveUserData(String username, String email, String phoneNumber, String gender, String skills, String experiences, String achievements, String education, byte[] profilePicture) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("phone_number", phoneNumber);
        contentValues.put("gender", gender);
        contentValues.put("skills", skills);
        contentValues.put("experiences", experiences);
        contentValues.put("achievements", achievements);
        contentValues.put("education", education);
        contentValues.put("profile_picture", profilePicture);  // byte array

        long result = db.insert("users", null, contentValues);
        if (result == -1) {
            Toast.makeText(this, "Failed to save user data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User data saved successfully", Toast.LENGTH_SHORT).show();
        }
    }
}