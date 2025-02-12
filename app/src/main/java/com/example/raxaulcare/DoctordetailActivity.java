package com.example.raxaulcare;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctordetailActivity extends AppCompatActivity {
    private String[][] doctor_detail1=
    {
            {"Doctor Name : DR Prabhu", "Hospital Address : Duncan Raxaul", "Exp : 12 years", "Mobile Number: 9898989898","150"},
        {"Doctor Name : DR RajKumar", "Hospital Address : Kaurihar Raxaul", "Exp : 5 years", "Mobile Number: 9060292050","500"},
        {"Doctor Name : DR N Akhatar", "Hospital Address : Shyampur Rd near old Nagarpalika office Raxaul", "Exp : 10 years", "Mobile Number: 9060292050","500"},
        {"Doctor Name : DR R P singh", "Hospital Address : Arya Samaj Rd", "Exp : 10 years", "Mobile Number: 080833 9677","500"},
        {"Doctor Name : DR Ajay Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
            {"Doctor Name : DR sanjay ", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
            {"Doctor Name : DR Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
            {"Doctor Name : DR nila Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
            {"Doctor Name : DR  sanjay ray Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},



    };
    private String[][] doctor_detail2=
            {
                    {"Doctor Name : DR Srivali", "Hospital Address : Duncan Raxaul", "Exp : 12 years", "Mobile Number: 9898989898","150"},
                    {"Doctor Name : DR RajKumar", "Hospital Address : Kaurihar Raxaul", "Exp : 5 years", "Mobile Number: 9060292050","500"},
                    {"Doctor Name : DR N Akhatar", "Hospital Address : Shyampur Rd near old Nagarpalika office Raxaul", "Exp : 10 years", "Mobile Number: 9060292050","500"},
                    {"Doctor Name : DR R P singh", "Hospital Address : Arya Samaj Rd", "Exp : 10 years", "Mobile Number: 080833 9677","500"},
                    {"Doctor Name : DR Ajay Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},


            };

    private String[][] doctor_detail3=
            {
                    {"Doctor Name : DR Prabhu", "Hospital Address : Duncan Raxaul", "Exp : 12 years", "Mobile Number: 9898989898","150"},
                    {"Doctor Name : DR RajKumar", "Hospital Address : Kaurihar Raxaul", "Exp : 5 years", "Mobile Number: 9060292050","500"},
                    {"Doctor Name : DR N Akhatar", "Hospital Address : Shyampur Rd near old Nagarpalika office Raxaul", "Exp : 10 years", "Mobile Number: 9060292050","500"},
                    {"Doctor Name : DR R P singh", "Hospital Address : Arya Samaj Rd", "Exp : 10 years", "Mobile Number: 080833 9677","500"},
                    {"Doctor Name : DR Ajay Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR sanjay ", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR nila Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR  sanjay ray Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},


            };
    private String[][] doctor_detail4=
            {
                    {"Doctor Name : DR Prabhu", "Hospital Address : Duncan Raxaul", "Exp : 12 years", "Mobile Number: 9898989898","150"},
                    {"Doctor Name : DR RajKumar", "Hospital Address : Kaurihar Raxaul", "Exp : 5 years", "Mobile Number: 9060292050","500"},
                    {"Doctor Name : DR N Akhatar", "Hospital Address : Shyampur Rd near old Nagarpalika office Raxaul", "Exp : 10 years", "Mobile Number: 9060292050","500"},
                    {"Doctor Name : DR R P singh", "Hospital Address : Arya Samaj Rd", "Exp : 10 years", "Mobile Number: 080833 9677","500"},
                    {"Doctor Name : DR Ajay Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR sanjay ", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR nila Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR  sanjay ray Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},


            };
    private String[][] doctor_detail5=
            {
                    {"Doctor Name : DR Prabhu", "Hospital Address : Duncan Raxaul", "Exp : 12 years", "Mobile Number: 9898989898","150"},
                    {"Doctor Name : DR RajKumar", "Hospital Address : Kaurihar Raxaul", "Exp : 5 years", "Mobile Number: 9060292050","500"},
                    {"Doctor Name : DR N Akhatar", "Hospital Address : Shyampur Rd near old Nagarpalika office Raxaul", "Exp : 10 years", "Mobile Number: 9060292050","500"},
                    {"Doctor Name : DR R P singh", "Hospital Address : Arya Samaj Rd", "Exp : 10 years", "Mobile Number: 080833 9677","500"},
                    {"Doctor Name : DR Ajay Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR sanjay ", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR nila Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},
                    {"Doctor Name : DR  sanjay ray Varma", "Hospital Address : Raja Bazar Motihari", "Exp : 25 years", "Mobile Number: 9835450998","500"},


            };
TextView tv;
String[][]Doctor_detail={};
HashMap<String,String>item;
ArrayList list;
SimpleAdapter sa;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctordetail);

        tv=findViewById(R.id.textviewtit);
        btn=findViewById(R.id.button);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0){
            Doctor_detail=doctor_detail1;
        }
        else if(title.compareTo("DIETICIAN")==0){

            Doctor_detail=doctor_detail2;
        }
        else if(title.compareTo("DENTIEST")==0){

            Doctor_detail=doctor_detail3;
        }
        else if(title.compareTo("SURGEON")==0){

            Doctor_detail=doctor_detail4;
        }
        else {

            Doctor_detail = doctor_detail5;

        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctordetailActivity.this,fiddoctor.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        list = new ArrayList();
        for (int i = 0; i < Doctor_detail.length; i++) {
            item = new HashMap<>();
            item.put("line1", Doctor_detail[i][0]); // Use Doctor_detail instead of doctor_detail1
            item.put("line2", Doctor_detail[i][1]);
            item.put("line3", Doctor_detail[i][2]);
            item.put("line4", Doctor_detail[i][3]);
            item.put("line5", "Cons Fee: " + Doctor_detail[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multilines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee});
        ListView lst = findViewById(R.id.listdb);
        lst.setAdapter(sa);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctordetailActivity.this, bookAppoinmentActivity.class);

                it.putExtra("text1", title);
                it.putExtra("text2", Doctor_detail[i][0]); // Doctor's name
                it.putExtra("text3", Doctor_detail[i][1]); // Hospital address
                it.putExtra("text4", Doctor_detail[i][3]); // Mobile number
                it.putExtra("text5", Doctor_detail[i][4]); // Cons fee

                startActivity(it);
            }
        });

    }

}