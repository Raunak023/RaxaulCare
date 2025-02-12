package com.example.raxaulcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HealthArticleDetail extends AppCompatActivity {
    TextView dddd;
    ImageView iiii;
    Button bbbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_article_detail);

        // Initialize the views
        dddd = findViewById(R.id.dddd);
        iiii = findViewById(R.id.iiii);
        bbbb = findViewById(R.id.bbbb);

        // Get the data passed via intent
        Intent intent = getIntent();
        dddd.setText(intent.getStringExtra("text1"));

        // Get the image resource passed through the intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int restid = bundle.getInt("text2");
            iiii.setImageResource(restid);  // Set the image resource
        }

        // Set up the back button click listener
        bbbb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticleDetail.this, HealthArticle.class));
            }
        });

        // Apply window insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
