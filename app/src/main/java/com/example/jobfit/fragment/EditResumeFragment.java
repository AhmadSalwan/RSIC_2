package com.example.jobfit.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.canhub.cropper.CropImageContract;
import com.canhub.cropper.CropImageContractOptions;

import com.canhub.cropper.CropImageView;
import com.example.jobfit.R;
import com.example.jobfit.activity.MainActivity;
import com.example.jobfit.db.DBHelper;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import java.io.File;
import java.io.IOException;

public class EditResumeFragment extends Fragment {
    private static final int GalleryPick = 1;
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    private static final int IMAGEPICK_GALLERY_REQUEST = 300;
    private static final int IMAGE_PICKCAMERA_REQUEST = 400;
    private  Uri imageUri;
    private EditText editTextSkills, editTextExperiences, editTextAchievements, editTextEducation;
    private DBHelper dbHelper;
    String email;
    ImageButton addSkillButton, addExperienceButton, addAchievementButton, addEducationButton;
    ImageView selectedImageView;
    private ActivityResultLauncher<Intent> pickImageLauncher;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<String> cameraPermissionLauncher;
    private ActivityResultLauncher<String> storagePermissionLauncher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_resume, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHelper = new DBHelper(getContext());
        editTextSkills = view.findViewById(R.id.skillEditText);
        editTextExperiences = view.findViewById(R.id.experienceEditText);
        editTextAchievements = view.findViewById(R.id.achievementEditText);
        editTextEducation = view.findViewById(R.id.educationEditText);
        addSkillButton = view.findViewById(R.id.addSkillButton);
        addExperienceButton = view.findViewById(R.id.addExperienceButton);
        addAchievementButton = view.findViewById(R.id.addAchievementButton);
        addEducationButton = view.findViewById(R.id.addEducationButton);
        selectedImageView=view.findViewById(R.id.expimageview);

        addSkillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImagePicDialog();
            }
        });

        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK ) {
                        selectedImageView.setImageURI(imageUri);
                        performOCR(imageUri);
                    } else {
                        Toast.makeText(getContext(), "Gagal"+imageUri , Toast.LENGTH_SHORT).show();
                    }
                }
        );
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        selectedImageView.setImageURI(imageUri);
                    } else {
                        Toast.makeText(getContext(), "Failed to pick image", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        storagePermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        pickFromGallery();
                    } else {
                        if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)){
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Storage Permission Required")
                                    .setMessage("Storage permission is needed to access images. Please allow this permission in settings.")
                                    .setPositiveButton("OK", (dialog, which) -> {
                                    })
                                    .setNegativeButton("Cancel", (dialog, which) -> {
                                        // User chose not to provide permission
                                        Toast.makeText(getContext(), "Storage permission is still required", Toast.LENGTH_LONG).show();
                                    })
                                    .create()
                                    .show();
                        } else {
                            // Pengguna memilih "Jangan tanyakan lagi", arahkan ke pengaturan
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Permission Denied")
                                    .setMessage("You have denied storage permission. Please enable it in the app settings.")
                                    .setPositiveButton("Go to Settings", (dialog, which) -> {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    })
                                    .setNegativeButton("Cancel", (dialog, which) -> {
                                        Toast.makeText(getContext(), "Storage permission is still required", Toast.LENGTH_LONG).show();
                                    })
                                    .create()
                                    .show();
                        }

                    }
                }
        );
        cameraPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        // Jika izin diberikan, buka kamera
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, IMAGE_PICKCAMERA_REQUEST);
                    } else {
                        // Cek apakah perlu memberikan penjelasan
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                            // Tampilkan dialog penjelasan
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Camera Permission Required")
                                    .setMessage("Camera permission is needed to take pictures. Please allow this permission.")
                                    .setPositiveButton("OK", (dialog, which) -> {
                                        // Minta izin lagi
                                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA);
                                    })
                                    .setNegativeButton("Cancel", (dialog, which) -> {
                                        Toast.makeText(getContext(), "Camera permission is still required", Toast.LENGTH_LONG).show();
                                    })
                                    .create()
                                    .show();
                        } else {
                            // Pengguna memilih "Jangan tanyakan lagi", arahkan ke pengaturan
                            new AlertDialog.Builder(getContext())
                                    .setTitle("Permission Denied")
                                    .setMessage("You have denied camera permission. Please enable it in the app settings.")
                                    .setPositiveButton("Go to Settings", (dialog, which) -> {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    })
                                    .setNegativeButton("Cancel", (dialog, which) -> {
                                        Toast.makeText(getContext(), "Camera permission is still required", Toast.LENGTH_LONG).show();
                                    })
                                    .create()
                                    .show();
                        }
                    }
                }
        );


        Button saveButton = view.findViewById(R.id.save_resume);
        email = getActivity().getSharedPreferences("UserPrefs", getContext().MODE_PRIVATE).getString("email", null);

        saveButton.setOnClickListener(v -> {
            String skills = editTextSkills.getText().toString();
            String experiences = editTextExperiences.getText().toString();
            String achievements = editTextAchievements.getText().toString();
            String education = editTextEducation.getText().toString();

            boolean updated = dbHelper.updateUserData(email, skills, experiences, achievements, education);

            if (updated) {
                Toast.makeText(getContext(), "Skills: " + skills + "\nExperience: " + experiences, Toast.LENGTH_LONG).show();
                ProfileFragment profileFragment = new ProfileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main, profileFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else {
                Toast.makeText(getContext(), "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    give onclick for each imageview
    private void showImagePicDialog() {
        String options[] = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Pick Image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    } else {
                        pickfromCamera();
                    }
                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromGallery();
                    }
                }
            }
        });
        builder.create().show();
    }


    private Boolean checkStoragePermission() {
        return ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }
    private Boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            storagePermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            storagePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            pickFromGallery();
        }
    }
    private void requestCameraPermission() {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA);
    }

    private void pickFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(galleryIntent);
    }

    private void pickfromCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageUri = createImageFileUri(); // Dapatkan URI file gambar sementara

        if (imageUri != null) {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            cameraLauncher.launch(cameraIntent);
        } else {
            Toast.makeText(getContext(), "Gagal membuat file untuk gambar", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CAMERA_REQUEST:{
                Uri imageUri = data.getData(); // Sesuaikan dengan cara menangani gambar yang diambil
                selectedImageView.setImageURI(imageUri); // Tampilkan gambar yang diambil
            }
            case STORAGE_REQUEST:{

            }
        }
    }
    private Uri createImageFileUri() {
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        try {
            File imageFile = File.createTempFile(
                    "JPEG_" + System.currentTimeMillis() + "_",
                    ".jpg",
                    storageDir
            );
            return FileProvider.getUriForFile(getContext(), "com.example.jobfit.fileprovider", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void performOCR(Uri imageUri) {
        try {
            // Membuat objek InputImage dari URI
            InputImage image = InputImage.fromFilePath(getContext(), imageUri);

            // Inisialisasi Text Recognizer dari ML Kit
            TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

            // Memulai proses OCR
            recognizer.process(image)
                    .addOnSuccessListener(visionText -> {
                        // Mendapatkan hasil teks
                        String detectedText = visionText.getText();
                        // Tampilkan hasil di UI atau simpan ke database
                        Toast.makeText(getContext(), "Teks terdeteksi: " + detectedText, Toast.LENGTH_LONG).show();
                    })
                    .addOnFailureListener(e -> {
                        // Penanganan jika OCR gagal
                        Toast.makeText(getContext(), "Gagal mendeteksi teks", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    });

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
        }
    }
}
