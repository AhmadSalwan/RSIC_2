package com.example.jobfit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jobfit.R;
import com.example.jobfit.model.CompanyFacts;

import org.w3c.dom.Text;

public class CompanyFactsActivity extends AppCompatActivity {
    TextView tv_title, tv_description;
    ImageView iv_factsimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_company_facts);

        iv_factsimg = findViewById(R.id.IV_imgFactsDesc);
        tv_title = findViewById(R.id.TV_titleFacstDesc);
        tv_description = findViewById(R.id.TV_DescFacstDesc);

        Intent intent = getIntent();
        CompanyFacts companyFacts = intent.getParcelableExtra("factss");

        iv_factsimg.setImageResource(companyFacts.getFactsImg());
        tv_title.setText(companyFacts.getFactsTitle());
        tv_description.setText(companyFacts.getFactsDesc());

    }
}