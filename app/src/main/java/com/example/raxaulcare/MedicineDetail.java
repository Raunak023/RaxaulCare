package com.example.raxaulcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MedicineDetail extends AppCompatActivity {
EditText listvvv;
TextView cost, textp;
Button btngoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicine_detail);

        listvvv=findViewById(R.id.listvvv);
        cost=findViewById(R.id.cost);
        btngoto=findViewById(R.id.btngoto);
        textp=findViewById(R.id.textp);

        Intent intent=getIntent();
        textp.setText(intent.getStringExtra("text1"));
        listvvv.setText(intent.getStringExtra("text2"));
        cost.setText("Total Cost;-" + intent.getStringExtra("text3") +"/-");


        btngoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product= textp.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkcard(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addCart(username,product,price,"Medicine");
                    Toast.makeText(getApplicationContext(), "Added to the Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MedicineDetail.this,BuymedicineActivity.class));
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}