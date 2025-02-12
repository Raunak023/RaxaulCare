package com.example.raxaulcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class bookAppoinmentActivity extends AppCompatActivity {
   TextView textViewapptitle;
   EditText editTextapp,editTextadd,editTextnum,editTextfee;
   Button btnbook,button2,button3;
   private DatePickerDialog datePickerDialog;
   private TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appoinment);



        textViewapptitle=findViewById(R.id.textViewapptitle);
        editTextapp =findViewById(R.id.editTextapp);
        editTextadd=findViewById(R.id.editTextadd);
        editTextnum=findViewById(R.id.editTextnum);
        editTextfee=findViewById(R.id.editTextfee);
        btnbook=findViewById(R.id.btnbook);

        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        editTextapp.setKeyListener(null);
        editTextadd.setKeyListener(null);
        editTextnum.setKeyListener(null);
        editTextfee.setKeyListener(null);
        btnbook.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");


        textViewapptitle.setText(title);
        editTextapp.setText(fullname);
        editTextadd.setText(address);
        editTextnum.setText(contact);
        editTextfee.setText("Cons: "+ fees+"/-");

        datePickerDialog();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        initTimePeaker();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

            btnbook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            Database db =new Database(getApplicationContext(),"healthcare",null,1);
                    SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username = sharedPreferences.getString("username","").toString();

                    if(db.checkAppointmentExists(username,title+" => "+fullname,address,contact,button2.getText().toString(),button3.getText().toString())==1){
                        Toast.makeText(getApplicationContext(), "Appointment already Book on this day please choose another day.", Toast.LENGTH_SHORT).show();
                    }else{
                        db.addorder(username,title+" => "+fullname,address,contact,0,button2.getText().toString(),button3.getText().toString(),Float.parseFloat(fees),"appointment");
                        Toast.makeText(getApplicationContext(), "Your Appointment is done Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(bookAppoinmentActivity.this, home.class));
                    }
                }
            });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void datePickerDialog(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                i1=i1+1;
                button2.setText(i+":"+i1);

            }
        };

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }
    public  void initTimePeaker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                button3.setText(i+":"+i1);
            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog= new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }

}