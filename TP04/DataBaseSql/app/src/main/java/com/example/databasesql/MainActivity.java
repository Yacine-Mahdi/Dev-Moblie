package com.example.databasesql;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EmployerDbHelper dbHelper;
    private EditText name;
    private EditText email;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new EmployerDbHelper(this);

        Button save = findViewById(R.id.addEmployer);
        Button viewDB = findViewById(R.id.ViewDB);

        name = findViewById(R.id.nameValue);
        email = findViewById(R.id.emailValue);
        phone = findViewById(R.id.phoneValue);

        save.setOnClickListener(v -> {
            // We delete unnecessary spaces so it doesn't cause problems later in modify data
            String nameValue = name.getText().toString().trim();
            String mailValue = email.getText().toString().trim();
            String phoneValue = phone.getText().toString().trim();
            if (!nameValue.equalsIgnoreCase("") &&
            !mailValue.equalsIgnoreCase("") &&
            !phoneValue.equalsIgnoreCase(""))
            {
                boolean inserted = dbHelper.addEmployer(nameValue, mailValue, phoneValue);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    // Clear the fields for better User Experience
                    name.getText().clear();
                    email.getText().clear();
                    phone.getText().clear();
                } else {
                    Toast.makeText(MainActivity.this, "Insertion Error", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Tous les champs doivent etre remplis", Toast.LENGTH_SHORT).show();
            }
        });

        viewDB.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Employers.class);
            startActivity(intent);
        });
    }


}
