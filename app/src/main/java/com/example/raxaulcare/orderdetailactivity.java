package com.example.raxaulcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class orderdetailactivity extends AppCompatActivity {
    private String[][] order_details = {};

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    ListView names;
    Button bok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orderdetailactivity);

        names = findViewById(R.id.names);
        bok = findViewById(R.id.bok);

        bok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(orderdetailactivity.this, home.class));
            }
        });

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        ArrayList<String> dbdata = db.getorderdata(username);

        order_details = new String[dbdata.size()][];
        for (int i = 0; i < order_details.length; i++) {
            order_details[i] = new String[5];
            String arrdata = dbdata.get(i);
            String[] strdata = arrdata.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strdata[0];  // Order ID
            order_details[i][1] = strdata[1];  // Product name or other details
            if (strdata[7].compareTo("medicine") == 0) { // Handle if it's a medicine
                order_details[i][3] = "Del: " + strdata[4];
            } else {
                order_details[i][3] = "del: " + strdata[4] + " " + strdata[5];
            }
            order_details[i][2] = "Rs." + strdata[6];  // Price
            order_details[i][4] = strdata[7];  // Type (e.g., medicine, etc.)
        }

        list = new ArrayList<>();
        for (int i = 0; i < order_details.length; i++) {
            item = new HashMap<>();
            item.put("line1", order_details[i][0]);
            item.put("line2", order_details[i][1]);
            item.put("line3", order_details[i][2]);
            item.put("line4", order_details[i][3]);
            item.put("line5", order_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(
                this,
                list,
                R.layout.multilines,  // XML layout file for each list item
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee});
        names.setAdapter(sa);

        // Fix for ViewCompat to apply window insets (for devices with system bars)
        View rootView = findViewById(android.R.id.content); // Use the root view of the activity
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }
}
