package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.jobfit.R;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


public class activity_register_page extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_page);
        AutoCompleteTextView genderDropdown = findViewById(R.id.genderDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_options, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderDropdown.setAdapter(adapter);

        button=findViewById(R.id.continue_regist);
        button.setOnClickListener( v ->{
            Intent intent = new Intent(activity_register_page.this, activity_register_image.class);
            startActivity(intent);
        });
    }
}