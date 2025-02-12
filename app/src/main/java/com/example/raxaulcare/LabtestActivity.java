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

public class LabtestActivity extends AppCompatActivity {

    private String  [][] packages= {
            {"Package 1 : Full Body Checkup", "", "", "", "999"},
            {"Package 2 : Blood Glucose fasting", "", "", "", "299"},
            {"Package 3 : COVID-19 Antibody", "", "", "", "899"},
            {"Package 4 : Thyoid Check", "", "", "", "499"},
            {"Package 5 : Immunity Check", "", "", "", "599"},
//            {"Package 6 : X-Ray", "", "", "", "99"},
//            {"Package 7 : CITI Scan", "", "", "", "9999"},

    };

    private String[][] package_detail = {
            {
                    "Blood Glucose Fasting\n" +
                            "Complete Hamegram\n" +
                            "Full Body Checkup\n" +
                            "Complete Blood Count (CBC)\n" +
                            "Liver Function Test\n" +
                            "Kidney Function Test\n" +
                            "Urine Routine and Microscopy\n",
                    "Thyroid Profile",
                    "ECG",
                    "Chest X-Ray",
                    "HIV Test" +
                            "Cholesterol Test (Lipid Profile)\n" +
                            "Electrocardiogram (ECG)\n" +
                            "Blood Urea Nitrogen (BUN)\n" +
                            "Creatinine Test\n" +
                            "Ferritin Test\n" +
                            "Thyroid Ultrasound\n" +
                            "Hematocrit Test"

            }
            };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button buttoncart;
    ListView list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_labtest);

        buttoncart = findViewById(R.id.addcart);
        list1 = findViewById(R.id.list1);

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("Line1", packages[i][0]);
            item.put("Line2", packages[i][1]);
            item.put("Line3", packages[i][2]);
            item.put("Line4", packages[i][3]);
            item.put("Line5", "Total Cost " + packages[i][4] + "/-");
            list.add(item);

            sa = new SimpleAdapter(this, list,
                    R.layout.multi2,
                    new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                    new int[]{R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee});
            list1.setAdapter(sa);
//    }
            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    Intent it = new Intent(LabtestActivity.this, LabtestdetalActivity.class);
                    it.putExtra("text1", packages[i][0]); // Package name
                    it.putExtra("text2", package_detail[0][0]); // Details (adjust as needed)
                    it.putExtra("text3", packages[i][4]); // Cost
                    startActivity(it);
                }
            });

            buttoncart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LabtestActivity.this,Cartlabactivity.class));
                }
            });


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}