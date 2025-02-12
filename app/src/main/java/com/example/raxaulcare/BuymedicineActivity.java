package com.example.raxaulcare;

import static androidx.core.content.ContextCompat.startActivity;

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

public class BuymedicineActivity extends AppCompatActivity {


    private String[][] packages = {
            {"Paracetamol 500mg Tablet", "", "", "", "150"},
            {"Ciprofloxacin 250mg Tablet", "", "", "", "200"},
            {"Amoxicillin 500mg Capsule", "", "", "", "300"},
            {"Cetirizine 10mg Tablet", "", "", "", "120"},
            {"Ibuprofen 400mg Tablet", "", "", "", "180"},
            {"Pantoprazole 40mg Tablet", "", "", "", "220"},
            {"Metformin 500mg Tablet", "", "", "", "250"},
            {"Losartan 50mg Tablet", "", "", "", "180"},
            {"Atorvastatin 10mg Tablet", "", "", "", "200"},
            {"Omeprazole 20mg Capsule", "", "", "", "150"},
    };
    private String[] package_details = {
            "Paracetamol 500mg Tablet:\n" +
                    "Relieves pain and reduces fever\n" +
                    "Commonly used for headaches, muscle pain, and colds",
            "Ciprofloxacin 250mg Tablet:\n" +
                    "Treats bacterial infections\n" +
                    "Effective for urinary tract infections, respiratory infections, and more",
            "Amoxicillin 500mg Capsule:\n" +
                    "Treats bacterial infections\n" +
                    "Used for respiratory tract infections, ear infections, and more",
            "Cetirizine 10mg Tablet:\n" +
                    "Relieves allergies\n" +
                    "Reduces symptoms like sneezing, runny nose, and itchy eyes",
            "Ibuprofen 400mg Tablet:\n" +
                    "Reduces pain and inflammation\n" +
                    "Used for conditions like arthritis, muscle pain, and fever",
            "Pantoprazole 40mg Tablet:\n" +
                    "Reduces stomach acid\n" +
                    "Treats acid reflux, heartburn, and peptic ulcers",
            "Metformin 500mg Tablet:\n" +
                    "Controls blood sugar levels\n" +
                    "Used for managing Type 2 diabetes",
            "Losartan 50mg Tablet:\n" +
                    "Lowers high blood pressure\n" +
                    "Reduces the risk of heart attack and stroke",
            "Atorvastatin 10mg Tablet:\n" +
                    "Lowers cholesterol levels\n" +
                    "Prevents cardiovascular diseases",
            "Omeprazole 20mg Capsule:\n" +
                    "Reduces stomach acid\n" +
                    "Treats conditions like acid reflux and gastric ulcers"
    };

    ListView mdlist;
    Button mdcart;
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buymedicine);

        mdlist = findViewById(R.id.mdlist);
        mdcart=findViewById(R.id.mdcart);

        mdcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuymedicineActivity.this, medicinecaart.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[] {"line1", "line2","line3","line4","line5"},
                new int[] {R.id.linea,R.id.lineb,R.id.linec,R.id.lined,R.id.linee});
                mdlist.setAdapter(sa);

             mdlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int i, long l) {                        Intent it=new Intent(BuymedicineActivity.this,MedicineDetail.class);
                     Intent io=new Intent(BuymedicineActivity.this,MedicineDetail.class);
                     io.putExtra("text1",packages[i][0]);
                     io.putExtra("text2",package_details[i]);
                     io.putExtra("text3",packages[i][4]);
                     startActivity(io);
                 }
             });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}