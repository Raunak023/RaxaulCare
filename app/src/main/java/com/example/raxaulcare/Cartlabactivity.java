package com.example.raxaulcare;

import android.annotation.SuppressLint;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Cartlabactivity extends AppCompatActivity {
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    TextView price;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button buttondate, buttontime, buy;
    private String[][] packages;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cartlabactivity);

        buttondate = findViewById(R.id.buttondate);
        buttontime = findViewById(R.id.buttontime);
        buy = findViewById(R.id.buy);
        price = findViewById(R.id.price);
        lst=findViewById(R.id.labdetails);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        float totalAmount = 0;
        ArrayList<String> dbdata = db.getcartdata(username, "lab");
        Toast.makeText(this, "" + dbdata, Toast.LENGTH_SHORT).show();

        packages = new String[dbdata.size()][5]; // Initialize array with correct size

        for (int i = 0; i < dbdata.size(); i++) {
            String arrData = dbdata.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0]; // Product
            packages[i][4] = "Cost: " + strData[1] + "/-"; // Price
            totalAmount += Float.parseFloat(strData[1]);
        }

        price.setText("Total Cost: " + totalAmount);

        list = new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<>();
            item.put("line1", packages[i][0]); // Changed from packageData to packages
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }


        sa = new SimpleAdapter(
                this,
                list,
                R.layout.multilines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee});
                lst.setAdapter(sa);


           buy.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
              Intent it= new Intent(Cartlabactivity.this,LabtestBookActivity.class);
              it.putExtra("price",price.getText());
              it.putExtra("date",buttondate.getText());
              it.putExtra("time",buttontime.getText());
              startActivity(it);

               }
           });


        // Initialize the date and time pickers
        initDatePicker();
        buttondate.setOnClickListener(v -> datePickerDialog.show());

        initTimePicker();
        buttontime.setOnClickListener(v -> timePickerDialog.show());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1; // Months are 0-indexed
            buttondate.setText(year + ":" + month + ":" + dayOfMonth);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000); // Set minimum date to tomorrow
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hourOfDay, minute) ->
                buttontime.setText(hourOfDay + ":" + minute);

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}
