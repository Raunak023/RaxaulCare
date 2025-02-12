package com.example.raxaulcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuymedicinebookActivity extends AppCompatActivity {
EditText namee,address,pinn,conn;
Button btnnn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buymedicinebook);

        namee=findViewById(R.id.namee);
        address=findViewById(R.id.addresss);
        pinn=findViewById(R.id.pinn);
        conn=findViewById(R.id.conn);
        btnnn=findViewById(R.id.btnnn);

        Intent it=getIntent();
        String[]price= it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=it.getStringExtra("date");

        btnnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addorder(username,namee.getText().toString(),address.getText().toString(),conn.getText().toString(),Integer.parseInt(pinn.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removecart(username,"medicine");
                Toast.makeText(getApplicationContext(), "Your Booking Is Done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuymedicinebookActivity.this, home.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}