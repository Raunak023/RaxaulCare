package com.example.raxaulcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

public class HealthArticle extends AppCompatActivity {
    Button btn11;
    private String[][] health_details = {
            {"Walk Daily", "", "", "", "Click More Details"},
            {"Eat Balanced Diet", "", "", "", "Click More Details"},
            {"Drink Plenty of Water", "", "", "", "Click More Details"},
            {"Get Enough Sleep", "", "", "", "Click More Details"},
            {"Exercise Regularly", "", "", "", "Click More Details"}
    };

    private int[] images = {
            R.drawable.guidee,
            R.drawable.stop,
            R.drawable.guid,
            R.drawable.fruits,
            R.drawable.fru
    };

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list = new ArrayList<>();
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_article);

        btn11 = findViewById(R.id.btn11);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticle.this, home.class));
            }
        });

        for (int i = 0; i < health_details.length; i++) {
            item = new HashMap<>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);
            item.put("image", String.valueOf(images[i]));  // Add image resource as a string to HashMap
            list.add(item); // Add the HashMap to the list
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multilines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee});

        ListView lst = findViewById(R.id.list1);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(HealthArticle.this, HealthArticleDetail.class);
                it.putExtra("text1", health_details[i][0]);
                it.putExtra("text2", images[i]);
                startActivity(it);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
