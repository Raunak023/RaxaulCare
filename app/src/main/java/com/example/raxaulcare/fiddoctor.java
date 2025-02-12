package com.example.raxaulcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class fiddoctor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fiddoctor);

        // Corrected ID reference
        CardView exit1 = findViewById(R.id.cardEXIT);
        exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fiddoctor.this, home.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CardView familyphysician = findViewById(R.id.cardfamily);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(fiddoctor.this, DoctordetailActivity.class);
                it.putExtra("title", "Family Physician");
                startActivity(it);
            }
        });

        CardView cardfdDit = findViewById(R.id.cardfdDIE);
        cardfdDit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(fiddoctor.this, DoctordetailActivity.class);
                it.putExtra("title", "DIETICIAN");
                startActivity(it);
            }
        });

        CardView cardteeth = findViewById(R.id.CARDFDDENTIST);
        cardteeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(fiddoctor.this, DoctordetailActivity.class);
                it.putExtra("title", "DENTIST");
                startActivity(it);
            }
        });

        CardView sear = findViewById(R.id.CARDFDSEARGEON);
        sear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(fiddoctor.this, DoctordetailActivity.class);
                it.putExtra("title", "SURGEON");
                startActivity(it);
            }
        });

        CardView car = findViewById(R.id.cardfdcan);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(fiddoctor.this, DoctordetailActivity.class);
                it.putExtra("title", "CARDIOLOGIST");
                startActivity(it);
            }
        });
    }
}
