package com.example.raxaulcare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Calendar;
import java.util.HashMap;

public class medicinecaart extends AppCompatActivity {

    TextView titlecart, pricee;
    ListView titlelist;

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    private DatePickerDialog datePickerDialog;
    private Button datebm, buyy;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_medicinecaart);

        titlecart = findViewById(R.id.titlecart);
        pricee = findViewById(R.id.pricee);
        titlelist = findViewById(R.id.titlelist);
        datebm = findViewById(R.id.datebm);
        buyy = findViewById(R.id.buyy);


        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);

        float totalAmount = 0;
        ArrayList dbdata = db.getcartdata(username, "Medicine");
        Log.d("CART DATA","Fetched cart data: " + dbdata.toString());

        packages = new String[dbdata.size()][5];

        for (int i = 0; i < packages.length; i++) {
            packages[i] = new String[5];
        }

        for (int i = 0; i < dbdata.size(); i++) {
            String arrData = dbdata.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0]; // Product
            packages[i][4] = "Cost: " + strData[1] + "/-"; // Price
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
        }

        pricee.setText("Total Cost: " + totalAmount);

        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multilines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee});
        titlelist.setAdapter(sa);

        buyy.setOnClickListener(v -> {
//             Uncomment and replace with your desired action
             Intent it = new Intent(medicinecaart.this, BuymedicinebookActivity.class);
            it.putExtra("price", pricee.getText());
           it.putExtra("date", datebm.getText());
           startActivity(it);
        });

        // Initialize the date and time pickers
        initDatePicker();
        datebm.setOnClickListener(v -> datePickerDialog.show());
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            month = month + 1; // Months are 0-indexed
            datebm.setText(year + ":" + month + ":" + dayOfMonth);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000); // Set minimum date to tomorrow

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }



}

