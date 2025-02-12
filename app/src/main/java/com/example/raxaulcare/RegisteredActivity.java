package com.example.raxaulcare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisteredActivity extends AppCompatActivity {

    EditText uname, pass, pass2, emaill;
    Button login;
    TextView haveac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registered);

        // Initialize Views
        uname = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);
        pass2 = findViewById(R.id.pass2);
        emaill = findViewById(R.id.emaill);
        login = findViewById(R.id.login);
        haveac = findViewById(R.id.haveac);

        // Navigate to Login Activity
        haveac.setOnClickListener(v -> startActivity(new Intent(RegisteredActivity.this, loginActivity.class)));

        // Login Button Click Listener
        login.setOnClickListener(v -> {
            String username = uname.getText().toString();
            String password = pass.getText().toString();
            String email = emaill.getText().toString();
            String confirmPassword = pass2.getText().toString();

            Database db = new Database(getApplicationContext(), "healthcare", null, 1);

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(RegisteredActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(RegisteredActivity.this, "Password and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
            } else if (!isValid(password)) {
                Toast.makeText(RegisteredActivity.this, "Password must contain at least 8 characters, 1 uppercase, 1 lowercase, and 1 digit", Toast.LENGTH_LONG).show();
            } else {
                db.register(username, email, password);
                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisteredActivity.this, loginActivity.class));
            }
        });

        // Handle Edge-to-Edge Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to validate password
    public static boolean isValid(String password) {
        if (password.length() < 8) return false;

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            if (Character.isLowerCase(c)) hasLowercase = true;
            if (Character.isDigit(c)) hasDigit = true;

            // If all conditions are met, no need to check further
            if (hasUppercase && hasLowercase && hasDigit) return true;
        }

        return false;
    }


}
