package com.example.raxaulcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LabtestdetalActivity extends AppCompatActivity {


    TextView textp, totalcost;
    EditText listmulti;
    Button addcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_labtestdetal);

        textp = findViewById(R.id.textp);
        totalcost = findViewById(R.id.totalcost);
        listmulti = findViewById(R.id.listmulti);
        addcart = findViewById(R.id.addcart);

        // Adjust window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listmulti.setKeyListener(null); // Make EditText non-editable

        // Receive intent data
        Intent i = getIntent();
        textp.setText(i.getStringExtra("text1")); // Package name
        listmulti.setText(i.getStringExtra("text2")); // Package details
        totalcost.setText("Total Cost: " + i.getStringExtra("text3") + "/-");

        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username", "").toString();
                String product=textp.getText().toString();
                float price=Float.parseFloat(i.getStringExtra("text3").toString());
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(db.checkcard(username,product)==1){
                    Toast.makeText(LabtestdetalActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(LabtestdetalActivity.this, "Added to the Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabtestdetalActivity.this,LabtestActivity.class));
                }
            }
        });
    }
}